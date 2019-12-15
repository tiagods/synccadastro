package com.prolink.synccadastro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.prolink.synccadastro.model.ClienteNs;

@Repository
public interface ClienteMongoRepository extends MongoRepository<ClienteNs, String>{
	ClienteNs findByApelido(Integer apelido);
}
