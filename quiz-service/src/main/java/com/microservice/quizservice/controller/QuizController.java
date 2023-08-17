package com.microservice.quizservice.controller;

import com.microservice.quizservice.dto.QuestionDto;
import com.microservice.quizservice.dto.QuizDto;
import com.microservice.quizservice.dto.Response;
import com.microservice.quizservice.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    QuizService quizService;
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Long> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}