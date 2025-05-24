package org.app.repository;

import org.app.entity.University;

import java.util.Optional;
import java.util.List;

public interface UniversitiesRepositoryInterface {
  University saveUniversity(University university);

  University updateUniversity(University university);

  University getUniversityById(Integer id);

  University getUniversityByNameAndCity(String name, String city);

  University deleteUniversity(Integer id);

  List<University> getAllUniversities();
}