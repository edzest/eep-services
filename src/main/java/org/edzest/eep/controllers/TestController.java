package org.edzest.eep.controllers;

import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.services.TestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestInfoService testInfoService;

    @GetMapping
    public List<TestInfo> getAllTests() {
        return testInfoService.findAll();
    }
}
