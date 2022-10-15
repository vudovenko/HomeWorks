package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {

    List<T> retrieveAll();

    void store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    boolean removeItemByRegex(String regex);
}
