package com.prolink.synccadastro.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.model.ClienteNs;
import com.prolink.synccadastro.repository.ClientesNs;

import lombok.extern.slf4j.Slf4j;

@Service
public class ClientesServicesNs {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClientesNs clientesNs;
	private static Long ultimoRegistro = -1L;
	
	@Async
	public CompletableFuture<List<ClienteNs>> listar(){
		return CompletableFuture.completedFuture(clientesNs.findAll());
	}

	@Async
	public void atualizar(List<Cliente> clientesList,boolean atualizarTudo) throws InterruptedException, ExecutionException {
		logger.info("Iniciando atualizacao dos clientes no banco nosql");
		ultimoRegistro = clientesNs.pegarUltimoSalvo();
		List<ClienteNs> baseAtual = listar().get();
		List<ClienteNs> createList = new LinkedList<>();
		
		clientesList.forEach(c->{
			ClienteNs ns = convert(c);
			Optional<ClienteNs> opt = buscarRegistro(c, baseAtual);
			if(opt.isPresent() && atualizarTudo) {
				ns = opt.get();
				createList.add(ns);
			}
			//inserir novo
			else if(c.getCOD()>ultimoRegistro) {
				createList.add(ns);
			}
		});
		salvar(createList);
		logger.info("Concluido atualizacao dos clientes no banco nosql");
	}

	@Async
	private void salvar(List<ClienteNs> list) {
		logger.info("Saving a list of cliente (ns) of size {} records", list.size());
		clientesNs.saveAll(list);
	}
	
	//buscar registro na lista select.sql do nosql e comparar se é diferente
	private Optional<ClienteNs> buscarRegistro(Cliente c, List<ClienteNs> baseAtual){
		return baseAtual
				.parallelStream()
				.filter(f->f.getApelido().intValue()==c.getCOD())
				.findFirst();
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
