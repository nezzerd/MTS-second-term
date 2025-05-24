package org.app.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Slf4j
@Repository
public class CoursesRepository implements CoursesRepositoryInterface {
  private final List<Course> courses = new CopyOnWriteArrayList<>();
  private static final AtomicInteger idCounter = new AtomicInteger(1);

  @Override
  public Course saveCourse(Course course) {
    log.info("Creating new course: {}", course);
    course.setId(idCounter.getAndIncrement());
    courses.add(course);
    return course;
  }

  @Override
  public Course updateCourse(Course updatedCourse) {
    log.info("Updating course: {}", updatedCourse);
    Course oldCourse = getCourseById(updatedCourse.getId());
    if (oldCourse != null) {
      courses.set(courses.indexOf(oldCourse), updatedCourse);
    }
    return updatedCourse;
  }

  @Override
  public Course getCourseById(Integer id) {
    log.info("Retrieving course by id: {}", id);
    return courses.stream()
        .filter(course -> course.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Course getCourseByName(String name) {
    log.info("Retrieving course by name: {}", name);
    return courses.stream()
        .filter(course -> course.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  @Override
  public Course deleteCourse(Integer id) {
    log.info("Deleting course with id: {}", id);
    Course course = getCourseById(id);
    if (course != null) {
      courses.remove(course);
    }
    return course;
  }

  @Override
  public List<Course> getAllCourses() {
    log.info("Retrieving all courses");
    return List.copyOf(courses);
  }
}
