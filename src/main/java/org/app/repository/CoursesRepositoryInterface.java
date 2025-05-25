package org.app.repository;

import org.app.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CoursesRepositoryInterface {
  Course saveCourse(Course course);

  Course updateCourse(Course course);

  Course deleteCourse(Integer id);

  Course getCourseById(Integer id);

  Course getCourseByName(String name);

  List<Course> getAllCourses();
}
