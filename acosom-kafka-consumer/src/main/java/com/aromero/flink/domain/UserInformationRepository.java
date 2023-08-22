package com.aromero.flink.domain;

import com.aromero.flink.domain.entity.UserInformationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserInformationRepository extends MongoRepository<UserInformationModel, String> {
}
