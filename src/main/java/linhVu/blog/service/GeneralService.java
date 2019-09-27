package linhVu.blog.service;

import java.util.List;

public interface GeneralService<E> {
    List<E> findAll();

    E findById(int id);

    List<E>findByName(String name);

    void save(E e);

    void remove(int id);

}
