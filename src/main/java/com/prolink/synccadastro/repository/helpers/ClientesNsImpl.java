package com.prolink.synccadastro.repository.helpers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.prolink.synccadastro.model.ClienteNs;

public class ClientesNsImpl {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Long pegarUltimoSalvo() {
		Query query = new Query();
		query.with(Sort.by(Sort.Order.desc("apelido")));
		query.limit(1);
		ClienteNs cli = mongoTemplate.findOne(query,ClienteNs.class);
		if(cli == null) return 0L;
		else return cli.getApelido();
	}

	public void update(ClienteNs cli) {
		Query query = new Query();
        query.addCriteria(Criteria.where("apelido")
        		.is(cli.getApelido()));
        Update update = new Update();
        update.set("status", cli.getStatus());
        update.set("nome", cli.getNome());
        update.set("cnpj", cli.getCnpj());
        update.set("data", new Date());
        mongoTemplate.findAndModify(query, update, ClienteNs.class);
	}
}
