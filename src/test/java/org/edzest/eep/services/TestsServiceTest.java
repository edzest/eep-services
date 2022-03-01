package org.edzest.eep.services;

import org.edzest.eep.entities.Question;
import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.models.FullTest;
import org.edzest.eep.models.QuestionsBody;
import org.edzest.eep.repositories.QuestionRepository;
import org.edzest.eep.repositories.TestInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class TestsServiceTest {

    private TestsService testsService;
    private TestInfoRepository testInfoRepository;
    private QuestionRepository questionRepository;

    @BeforeEach
    public void init() {
        testInfoRepository = Mockito.mock(TestInfoRepository.class);
        questionRepository = Mockito.mock(QuestionRepository.class);
        testsService = new TestsService(testInfoRepository, questionRepository);
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
        Long testId = 1L;
        TestInfo testInfo = new TestInfo(testId, "Mock Test", "Sample Instructions");
        Question question = new Question(testId, testInfo, "Fake question", "option 1", "option 2",
                "option 3", "option 4", "option 4");
        List<String> optionList = List.of(
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4());
        QuestionsBody questionsBody = new QuestionsBody(question.getQuestionId(), question.getQuestionTxt(), optionList);
        List<QuestionsBody> questionList = List.of(questionsBody);
        FullTest expectedFullTest = new FullTest(testInfo.getId(), testInfo.getTitle(), testInfo.getInstructions(), questionList);

        when(testInfoRepository.findById(testId)).thenReturn(Optional.of(testInfo));
        when(questionRepository.findAllQuestionByTestId(testId)).thenReturn(List.of(question));

        FullTest fullTest = testsService.getFullTestByTestId(testId);

        assertThat(fullTest).isEqualTo(expectedFullTest);
    }

    @Test
    public void getFullTestWhenTestIsMissing() {
        Long missingTestId = 2L;
        when(testInfoRepository.findById(missingTestId)).thenThrow(NoSuchElementException.class);

        assertThatThrownBy(() -> testsService.getFullTestByTestId(missingTestId))
                .isInstanceOf(NoSuchElementException.class);
    }


}
