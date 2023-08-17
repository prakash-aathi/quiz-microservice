package com.microservice.quizservice.repositiry;

import com.microservice.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
