package com.microservice.quiz.controller;


import com.microservice.quiz.dto.QuestionDto;
import com.microservice.quiz.dto.Response;
import com.microservice.quiz.model.Question;
import com.microservice.quiz.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    QuestionService questionService;
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numQ){
        return questionService.generateQuestions(category,numQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestions(@RequestBody List<Integer> questionIds){
        return questionService.getQuestions(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Long> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }


}