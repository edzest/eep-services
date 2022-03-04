package org.edzest.eep.services;

import org.edzest.eep.entities.Question;
import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.models.FullTest;
import org.edzest.eep.models.QuestionsBody;
import org.edzest.eep.models.TestResultRequest;
import org.edzest.eep.models.TestResultResponse;
import org.edzest.eep.models.answers.SingleChoiceAnswer;
import org.edzest.eep.models.questions.TestResultResponseQuestion;
import org.edzest.eep.models.scores.SimpleScore;
import org.edzest.eep.repositories.QuestionRepository;
import org.edzest.eep.repositories.TestInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
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
        List<String> optionList = Arrays.asList(
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4());
        QuestionsBody questionsBody = new QuestionsBody(question.getQuestionId(), question.getQuestionTxt(), optionList);
        List<QuestionsBody> questionList = List.of(questionsBody);
        FullTest expectedFullTest = new FullTest(testInfo.getId(), testInfo.getTitle(), testInfo.getInstructions(), questionList);

        when(testInfoRepository.findById(testId)).thenReturn(Optional.of(testInfo));
        when(questionRepository.findAllQuestionByTestId(testId)).thenReturn(Optional.of(List.of(question)));

        FullTest fullTest = testsService.getFullTestByTestId(testId);

        assertThat(fullTest.getTestId()).isEqualTo(expectedFullTest.getTestId());
        assertThat(fullTest.getTitle()).isEqualTo(expectedFullTest.getTitle());
        assertThat(fullTest.getInstructions()).isEqualTo(expectedFullTest.getInstructions());
    }

    @Test
    public void getFullTestWhenTestIsMissing() {
        Long missingTestId = 2L;
        when(testInfoRepository.findById(missingTestId)).thenThrow(NoSuchElementException.class);

        assertThatThrownBy(() -> testsService.getFullTestByTestId(missingTestId))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void evaluateTestResult() {
        Long testId = 1L;
        TestResultRequest testResultRequest = new TestResultRequest();
        testResultRequest.setTestId(testId);
        testResultRequest.setStudentName("MS Dhoni");
        SingleChoiceAnswer answer1 = new SingleChoiceAnswer(1L, "option 4");
        SingleChoiceAnswer answer2 = new SingleChoiceAnswer(2L, "option 3");
        testResultRequest.setAnswers(List.of(answer1, answer2));

        TestInfo testInfo = new TestInfo(testId, "Mock Test", "Sample Instructions");
        Question question1 = new Question(testId, testInfo, "Fake question", "option 1", "option 2",
                "option 3", "option 4", "option 4");
        Question question2 = new Question(testId, testInfo, "Fake question number 2", "option 1", "option 2",
                "option 3", "option 4", "option 4");

        when(testInfoRepository.findById(testId)).thenReturn(Optional.of(testInfo));
        when(questionRepository.findAllQuestionByTestId(testId)).thenReturn(Optional.of(List.of(question1, question2)));

        SimpleScore scores = new SimpleScore(1,2);
        TestResultResponseQuestion qstnResponse1 = new TestResultResponseQuestion(testId, question1.getQuestionTxt(),
                List.of(question1.getOption1(), question1.getOption2(), question1.getOption3(), question1.getOption4()),
                "option 4", "option 4");
        TestResultResponseQuestion qstnResponse2 = new TestResultResponseQuestion(testId, question2.getQuestionTxt(),
                List.of(question2.getOption1(), question2.getOption2(), question2.getOption3(), question2.getOption4()),
                "option 4", "option 3");
        TestResultResponse expectedResponse = new TestResultResponse(
                testId,
                "MS Dhoni",
                scores,
                List.of(qstnResponse1, qstnResponse2)
        );

        TestResultResponse actualResponse = testsService.evaluateTest(testResultRequest);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }


}
