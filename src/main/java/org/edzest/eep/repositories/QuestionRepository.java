package org.edzest.eep.repositories;

import org.edzest.eep.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllQuestionByTestId(Long testId);
}
