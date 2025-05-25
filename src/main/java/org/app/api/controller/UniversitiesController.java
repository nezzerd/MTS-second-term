package org.app.api.controller;

import lombok.RequiredArgsConstructor;
import org.app.api.annotation.UniversitiesAnnotation;
import org.app.dto.request.UniversityRequest;
import org.app.dto.response.UniversityResponse;
import org.app.service.UniversitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/universities")
public class UniversitiesController implements UniversitiesAnnotation {
  private final UniversitiesService universitiesService;

  @Override
  public ResponseEntity<UniversityResponse> createUniversity(@RequestBody UniversityRequest universityRequest) {
    UniversityResponse universityResponse = universitiesService.createUniversity(universityRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(universityResponse);
  }

  @Override
  public ResponseEntity<UniversityResponse> deleteUniversity(@PathVariable Integer id) {
    UniversityResponse universityResponse = universitiesService.deleteUniversity(id);
    return ResponseEntity.status(HttpStatus.OK).body(universityResponse);
  }

  @Override
  public ResponseEntity<List<UniversityResponse>> getAllUniversities() {
    List<UniversityResponse> universityResponses = universitiesService.getAllUniversities();
    return ResponseEntity.status(HttpStatus.OK).body(universityResponses);
  }
}
