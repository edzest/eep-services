package org.edzest.eep.controllers;

import org.edzest.eep.models.TestResultRequest;
import org.edzest.eep.models.TestResultResponse;
import org.edzest.eep.services.TestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test_result")
@CrossOrigin(origins = {"http://localhost:4200/", "https://eep-dashboard.web.app"})
public class TestResultController {

    private TestsService testsService;

    @Autowired
    public TestResultController(TestsService testsService) {
        this.testsService = testsService;
    }

    @PostMapping
    public TestResultResponse evaluate(@RequestBody TestResultRequest testResultRequest) {
        return this.testsService.evaluateTest(testResultRequest);
    }
}
