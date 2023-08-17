package com.microservice.quizservice.service;


import com.microservice.quizservice.dto.QuestionDto;
import com.microservice.quizservice.dto.Response;
import com.microservice.quizservice.feign.QuizInterface;
import com.microservice.quizservice.model.Quiz;
import com.microservice.quizservice.repositiry.QuizRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    QuizRepo quizDao;

    QuizInterface quizInterface;

    public QuizService(QuizRepo quizDao, QuizInterface quizInterface) {
        this.quizDao = quizDao;
        this.quizInterface = quizInterface;
    }


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.generateQuestions(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDto>> getQuizQuestions(Integer id) {
          Optional<Quiz> quiz = quizDao.findById(id);
          if(quiz.isPresent()){
            return  quizInterface.getQuestions(quiz.get().getQuestions());
          }else{
              throw new RuntimeException("Quiz not found");
          }
    }

    public ResponseEntity<Long> calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }
}