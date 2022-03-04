package org.edzest.eep.services;

import org.aspectj.weaver.tools.cache.SimpleCache;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestsService {

    private TestInfoRepository testInfoRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public TestsService(TestInfoRepository testInfoRepository, QuestionRepository questionRepository) {
        this.testInfoRepository = testInfoRepository;
        this.questionRepository = questionRepository;
    }

    public List<TestInfo> findAll() {
        return testInfoRepository.findAll();
    }

    public FullTest getFullTestByTestId(Long testId) throws NoSuchElementException {
        TestInfo testInfo = testInfoRepository.findById(testId).orElseThrow(NoSuchElementException::new);
        List<Question> questions = questionRepository.findAllQuestionByTestId(testId).orElse(List.of());

        List<QuestionsBody> questionsBodyList = questions.stream().map((question) -> {

            List<String> options = Arrays.asList(
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4());
            return new QuestionsBody(question.getQuestionId(), question.getQuestionTxt(), options);
        }).collect(Collectors.toList());

        return new FullTest(testInfo.getId(), testInfo.getTitle(), testInfo.getInstructions(), questionsBodyList);
    }

    public TestResultResponse evaluateTest(TestResultRequest testResultRequest) {
        Long testId = testResultRequest.getTestId();
        TestResultResponse response = new TestResultResponse();
        response.setTestId(testId);
        response.setStudentName(testResultRequest.getStudentName());

        List<Question> questions = questionRepository.findAllQuestionByTestId(testId).orElse(List.of());
        List<SingleChoiceAnswer> answers = testResultRequest.getAnswers();

        // calculate score and build result answer response - this assumes both the list are of same size
        // & contains same question Ids
        Iterator<Question> questionIterator = questions.iterator();
        Iterator<SingleChoiceAnswer> answerIterator = answers.iterator();

        List<TestResultResponseQuestion> responseQuestions = new ArrayList<>();
        SimpleScore score = new SimpleScore();
        score.setOutOf(questions.size());
        while (questionIterator.hasNext() && answerIterator.hasNext()) {
            TestResultResponseQuestion responseQuestion = new TestResultResponseQuestion();
            Question question = questionIterator.next();
            SingleChoiceAnswer answer = answerIterator.next();
            responseQuestion.setQuestionId(question.getQuestionId());
            responseQuestion.setQuestionTxt(question.getQuestionTxt());
            responseQuestion.setOptions(question.getAllOptions());
            String correctOption = question.getCorrectOption();
            String selectedOption = answer.getSelectedOption();
            responseQuestion.setCorrectOption(correctOption);
            responseQuestion.setSelectedOption(selectedOption);
            if (Objects.equals(correctOption, selectedOption)) score.incrementScored();
            responseQuestions.add(responseQuestion);
        }
        response.setScores(score);
        response.setQuestions(responseQuestions);

        return response;
    }
}
