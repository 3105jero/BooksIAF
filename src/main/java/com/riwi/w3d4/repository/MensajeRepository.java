package com.riwi.w3d4.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.riwi.w3d4.model.Mensaje;

@Repository
public interface MensajeRepository extends MongoRepository<Mensaje, String> {
}