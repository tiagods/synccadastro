package com.prolink.synccadastro.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prolink.synccadastro.model.Aniversariante;
import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.repository.Clientes;
import com.prolink.synccadastro.services.workbook.WorkbookServices;
import com.prolink.synccadastro.util.LocalDateConversor;

@Service
@PropertySource("classpath:arquivos.properties")
public class ClientesServices {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${origem.planilha}")
	private String origemPlanilha;
	@Value("${destino.planilha}")
	private String destinoPlanilha;

	@Autowired
	private Clientes clientes;
	@Autowired
	private WorkbookServices workbooks;
	@Autowired
	private LocalDateConversor conversores;

	public List<Cliente> listar() {
		return clientes.findAll();
	}

	@Transactional
	public void salvar(Cliente cliente) {
		clientes.save(cliente);
	}
	public void salvar(List<Cliente> cls) {
		//clientes.save(cls);
		for(Cliente c : cls) {
			if(c.getCOD()!=0) clientes.saveAndFlush(c);
		}
	}

	public void iniciarAtualizacao() {
		if (workbooks.validateExtension(origemPlanilha)) {
			try {
				workbooks.copyWorkbook(origemPlanilha, destinoPlanilha);
				List<Cliente> clientes = workbooks.readWorkbook(destinoPlanilha);
				workbooks.removeTempWorkbook(destinoPlanilha);
				salvar(clientes);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	public List<Aniversariante> getAniversariantes() {
		List<Aniversariante> aniversariantes = new ArrayList<>();
		List<LocalDate> lista = conversores.tratarDias();
		String[] a = new String[] { "PLATINA", "PRATA 2", "OURO 3", "OURO 2", "PRATA 3", "OURO 1", "BRONZE", "PRATA 1",
				"Exceção", "Inativa", "Em andamento" };
		List<String> filtroStatus = Arrays.asList(a);
		List<Cliente> clientes1 = clientes.listarAniversariantes(lista, filtroStatus, "DIA_NASC1","MES_NASC1");
		aniversariantes.addAll(processarAniversariantes1(clientes1));
		List<Cliente> clientes2 = clientes.listarAniversariantes(lista, filtroStatus,"DIA_NASC2","MES_NASC2");
		aniversariantes.addAll(processarAniversariantes2(clientes2));
		logger.info("Aniversariantes "+aniversariantes.size());
		
		Comparator<Aniversariante> comparator = new Comparator<Aniversariante>() {
			@Override
			public int compare(Aniversariante o1, Aniversariante o2) {
				return o1.getData().compareTo(o2.getData());
			}
		};
		Collections.sort(aniversariantes, comparator);
		return aniversariantes;
	}
	
	private List<Aniversariante> processarAniversariantes1(List<Cliente> clientes1) {
		List<Aniversariante> aniversariantes = new ArrayList<>();
		clientes1.forEach(c -> {
			int dia = Integer.parseInt(c.getDIA_NASC1());
			int mes = Integer.parseInt(conversores.convertMounth(c.getMES_NASC1()));
			LocalDate localDate = LocalDate.
					of(00, mes, dia);
			
			Aniversariante aniversariante = new Aniversariante();
			aniversariante.setId(String.valueOf(c.getCOD()));
			aniversariante.setStatus(c.getSTATUS());
			aniversariante.setEmpresa(c.getEMPRESA());
			aniversariante.setNome(c.getNOME_SOCIO1());
			aniversariante.setAniversario(dia+ "/" + mes);
			aniversariante.setEmail(c.getEMAIL_SOC_1());
			aniversariante.setData(localDate);
			String listaFones = "";
			if (c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
				listaFones = c.getDDD1CEL() + " " + c.getCELULAR();
			else if (!c.getFONECOML1().trim().equals("") && c.getCELULAR().trim().equals(""))
				listaFones = c.getDDD1COML() + " " + c.getFONECOML1();
			else if (!c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
				listaFones = c.getDDD1COML() + " " + c.getFONECOML1() + "," + c.getDDD1CEL() + " "
						+ c.getCELULAR();
			aniversariante.setTelefone(listaFones);
			aniversariante.setTipoSocio(1);
			aniversariantes.add(aniversariante);
		});
		return aniversariantes;
	}
	private List<Aniversariante> processarAniversariantes2(List<Cliente> clientes2) {
		List<Aniversariante> aniversariantes = new ArrayList<>();
		clientes2.forEach(c -> {
			int dia = Integer.parseInt(c.getDIA_NASC2());
			int mes = Integer.parseInt(conversores.convertMounth(c.getMES_NASC2()));
			LocalDate localDate = LocalDate.
					of(00, mes, dia);
			Aniversariante aniversariante = new Aniversariante();
			aniversariante.setId(String.valueOf(c.getCOD()));
			aniversariante.setStatus(c.getSTATUS());
			aniversariante.setEmpresa(c.getEMPRESA());
			aniversariante.setNome(c.getNOME_SOC_2());
			aniversariante.setAniversario(dia + "/" + mes);
			aniversariante.setEmail(c.getEMAIL_SOC_1());
			aniversariante.setTipoSocio(2);
			aniversariante.setData(localDate);
			String listaFones2 = "";
			if (c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
				listaFones2 = c.getDDD2CEL() + " " + c.getCELULAR2();
			else if (!c.getFONECOM2().trim().equals("") && c.getCELULAR2().trim().equals(""))
				listaFones2 = c.getDDD2COML() + " " + c.getFONECOM2();
			else if (!c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
				listaFones2 = c.getDDD2COML() + " " + c.getFONECOM2() + "," + c.getDDD2CEL() + " "
						+ c.getCELULAR2();
			aniversariante.setTelefone(listaFones2);
			aniversariantes.add(aniversariante);
		});
		return aniversariantes;
	}
	
}
