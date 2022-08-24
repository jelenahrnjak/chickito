package com.ftn.Chickito.service;

import com.ftn.Chickito.model.Machine;

import java.util.List;

public interface MachineService {

    List<Machine> findAllByLeader(String leaderUsername);

    List<Machine> findAllByDirector(String username);
}
