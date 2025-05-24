package org.app.api.controller;

import lombok.RequiredArgsConstructor;
import org.app.api.annotation.CoursesAnnotation;
import org.app.dto.request.CourseRequest;
import org.app.dto.response.CourseResponse;
import org.app.service.CoursesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CoursesController implements CoursesAnnotation {
  private final CoursesService coursesService;

  @Override
  public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
    CourseResponse courseResponse = coursesService.createCourse(courseRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
  }

  @Override
  public ResponseEntity<CourseResponse> deleteCourse(@PathVariable Integer id) {
    CourseResponse courseResponse = coursesService.deleteCourse(id);
    return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
  }

  @Override
  public ResponseEntity<List<CourseResponse>> getAllCourses() {
    List<CourseResponse> courseResponses = coursesService.getAllCourses();
    return ResponseEntity.status(HttpStatus.OK).body(courseResponses);
  }

  @Override
  public ResponseEntity<CourseResponse> fullUpdateCourse(@PathVariable Integer id, @RequestBody CourseRequest courseRequest) {
    CourseResponse courseResponse = coursesService.updateCourse(id, courseRequest);
    return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
  }

  @Override
  public ResponseEntity<CourseResponse> getCourseById(@PathVariable Integer id) {
    return ResponseEntity.ok(coursesService.getCourseById(id));
  }
}