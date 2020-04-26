package com.prolink.synccadastro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.prolink.synccadastro.model.ClienteNs;

@Repository
public interface ClientesNs extends MongoRepository<ClienteNs, String>{
	Long pegarUltimoSalvo();
	void save(List<ClienteNs> lista);
	void update(ClienteNs cli);
	Optional<ClienteNs> findByApelido(Long apelido);
}
