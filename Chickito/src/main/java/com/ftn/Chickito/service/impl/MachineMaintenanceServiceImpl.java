package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.dto.machineMaintenance.CreateMachineMaintenanceDto;
import com.ftn.Chickito.dto.machineMaintenance.MaintenanceReportDto;
import com.ftn.Chickito.dto.order.OrderReportDto;
import com.ftn.Chickito.mapper.MachineMaintenanceMapper;
import com.ftn.Chickito.mapper.SectorMapper;
import com.ftn.Chickito.model.*;
import com.ftn.Chickito.model.enums.SectorType;
import com.ftn.Chickito.repository.*;
import com.ftn.Chickito.service.EmailService;
import com.ftn.Chickito.service.MachineMaintenanceService;
import com.ftn.Chickito.service.WorkerOnMachineService;
import com.sun.istack.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MachineMaintenanceServiceImpl implements MachineMaintenanceService {

    private final MachineMaintenanceRepository machineMaintenanceRepository;
    private final MachineMaintenanceItemRepository machineMaintenanceItemRepository;
    private final WorkerOnMachineService workerOnMachineService;
    private final UserRepository userRepository;
    private final MachineRepository machineRepository;
    private final SectorRepository sectorRepository;
    private final EmailService emailService;
    private final MachineMaintenanceMapper mapper;
    private final static String LEADER_ROLE = "LEADER";
    private final static String WORKER_ROLE = "WORKER";

    @Override
    public MachineMaintenance createMachineMaintenance(String authorUsername, CreateMachineMaintenanceDto createMaintenanceDto) {


        Set<MachineMaintenanceItem> items = new HashSet<>();
        User author = getLeader(authorUsername);

        createMaintenanceDto.getItems().forEach(itemDto -> {

            Machine machine = machineRepository.findById(itemDto.getMachine().getId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Machine with id = %s doesn't exist.", itemDto.getMachine().getId())));

            MachineMaintenanceItem machineMaintenanceItem = MachineMaintenanceItem.builder()
                    .machine(machine)
                    .plan(itemDto.getPlan())
                    .build();

            items.add(machineMaintenanceItem);
        });

        MachineMaintenance machineMaintenance = MachineMaintenance.builder()
                .author(author)
                .items(items)
                .year(createMaintenanceDto.getYear())
                .build();

        items.forEach(item -> item.setMachineMaintenance(machineMaintenance));
        machineMaintenanceRepository.save(machineMaintenance);

        return machineMaintenance;

    }

    private User getLeader(String username) {
        return userRepository.findByUsernameAndRoleName(username, LEADER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Leader with username = %s doesn't exist.", username)));
    }

    private User getWorker(String username) {
        return userRepository.findByUsernameAndRoleName(username, WORKER_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Worker with username = %s doesn't exist.", username)));
    }

    @Override
    public List<MachineMaintenance> findAllByAuthor(String authorUsername) {
        return this.machineMaintenanceRepository.findAllByAuthor(authorUsername);
    }

    @Override
    public List<MachineMaintenance> findAllByDirector(String directorUsername) {
        return this.machineMaintenanceRepository.findAllByDirector(directorUsername);
    }

    @Override
    public void generateMaintenancePdfLeader(String username, Integer year) throws FileNotFoundException, JRException, MessagingException {

        this.generateMaintenancePdf(username,this.machineMaintenanceItemRepository.findAllByYearAndAuthor(username, year));
    }

    @Override
    public void generateMaintenancePdfDirector(String username, Integer year, String sectorType) throws JRException, MessagingException, FileNotFoundException {

        Sector sector = sectorRepository.findByDirectorAndType(username, SectorType.valueOf(sectorType));
        this.generateMaintenancePdf(username,this.machineMaintenanceItemRepository.findAllByYearAndSector(sector.getId(), year));
    }

    @Override
    public Set<MachineMaintenanceItem> findAllByWorker(String username) {
        List<Machine> machines = workerOnMachineService.findAllMachinesByWorker(username);
        List<MachineMaintenanceItem> ret = new ArrayList<>();
        Integer year = LocalDate.now().getYear();

        machines.forEach(machine -> {
            ret.addAll(machineMaintenanceItemRepository.findAllByYearAndMachine(machine.getId(), year));
        });

        return null;
    }

    @Override
    public Set<MachineMaintenanceItem> findAllByMachine(Long machineId) {
        Integer year = LocalDate.now().getYear();
        return machineMaintenanceItemRepository.findAllByYearAndMachine(machineId, year);
    }

    private void generateMaintenancePdf(String username, List<MachineMaintenanceItem> maintenanceItems) throws FileNotFoundException, JRException, MessagingException {

        if(maintenanceItems.isEmpty()){
            throw new EntityNotFoundException(String.format("Nema planova održavanja za zadate kriterijume."));
        }

        for (MachineMaintenanceItem m: maintenanceItems) {
            if(m.getMachine().getModel() == null){
                m.getMachine().setModel("Nije naveden");
            }
        }


        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with username = %s doesn't exist.", username)));

        File file = ResourceUtils.getFile("classpath:reportTemplates\\MaintenancePlan.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(maintenanceItems);

        MaintenanceReportDto reportDto = mapper.maintenanceToMaintenanceReportDto(maintenanceItems.get(0).getMachineMaintenance());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyName", reportDto.getCompanyName());
        parameters.put("sector", reportDto.getSector());
        parameters.put("headOffice", reportDto.getHeadOffice());
        parameters.put("author", reportDto.getAuthor());
        parameters.put("title" , reportDto.getTitle());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
        DataSource attachment =  new ByteArrayDataSource(byteArrayOutputStream.toByteArray(), "application/pdf");

        this.emailService.sendMachineMaintenancePdf(user.getEmail(), attachment, reportDto.getYear());

    }


}
