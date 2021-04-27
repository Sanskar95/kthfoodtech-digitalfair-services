package com.kth.foodtech.digitalfair.repository;

import com.kth.foodtech.digitalfair.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    public Iterable<Optional<Question>> findAllByCompany(String company);
}
