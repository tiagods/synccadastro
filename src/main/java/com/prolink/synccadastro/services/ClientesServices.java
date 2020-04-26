package com.prolink.synccadastro.services;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prolink.synccadastro.model.Aniversariante;
import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.repository.Clientes;
import com.prolink.synccadastro.util.LocalDateConversor;
import com.prolink.synccadastro.util.UtilWorkbook;

@Service
public class ClientesServices {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${planilha.origem}")
	private String origemPlanilha;
	@Value("${planilha.destino}")
	private String destinoPlanilha;

	@Autowired
	private Clientes clientes;
	@Autowired
	private ClientesServicesNs clienteNs;
	@Autowired
	private UtilWorkbook workbooks;
	@Autowired
	private LocalDateConversor conversores;

	private final List<String> filtroStatus = Arrays.asList("PLATINA", "PRATA 2", "OURO 3", "OURO 2", "PRATA 3",
			"OURO 1", "BRONZE", "PRATA 1", "Exceção", "Inativa", "Em andamento");

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
		synchronized (workbooks) {
			if(validarExtensao()) {
				copiarPlanilha()
					.ifPresent(c->{
						List<Cliente> cli = prepararPersistencia(lerDocumento(c));
						prepararPersistenciaBase2(cli);
					});
			}
		}
	}
	
	private boolean validarExtensao() {
		if(workbooks.validateExtension(origemPlanilha)) return true;
		else {
			logger.error("Extensao de documento invalida: " + origemPlanilha);
			return false;
		}
	}

	private Optional<String> copiarPlanilha(){
		File diretorio = new File(destinoPlanilha);
		if (!diretorio.exists()) {
			logger.info("Criando diretorio: {}", diretorio);
			diretorio.mkdir();
		}
		String valor = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
		String novaPlanilha = destinoPlanilha + "/Cadastro" + valor + ".xls";
		workbooks.copyWorkbook(origemPlanilha, novaPlanilha);
		logger.info("Criando planilha: " + novaPlanilha);
		return Optional.of(novaPlanilha);
	}
	
	private List<Cliente> lerDocumento(String novaPlanilha){
		final long start = System.currentTimeMillis();
		List<Cliente> clientesList = new LinkedList<>();
		logger.info("Lendo planilha temporaria: " + novaPlanilha);
		Optional.ofNullable(workbooks.readWorkbook(novaPlanilha)).ifPresent(clientesList::addAll);
		logger.info("Tempo de Leitura: {}", System.currentTimeMillis() - start);
		clientesList.stream().filter(c -> c.getCOD() == 0).findFirst().ifPresent(clientesList::remove);
		logger.info("Removendo planilha temporaria: " + novaPlanilha);
		workbooks.removeTempWorkbook(destinoPlanilha);
		return clientesList;
	}
	@Async
	private List<Cliente> prepararPersistencia(List<Cliente> clientesList) {
		logger.info("Salvando registros na base local: {}", clientesList.size());
		return clientes.saveAll(clientesList);
	}

	private void prepararPersistenciaBase2(List<Cliente> clientesList) {
		logger.info("Salvando registros no nosql: {}", clientesList.size());
		try {
			clienteNs.atualizar(clientesList, false);
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Erro ao salvar registros no nosql");
		}
	}

	public List<Aniversariante> getAniversariantes() {
		List<Aniversariante> aniversariantes = new ArrayList<>();
		List<LocalDate> lista = conversores.tratarDias();

		Stream.of(
				processarAniversariantes(Aniversariante.Socio.SOCIO1,
						clientes.listarAniversariantes(lista, filtroStatus, "DIA_NASC1", "MES_NASC1")),
				processarAniversariantes(Aniversariante.Socio.SOCIO2,
						clientes.listarAniversariantes(lista, filtroStatus, "DIA_NASC2", "MES_NASC2")))
				.forEach(aniversariantes::addAll);

		logger.info("Aniversariantes " + aniversariantes.size());
		Comparator<Aniversariante> comparator = Comparator.comparing(Aniversariante::getData);
		Collections.sort(aniversariantes, comparator.thenComparing(c -> c.getId()).thenComparing(c -> c.getStatus()));
		return aniversariantes;
	}

	private List<Aniversariante> processarAniversariantes(Aniversariante.Socio socio, List<Cliente> clientes) {
		List<Aniversariante> aniversariantes = new ArrayList<>();
		clientes.forEach(c -> {
			Aniversariante aniversariante = new Aniversariante();
			aniversariante.setId(String.valueOf(c.getCOD()));
			aniversariante.setStatus(c.getSTATUS());
			aniversariante.setEmpresa(c.getEMPRESA());
			aniversariante.setTipoSocio(socio.getValor());
			aniversariante.setEmail(c.getEMAIL_SOC_1());

			LocalDate localDate = null;
			if (socio.equals(Aniversariante.Socio.SOCIO1)) {
				localDate = recuperarData(c.getDIA_NASC1(), conversores.convertMounth(c.getMES_NASC1()));
				aniversariante.setNome(c.getNOME_SOCIO1());
				aniversariante.setTelefone(montarTelefone1(c));
			} else {
				localDate = recuperarData(c.getDIA_NASC2(), conversores.convertMounth(c.getMES_NASC2()));
				aniversariante.setNome(c.getNOME_SOC_2());
				aniversariante.setTelefone(montarTelefone2(c));
			}
			aniversariante.setAniversario(conversores.fomatarMD(localDate));
			aniversariante.setData(localDate);
			aniversariantes.add(aniversariante);
		});
		return aniversariantes;
	}

	public LocalDate recuperarData(String dia, String mes) {
		int diaR = Integer.parseInt(dia);
		int mesR = Integer.parseInt(conversores.convertMounth(mes));
		return LocalDate.of(00, mesR, diaR);
	}

	private String montarTelefone1(Cliente c) {
		String listaFones = "";
		if (c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
			listaFones = c.getDDD1CEL() + " " + c.getCELULAR();
		else if (!c.getFONECOML1().trim().equals("") && c.getCELULAR().trim().equals(""))
			listaFones = c.getDDD1COML() + " " + c.getFONECOML1();
		else if (!c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
			listaFones = c.getDDD1COML() + " " + c.getFONECOML1() + "," + c.getDDD1CEL() + " " + c.getCELULAR();
		return listaFones;
	}

	private String montarTelefone2(Cliente c) {
		String listaFones2 = "";
		if (c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
			listaFones2 = c.getDDD2CEL() + " " + c.getCELULAR2();
		else if (!c.getFONECOM2().trim().equals("") && c.getCELULAR2().trim().equals(""))
			listaFones2 = c.getDDD2COML() + " " + c.getFONECOM2();
		else if (!c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
			listaFones2 = c.getDDD2COML() + " " + c.getFONECOM2() + "," + c.getDDD2CEL() + " " + c.getCELULAR2();
		return listaFones2;
	}
}
