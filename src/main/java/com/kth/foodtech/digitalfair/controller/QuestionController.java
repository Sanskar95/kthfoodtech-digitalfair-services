package com.kth.foodtech.digitalfair.controller;


import com.kth.foodtech.digitalfair.model.Question;
import com.kth.foodtech.digitalfair.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{company}")
    public List<Question> getAllCompanyQuestions(@PathVariable String company){
        return questionService.getQuestionsByCompany(company);
    }

    @PostMapping("/create")
    public Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    @PostMapping("/bulk_create")
    public List<Question> createBulkQuestions(@RequestBody List<Question> questions){
        return questionService.createBulkQuestions(questions);
    }
}
