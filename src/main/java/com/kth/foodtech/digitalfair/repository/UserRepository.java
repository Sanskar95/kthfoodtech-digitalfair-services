package com.kth.foodtech.digitalfair.repository;

import com.kth.foodtech.digitalfair.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
