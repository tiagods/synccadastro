package com.prolink.synccadastro.services.workbook;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prolink.synccadastro.model.Aniversariante;
import com.prolink.synccadastro.util.ExcelGenerico;

import jxl.write.WriteException;

@Service
public class ExcelGenerator {
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public File gerarExcel(Iterator<Aniversariante> iterator){
		ArrayList<ArrayList<String>> listaImpressao = new ArrayList<>();
		//SimpleDateFormat sdn = new SimpleDateFormat("ddMMyyyy");
		Integer[] colunasLenght = new Integer[]{10,40,10,30,30,18,30,15};
		String[] cabecalho = new String[]{
				"DIA","NOME","SOCIO","TELEFONE","E-MAIL","ID_CLIENTE",
				"NOME_CLIENTE","STATUS"};
		listaImpressao.add(new ArrayList<>());
		for(String c : cabecalho){
			listaImpressao.get(0).add(c);
		}
		int i=1;
		while(iterator.hasNext()){
			Aniversariante an = iterator.next();
			listaImpressao.add(new ArrayList<>());
			listaImpressao.get(i).add(an.getAniversario());
			listaImpressao.get(i).add(an.getNome());
			listaImpressao.get(i).add(""+an.getTipoSocio());
			listaImpressao.get(i).add(an.getTelefone());
			listaImpressao.get(i).add(an.getEmail());
			listaImpressao.get(i).add(an.getId());
			listaImpressao.get(i).add(an.getEmpresa());
			listaImpressao.get(i).add(an.getStatus());
			i++;
		}
		String arquivoGerado=System.getProperty("java.io.tmpdir")+"/lista"+sdf.format(new Date())+".xls";
		ExcelGenerico planilha = new ExcelGenerico(arquivoGerado,listaImpressao,colunasLenght);
		try {
			planilha.gerarExcel();
			return new File(arquivoGerado);
		} catch (WriteException e1) {
			logger.error(e1.getMessage());
			return null;
		} catch (IOException e2) {
			logger.error(e2.getMessage());
			return null;
		}
	}
}
