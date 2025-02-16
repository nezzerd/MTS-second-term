package org.app.services;

import org.app.entities.Course;
import org.app.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;


    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    public boolean deleteCourseByName(String name) {
        return courseRepository.deleteByName(name);
    }
}
