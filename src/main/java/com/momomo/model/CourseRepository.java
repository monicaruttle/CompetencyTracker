package com.momomo.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Monica on 2017-02-19.
 */
public interface CourseRepository extends CrudRepository<Course, String> {
    Course findByName(String name);
}