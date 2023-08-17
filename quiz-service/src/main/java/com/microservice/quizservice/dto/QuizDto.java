package com.microservice.quizservice.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class QuizDto {

    private String category;
    private int numQ;
    private String title;

}
