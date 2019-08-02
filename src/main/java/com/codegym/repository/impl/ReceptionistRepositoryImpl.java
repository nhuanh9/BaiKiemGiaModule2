package com.codegym.repository.impl;

import com.codegym.model.Receptionist;
import com.codegym.repository.ReceptionistRepository;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistRepositoryImpl implements ReceptionistRepository {

    private List<Receptionist> receptionistList = new ArrayList<>();

    @Override
    public List<Receptionist> findAll() {
        return receptionistList;
    }

    @Override
    public Receptionist findById(int id) {
        for (int i = 0; i < receptionistList.size(); i++) {
            if (receptionistList.get(i).getId() == id) {
                return receptionistList.get(i);
            }
        }
        return null;
    }

    @Override
    public void addElement(Receptionist element) {
        receptionistList.add(element);
    }

    @Override
    public void updateElement(int id, Receptionist element) {
        int index = -1;
        for (int i = 0; i < receptionistList.size(); i++) {
            if (receptionistList.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        receptionistList.set(id, element);
    }

    @Override
    public void removeElement(int id) {
        for (int i = 0; i < receptionistList.size(); i++) {
            if (receptionistList.get(i).getId() == id) {
                receptionistList.remove(id);
                break;
            }
        }
    }

    @Override
    public List<Receptionist> findByName(String name) {
        List<Receptionist> receptionists = new ArrayList<>();
        for (int i = 0; i < receptionistList.size(); i++) {
            if (receptionistList.get(i).getName().contains(name)) {
                receptionists.add(receptionistList.get(i));
            }
        }
        return receptionists;
    }
}
