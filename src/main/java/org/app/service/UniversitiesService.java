package org.app.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.app.dto.request.UniversityRequest;
import org.app.dto.response.UniversityResponse;
import org.app.entity.University;
import org.app.repository.UniversitiesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class UniversitiesService {
  private final UniversitiesRepository universitiesRepository;

  public UniversityResponse createUniversity(@Valid @NonNull UniversityRequest universityRequest) {
    University university = new University();
    university.setName(universityRequest.getName());
    university.setCity(universityRequest.getCity());

    if (universitiesRepository.getUniversityByNameAndCity(university.getName(), university.getCity()) == null) {
      universitiesRepository.saveUniversity(university);
      log.info("Created new university {} by {}", universityRequest.getName(), universityRequest.getCity());
      UniversityResponse universityResponse = new UniversityResponse();
      universityResponse.setCity(universityRequest.getCity());
      universityResponse.setName(universityRequest.getName());
      universityResponse.setId(university.getId());
      return universityResponse;
    } else {
      log.error("University with name {} and city {} already exists", universityRequest.getName(), universityRequest.getCity());
      throw new ResponseStatusException(HttpStatus.CONFLICT, "University with name " + universityRequest.getName() + " and city " + universityRequest.getCity() + " already exists");
    }
  }

  public UniversityResponse deleteUniversity(Integer id) {
    University university = universitiesRepository.getUniversityById(id);

    if (university != null) {
      universitiesRepository.deleteUniversity(id);
      log.info("Deleted university with id {}", id);
      UniversityResponse universityResponse = new UniversityResponse();
      universityResponse.setCity(university.getCity());
      universityResponse.setName(university.getName());
      universityResponse.setId(id);
      return universityResponse;
    } else {
      log.error("University with id {} doesn't exist", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "University doesn't exists");
    }
  }

  public List<UniversityResponse> getAllUniversities() {
    log.info("Retrieving all universities");
    List<University> universities = universitiesRepository.getAllUniversities();

    return universities.stream()
        .map(university -> new UniversityResponse(
            university.getId(),
            university.getName(),
            university.getCity()
        ))
        .collect(Collectors.toList());
  }
}
