package com.aromero.flink.domain;

import com.aromero.flink.domain.entity.UserInteractionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInteractionRepository extends MongoRepository<UserInteractionModel, String> {
}
