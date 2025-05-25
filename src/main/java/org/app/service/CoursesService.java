package org.app.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.app.dto.request.CourseRequest;
import org.app.dto.response.CourseResponse;
import org.app.entity.Course;
import org.app.repository.CoursesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class CoursesService {
  private final CoursesRepository coursesRepository;

  public CourseResponse createCourse(@Valid @NonNull CourseRequest courseRequest) {
    Course course = new Course();
    course.setName(courseRequest.getName());

    if (coursesRepository.getCourseByName(course.getName()) == null) {
      coursesRepository.saveCourse(course);
      log.info("Created new course {}", courseRequest.getName());
      CourseResponse courseResponse = new CourseResponse();
      courseResponse.setName(courseRequest.getName());
      courseResponse.setId(course.getId());
      return courseResponse;
    } else {
      log.error("Course with name {}", courseRequest.getName());
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Course with name " + courseRequest.getName() + " already exists");
    }
  }

  public CourseResponse deleteCourse(Integer id) {
    Course course = coursesRepository.getCourseById(id);

    if (course != null) {
      coursesRepository.deleteCourse(id);
      log.info("Deleted course with id {}", id);
      CourseResponse courseResponse = new CourseResponse();
      courseResponse.setName(course.getName());
      courseResponse.setId(id);
      return courseResponse;
    } else {
      log.error("Course with id {} not found", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with id " + id + " not found");
    }
  }

  public List<CourseResponse> getAllCourses() {
    log.info("Retrieving all courses");
    List<Course> courses = coursesRepository.getAllCourses();

    return courses.stream()
        .map(course -> new CourseResponse(
            course.getId(),
            course.getName()
        ))
        .collect(Collectors.toList());
  }

  public CourseResponse updateCourse(@NonNull @Positive Integer courseId, @Valid @NonNull CourseRequest courseRequest) {
    Course course = coursesRepository.getCourseById(courseId);
    if (course != null) {
      course.setName(courseRequest.getName());
      coursesRepository.updateCourse(course);
      log.info("Updated course {}", courseRequest.getName());
      CourseResponse courseResponse = new CourseResponse();
      courseResponse.setName(courseRequest.getName());
      courseResponse.setId(courseId);
      return courseResponse;
    } else {
      log.error("Course with id {} doesnt exist", courseId);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with id " + courseId + " not found");
    }
  }

  public CourseResponse getCourseById(@NonNull @Positive Integer courseId) {
    Course course = coursesRepository.getCourseById(courseId);
    if (course != null) {
      CourseResponse bookResponse = new CourseResponse();
      bookResponse.setName(course.getName());
      bookResponse.setId(courseId);
      return bookResponse;
    } else {
      log.error("Course with id {} doesnt exist", courseId);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course with id " + courseId + " not found");
    }
  }
}
