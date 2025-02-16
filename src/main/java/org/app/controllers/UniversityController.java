package org.app.controllers;

import org.app.entities.University;
import org.app.services.UniversityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public List<University> getUniversities() {
        return universityService.getUniversities();
    }

    @GetMapping("/search")
    public University getUniversityByName(@RequestParam String name) {
        return universityService.getUniversityByName(name);
    }

    @DeleteMapping("/delete")
    public boolean deleteUniversityByName(@RequestParam String name) {
        return universityService.deleteUniversityByName(name);
    }

    @GetMapping("/by-city")
    public List<University> getUniversitiesByAuthor(@RequestParam String city) {
        return universityService.getUniversitiesByCity(city);
    }
}
