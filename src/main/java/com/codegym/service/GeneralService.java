package com.codegym.service;

import java.util.List;

public interface GeneralService<T> {
    List<T> findAll();

    T findById(int id);

    void addElement(T element);

    void updateElement(int id, T element);

    void removeElement(int id);

    List<T> findByName(String name);

}