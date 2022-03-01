package org.edzest.eep.controllers;

import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.services.TestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
