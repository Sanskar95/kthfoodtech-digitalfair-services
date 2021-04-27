package com.kth.foodtech.digitalfair.service;


import com.kth.foodtech.digitalfair.model.Question;
import com.kth.foodtech.digitalfair.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class QuestionService {


    @Autowired
    private QuestionRepository questionRepository;


    public List<Question> getQuestionsByCompany(String company){
        List<Question> questions =
                StreamSupport.stream(questionRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return questions;
    }

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }
}