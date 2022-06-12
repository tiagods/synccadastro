package com.prolink.synccadastro.services;

import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.repository.Clientes;
import com.prolink.synccadastro.services.workbook.AbstractWorkbookFactory;
import com.prolink.synccadastro.services.workbook.WorkbookProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ClientesServices {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${planilha.origem}")
	private String origemPlanilha;
	@Value("${planilha.destino}")
	private String destinoPlanilha;
	@Value("${planilha.senha}")
	private String senha;
	@Autowired
	private Clientes clientes;
	@Autowired
	private ClientesServicesNs clienteNs;

	@Autowired
	private AbstractWorkbookFactory abstractWorkbookFactory;

	boolean unbloqued = true;
	@Async
	public CompletableFuture<List<Cliente>> listar() {
		logger.info("Listando todos os clientes");
		return CompletableFuture.completedFuture(clientes.findAll());
	}

	@Transactional
	public void salvar(Cliente cliente) {
		clientes.save(cliente);
	}

	@Async
	public void iniciarAtualizacao() {
		if (unbloqued) {
			unbloqued = false;
			try {
				WorkbookProcessor processor = abstractWorkbookFactory.getWorkbookService(Paths.get(origemPlanilha), Paths.get(destinoPlanilha), senha);
				Optional<Path> resultFile = processor.createTemporaryWorkbook();
				if(resultFile.isPresent()) {
					List<Cliente> cli = processor.readWorkbook(resultFile.get());
					processor.deleteTemporaryWorkbook();
					prepararPersistencia(cli);
					prepararPersistenciaBase2(cli);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				unbloqued = true;
			}
		}
	}
	@Async
	List<Cliente> prepararPersistencia(List<Cliente> clientesList) {
		logger.info("Salvando registros na base local: {}", clientesList.size());
		return clientes.saveAll(clientesList);
	}

	void prepararPersistenciaBase2(List<Cliente> clientesList) {
		logger.info("Salvando registros no nosql: {}", clientesList.size());
		try {
			clienteNs.atualizar(clientesList, false);
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Erro ao salvar registros no nosql");
		}
	}

	public List<Cliente> listarAniversariantes(List<LocalDate> lista, List<String> filtroStatus, String diaNasc, String mesNasc) {
		return clientes.listarAniversariantes(lista, filtroStatus, diaNasc, mesNasc);
	}
}