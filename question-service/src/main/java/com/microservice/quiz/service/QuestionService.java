package com.microservice.quiz.service;

import com.microservice.quiz.dto.QuestionDto;
import com.microservice.quiz.dto.Response;
import com.microservice.quiz.model.Question;
import com.microservice.quiz.repository.QuestionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    QuestionRepo questionDao;
    public QuestionService(QuestionRepo questionDao) {
        this.questionDao = questionDao;
    }

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> generateQuestions(String category, int numQ) {
        List<Integer> questionIds = questionDao.findRandomQuestionsByCategory(category,numQ);
        return  new ResponseEntity<>(questionIds,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDto>> getQuestions(List<Integer> questionIds) {
        List<Question> questions = questionIds.stream().map(id -> questionDao.findById(id).get()).collect(Collectors.toList());
        List<QuestionDto> questionWrapper = questions.stream().map(question -> QuestionDto.builder()
                .id(question.getId())
                .questionTitle(question.getQuestionTitle())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .build()).collect(Collectors.toList());
        return new ResponseEntity<>(questionWrapper,HttpStatus.OK);
    }

    public ResponseEntity<Long> getScore(List<Response> responses) {
        Long score = responses.stream().filter(response ->
                questionDao.findById(response.getId()).get().getRightAnswer()
                        .equals(response.getResponse())).count();
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}