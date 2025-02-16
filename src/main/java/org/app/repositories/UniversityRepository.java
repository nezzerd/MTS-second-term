package org.app.repositories;

import org.app.entities.University;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UniversityRepository {
    private final List<University> universities = new ArrayList<>();

    public UniversityRepository() {
        AtomicInteger idCounter = new AtomicInteger(1);
        universities.add(new University(idCounter.getAndIncrement(), "msu", "moscow"));
        universities.add(new University(idCounter.getAndIncrement(), "mai", "moscow"));
        universities.add(new University(idCounter.getAndIncrement(), "itmo", "spb"));
    }

    public List<University> findAll() {
        return universities;
    }

    public University findByName(String name) {
        for (University university : universities) {
            if (university.name().equalsIgnoreCase(name)) {
                return university;
            }
        }
        return null;
    }

    public boolean deleteByName(String name) {
        University university = findByName(name);
        if (university != null) {
            universities.remove(university);
            return true;
        }
        return false;
    }

    public List<University> findUniversitiesByCity(String city) {
        List<University> cityUniversities = new ArrayList<>();
        for (University university : universities) {
            if (university.city().equalsIgnoreCase(city)) {
                cityUniversities.add(university);
            }
        }
        return cityUniversities;
    }
}
