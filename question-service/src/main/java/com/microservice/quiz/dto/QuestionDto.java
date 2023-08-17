package com.microservice.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class QuestionDto {

    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
