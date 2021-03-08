package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.models.Score;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Integer> {
	List<Score> getAllByQuiz(Quiz quiz);

	@Query(value = "SELECT * FROM score WHERE user_id = ?1 AND quiz_id = ?2 LIMIT 1", nativeQuery = true)
	Score getUserScoreByQuiz(int userId, int quizId);
}
