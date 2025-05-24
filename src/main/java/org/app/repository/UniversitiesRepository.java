package org.app.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.entity.University;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@NoArgsConstructor
@Repository
public class UniversitiesRepository implements UniversitiesRepositoryInterface {
  private final List<University> universities = new CopyOnWriteArrayList<>();
  private static final AtomicInteger idCounter = new AtomicInteger(1);

  @Override
  public University saveUniversity(University university) {
    log.info("Creating new university: {}", university);
    university.setId(idCounter.getAndIncrement());
    universities.add(university);
    return university;
  }

  @Override
  public University updateUniversity(University updatedUniversity) {
    log.info("Updating university: {}", updatedUniversity);
    University oldUniversity = getUniversityById(updatedUniversity.getId());
    if (oldUniversity != null) {
      universities.set(universities.indexOf(oldUniversity), updatedUniversity);
    }
    return updatedUniversity;
  }

  @Override
  public University getUniversityById(Integer id) {
    log.info("Retrieving university by id: {}", id);
    return universities.stream()
        .filter(university -> university.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  @Override
  public University getUniversityByNameAndCity(String name, String city) {
    log.info("Retrieving university by name and city: {}, {}", name, city);
    return universities.stream()
        .filter(university -> university.getName().equals(name) && university.getCity().equals(city))
        .findFirst()
        .orElse(null);
  }

  @Override
  public University deleteUniversity(Integer id) {
    log.info("Deleting university with id: {}", id);
    University university = getUniversityById(id);
    if (university != null) {
      universities.remove(university);
    }
    return university;
  }

  @Override
  public List<University> getAllUniversities() {
    log.info("Retrieving all universities");
    return List.copyOf(universities);
  }
}
