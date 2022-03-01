package org.edzest.eep.services;

import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.repositories.TestInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class TestsServiceTest {

    private TestsService testsService;
    private TestInfoRepository testInfoRepository;

    @BeforeEach
    public void init() {
        testInfoRepository = Mockito.mock(TestInfoRepository.class);
        testsService = new TestsService(testInfoRepository);
    }

    @Test
    public void findAllShouldReturnAllTests() {
        TestInfo testInfo = new TestInfo(1L, "Mock Test", "Sample instructions");
        List<TestInfo> expectedTestInfoList = List.of(testInfo);
        when(testInfoRepository.findAll()).thenReturn(expectedTestInfoList);

        List<TestInfo> testInfoList = testsService.findAll();

        assertThat(testInfoList).isEqualTo(expectedTestInfoList);
    }

    @Test
    public void getFullTestByTestId() {

    }


}
