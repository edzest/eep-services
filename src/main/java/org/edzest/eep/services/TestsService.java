package org.edzest.eep.services;

import org.edzest.eep.entities.Question;
import org.edzest.eep.entities.TestInfo;
import org.edzest.eep.models.FullTest;
import org.edzest.eep.models.QuestionsBody;
import org.edzest.eep.repositories.QuestionRepository;
import org.edzest.eep.repositories.TestInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        List<Question> questions = questionRepository.findAllQuestionByTestId(testId);

        List<QuestionsBody> questionsBodyList = questions.stream().map((question) -> {
            List<String> options = List.of(
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4());
            return new QuestionsBody(question.getQuestionId(), question.getQuestionTxt(), options);
        }).collect(Collectors.toList());

        return new FullTest(testInfo.getId(), testInfo.getTitle(), testInfo.getInstructions(), questionsBodyList);
    }
}
