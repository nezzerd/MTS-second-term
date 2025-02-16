package org.app.services;

import org.app.entities.University;
import org.app.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

    public University getUniversityByName(String name) {
        return universityRepository.findByName(name);
    }

    public boolean deleteUniversityByName(String name) {
        return universityRepository.deleteByName(name);
    }

    public List<University> getUniversitiesByCity(String city) {
        return universityRepository.findUniversitiesByCity(city);
    }
}

