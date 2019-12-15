package com.prolink.synccadastro.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.prolink.synccadastro.exception.ClienteNotFoundException;
import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.model.ClienteNs;
import com.prolink.synccadastro.repository.ClienteMongoRepository;

@Service
public class ClientesServicesNs {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private ClienteMongoRepository clientesRepository;
	
	public Long pegarUltimoSalvo() {
		Query query = new Query();
		query.with(Sort.by(Sort.Order.desc("apelido")));
		query.limit(1);
		ClienteNs cli = mongoTemplate.findOne(query,ClienteNs.class);
		if(cli == null) return 0L;
		else return cli.getApelido();
	}

	public void atualizar(List<Cliente> clientes, boolean atualizarTudo) {
		Long ultimoRegistro = pegarUltimoSalvo();
		List<ClienteNs> createList = new ArrayList<>();

		List<ClienteNs> baseAtual = atualizarTudo ? new ArrayList<>() : clientesRepository.findAll();
		clientes.forEach(c->{
			//inserir novo
			if(c.getCOD()>ultimoRegistro) {
				createList.add(convert(c));
			}
			//atualizar status
			else {
				if(baseAtual.isEmpty()) {
					update(c);
				}
				else {
					try {
						//verificando se registro esta na base
						if(buscarRegistro(c, baseAtual)) 
							update(c);
					}catch(ClienteNotFoundException e) {
						ClienteNs ns = convert(c);
						clientesRepository.save(ns);
					}
				}
			}
		});
		Collections.sort(createList, Comparator.comparingLong(ClienteNs::getApelido));
		save(createList);
	}
	//buscar registro na lista select do nosql e comparar se é diferente
	private boolean buscarRegistro(Cliente c, List<ClienteNs> baseAtual) throws ClienteNotFoundException{
		Optional<ClienteNs> ns = baseAtual.parallelStream()
				.filter(f->f.getApelido().intValue()==c.getCOD()).findFirst();
		ClienteNs resultConvert = convert(c);
		if(!ns.isPresent()) throw new ClienteNotFoundException("Cliente não existe");
		else return(!ns.get().equals(resultConvert));
	}
	
	public void save(List<ClienteNs> list) {
		clientesRepository.saveAll(list);
	}
	
	private void update(Cliente cli) {
		Query query = new Query();
        query.addCriteria(Criteria.where("apelido")
        		.is(cli.getCOD()));
        Update update = new Update();
        update.set("status", cli.getSTATUS());
        update.set("nome", cli.getEMPRESA());
        update.set("cnpj", cli.getCNPJ());
        update.set("data", new Date());
        mongoTemplate.findAndModify(query, update, ClienteNs.class);
	}
	private ClienteNs convert(Cliente cli) {
		ClienteNs ns = new ClienteNs();
		ns.setApelido((long) cli.getCOD());
		ns.setCnpj(cli.getCNPJ());
		ns.setData(new Date());
		ns.setFolderCreate(false);
		ns.setNome(cli.getEMPRESA());
		ns.setStatus(cli.getSTATUS());
		return ns;
	}
}
