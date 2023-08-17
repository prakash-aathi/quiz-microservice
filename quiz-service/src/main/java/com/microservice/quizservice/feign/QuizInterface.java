package com.microservice.quizservice.feign;

import com.microservice.quizservice.dto.QuestionDto;
import com.microservice.quizservice.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numQ);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionDto>> getQuestions(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Long> getScore(@RequestBody List<Response> responses);
}
