package org.app.repositories;

import org.app.entities.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        AtomicInteger idCounter = new AtomicInteger(1);
        courses.add(new Course(idCounter.getAndIncrement(), "maths"));
        courses.add(new Course(idCounter.getAndIncrement(), "physics"));
        courses.add(new Course(idCounter.getAndIncrement(), "eng"));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Course findByName(String name) {
        for (Course course : courses) {
            if (course.name().equalsIgnoreCase(name)) {
                return course;
            }
        }
        return null;
    }

    public boolean deleteByName(String name) {
        Course course = findByName(name);
        if (course != null) {
            courses.remove(course);
            return true;
        }
        return false;
    }
}
