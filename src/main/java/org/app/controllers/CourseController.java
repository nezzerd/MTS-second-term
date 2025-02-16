package org.app.controllers;

import org.app.entities.Course;
import org.app.services.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/search")
    public Course getCourseByTitle(@RequestParam String name) {
        return courseService.getCourseByName(name);
    }

    @DeleteMapping("/delete")
    public boolean deleteCourseByName(@RequestParam String name) {
        return courseService.deleteCourseByName(name);
    }
}
