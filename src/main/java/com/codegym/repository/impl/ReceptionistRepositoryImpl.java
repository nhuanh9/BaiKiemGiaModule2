package com.codegym.repository.impl;

import com.codegym.model.Receptionist;
import com.codegym.repository.ReceptionistRepository;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistRepositoryImpl implements ReceptionistRepository {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO = Charset.forName("ISO-8859-1");

    private static List<Receptionist> receptionistList ;
    static {
        receptionistList = new ArrayList<>();
        receptionistList.add(new Receptionist(1,"Quinn", 10,"Việt Nam","Bay","1.png"));
        receptionistList.add(new Receptionist(2,"Tris", 20,"Yodle","Bắn","2.jpg"));
        receptionistList.add(new Receptionist(3,"Veigar", 30,"Pintorver","Farm","3.jpg"));
    }
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
        receptionistList.set(index, element);
    }

    @Override
    public void removeElement(int id) {
        for (int i = 0; i < receptionistList.size(); i++) {
            if (receptionistList.get(i).getId() == id) {
                receptionistList.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Receptionist> findByName(String name) {
        List<Receptionist> receptionists = new ArrayList<>();
        for (int i = 0; i < receptionistList.size(); i++) {
            if (receptionistList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                receptionists.add(receptionistList.get(i));
            }
        }
        return receptionists;
    }
}