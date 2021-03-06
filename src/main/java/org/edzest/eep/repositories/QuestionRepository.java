package org.edzest.eep.repositories;

import org.edzest.eep.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<List<Question>> findAllQuestionByTestId(Long testId);
}
