package org.edzest.eep.services;

import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.repositories.TestInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestInfoService {
    @Autowired
    private TestInfoRepository testInfoRepository;

    public List<TestInfo> findAll() {
        return testInfoRepository.findAll();
    }
}
