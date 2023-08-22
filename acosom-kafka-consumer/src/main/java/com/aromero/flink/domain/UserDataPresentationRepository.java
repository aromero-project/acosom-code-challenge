package com.aromero.flink.domain;

import com.aromero.flink.domain.entity.UserDataPresentationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataPresentationRepository extends MongoRepository<UserDataPresentationModel, String> {
}
