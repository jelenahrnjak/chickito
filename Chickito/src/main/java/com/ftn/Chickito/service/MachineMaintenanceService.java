package com.ftn.Chickito.service;


import com.ftn.Chickito.dto.machineMaintenance.CreateMachineMaintenanceDto;
import com.ftn.Chickito.model.MachineMaintenance;
import com.ftn.Chickito.model.MachineMaintenanceItem;
import net.sf.jasperreports.engine.JRException;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public interface MachineMaintenanceService {


    MachineMaintenance createMachineMaintenance(String authorUsername, CreateMachineMaintenanceDto createMaintenanceDto);

    List<MachineMaintenance> findAllByAuthor(String authorUsername);

    List<MachineMaintenance> findAllByDirector(String directorUsername);

    void generateMaintenancePdfLeader(String username, Integer year) throws FileNotFoundException, JRException, MessagingException;

    void generateMaintenancePdfDirector(String username, Integer year, String sector) throws FileNotFoundException, JRException, MessagingException;

    Set<MachineMaintenanceItem> findAllByWorker(String username);

    Set<MachineMaintenanceItem> findAllByMachine(Long machineId);
}