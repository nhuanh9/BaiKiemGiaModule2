package com.codegym.service.impl;

import com.codegym.model.Receptionist;
import com.codegym.repository.ReceptionistRepository;
import com.codegym.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReceptionistServiceImpl implements ReceptionistService {
    @Autowired
    ReceptionistRepository receptionistRepository;

    @Override
    public List<Receptionist> findAll() {
        return receptionistRepository.findAll();
    }

    @Override
    public Receptionist findById(int id) {
        return receptionistRepository.findById(id);
    }

    @Override
    public void addElement(Receptionist element) {
        receptionistRepository.addElement(element);
    }

    @Override
    public void updateElement(int id, Receptionist element) {
        receptionistRepository.updateElement(id, element);
    }

    @Override
    public void removeElement(int id) {
        receptionistRepository.removeElement(id);
    }

    @Override
    public List<Receptionist> findByName(String name) {
        return receptionistRepository.findByName(name);
    }
}
