package com.momomo.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Monica on 2017-02-19.
 */
public interface BookRepository extends CrudRepository<Book, String> {
    Book findByName(String name);
}