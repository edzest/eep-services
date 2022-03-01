package org.edzest.eep.services;

import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.models.FullTest;
import org.edzest.eep.repositories.TestInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestsService {

    private TestInfoRepository testInfoRepository;

    @Autowired
    public TestsService(TestInfoRepository testInfoRepository) {
        this.testInfoRepository = testInfoRepository;
    }

    public List<TestInfo> findAll() {
        return testInfoRepository.findAll();
    }

    public FullTest getFullTestByTestId(Long testId) {
        return null;
    }
}
