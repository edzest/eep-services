package org.edzest.eep.controllers;

import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.models.FullTest;
import org.edzest.eep.services.TestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = {"http://localhost:4200/", "https://eep-dashboard.web.app"})
public class TestController {

    @Autowired
    private TestsService testInfoService;

    @GetMapping
    public List<TestInfo> getAllTests() {
        return testInfoService.findAll();
    }

    @GetMapping("/{testId}")
    public FullTest getFullTest(@PathVariable(value = "testId") Long testId) {
        try {
            return testInfoService.getFullTestByTestId(testId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "test not found"
            );
        }
    }
}
