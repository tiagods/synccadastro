package com.prolink.synccadastro.resources;

import java.util.List;
import java.util.concurrent.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.services.ClientesServices;
import com.prolink.synccadastro.services.ClientesServicesNs;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

@RestController
@RequestMapping(value="/api/clientes")
public class ClientesResources {
	
	@Autowired
	private ClientesServices clientes;
	
	@Autowired
	private ClientesServicesNs clientesNs;
	
	
	@GetMapping(value="/aniversarios")
	ResponseEntity<?> aniversariantes(){
		return ResponseEntity.status(HttpStatus.OK).body(clientes.getAniversariantes());
	}
	
	@GetMapping(value="/atualizar")
	ResponseEntity atualizar() {
		clientes.iniciarAtualizacao()
				.subscribeOn(Schedulers.io())
				.subscribe();;
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping(value="/atualizar-ns")
	ResponseEntity<?> atualizarNs() throws InterruptedException, ExecutionException{
		CompletableFuture<List<Cliente>> list = clientes.listar();
		clientesNs.atualizar(list.get(), true);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
