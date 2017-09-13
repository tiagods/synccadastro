package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;

import factory.ConnectionFactory;
import factory.HibernateFactory;

public class CadastroDao {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	long tempoDeLeitura;
	long tempoGravacaoBD;
	static CadastroDao instance;
	private StringBuilder builder;
	private List<ComentarioBean> listaComentarios;
	
	public static CadastroDao getInstance(){
		if(instance==null){
			instance= new CadastroDao();
		}
		return instance;
	}
	
	public List<ComentarioBean> getComentarios(){
		return listaComentarios;
	}
	
	public List<CadastroBean> readWorkbook(String caminho) throws IOException{
		File file = new File(caminho);
		//informando a senha de descriptografia
		Biff8EncryptionKey.setCurrentUserPassword("PLKCONTRATOS");
		@SuppressWarnings("resource")
		NPOIFSFileSystem fs = new NPOIFSFileSystem(file, true);
		HSSFWorkbook workbook = new HSSFWorkbook(fs.getRoot(), true);
		//removendo senha para leitura
		Biff8EncryptionKey.setCurrentUserPassword(null);

		HSSFSheet sheet = workbook.getSheetAt(0);
		
		long inicio = System.currentTimeMillis();
		List<CadastroBean> lista = new ArrayList<>();
		listaComentarios = new ArrayList<>();
		builder = new StringBuilder();
		//pegando linhas e jogando no iterator

		Iterator<Row> linha = sheet.rowIterator();
		boolean stop = false;//parar cas
		while(linha.hasNext()){
			if(stop) 
				break;//parar caso codigo seja vazio ou 0
			HSSFRow row = (HSSFRow)linha.next();
			if(row==null) 
				break;
			if(row.getRowNum()==0) 
				continue;//pular a 1a linha
			CadastroBean bean = new CadastroBean();
			ComentarioBean com = new ComentarioBean();
			//iterando sobre as colunas da linha
			Iterator<Cell> columns = row.cellIterator();
			while(columns.hasNext()){
				HSSFCell celula = (HSSFCell)columns.next();
				if(row.getRowNum()!=0 && celula.getColumnIndex()==0){
					try{
						Integer.parseInt(readingCell(celula).trim());//se não conseguir o cast, o campo codigo estara em branco, nesse caso o for ira fechar
					}catch(NumberFormatException e){
						stop=true;
						break;
					}
				}
				HSSFComment comment = celula.getCellComment();
				String comentario ="";
				if(comment!=null){
					RichTextString rts = comment.getString();
					comentario = rts.getString().trim();
				}
				switch(celula.getColumnIndex()){//retorna o numero das colunas
				case 0:
					bean.setCOD(Integer.parseInt(readingCell(celula).trim()));
					com.setCOD(Integer.parseInt(readingCell(celula).trim()));
					com.setCOD_COMMENT(comentario);
					break;
				case 1:
					bean.setSTATUS(readingCell(celula).trim());
					com.setSTATUS(comentario);
					break;
				case 2:
					bean.setCOMPL_STS(readingCell(celula).trim());
					com.setCOMPL_STS(comentario);
					break;
				case 3:
					bean.setATENDIMENTO(readingCell(celula).trim());
					com.setATENDIMENTO(comentario);
					break;
				case 4:
					bean.setPROCESSOS(readingCell(celula).trim());
					com.setPROCESSOS(comentario);
					break;
				case 5:
					bean.setSISTEMA(readingCell(celula).trim());
					com.setSISTEMA(comentario);
					break;
				case 6:
					bean.setMP(readingCell(celula).trim());
					com.setMP(comentario);
					break;
				case 7:
					bean.setTIPO(readingCell(celula).trim());
					com.setTIPO(comentario);
					break;
				case 8:
					bean.setREGIME_TRIBUTARIO(readingCell(celula).trim());
					com.setREGIME_TRIBUTARIO(comentario);
					break;
				case 9:
					bean.setQUANT_SOC_GER(readingCell(celula).trim());
					com.setQUANT_SOC_GER(comentario);
					break;
				case 10:
					bean.setEMPRESA(readingCell(celula).trim());
					com.setEMPRESA(comentario);
					break;
				case 11:
					bean.setNOME(readingCell(celula).trim());
					com.setNOME(comentario);
					break;
				case 12:
					bean.setENDERECO(readingCell(celula).trim());
					com.setENDERECO(comentario);
					break;
				case 13:
					bean.setBAIRRO(readingCell(celula).trim());
					com.setBAIRRO(comentario);
					break;
				case 14:
					bean.setCIDADE(readingCell(celula).trim());
					com.setCIDADE(comentario);
					break;
				case 15:
					bean.setEST(readingCell(celula).trim());
					com.setEST(comentario);
					break;
				case 16:
					bean.setCEP(readingCell(celula).trim());
					com.setCEP(comentario);
					break;
				case 17:
					bean.setCNPJ(readingCell(celula).trim());
					com.setCNPJ(comentario);
					break;
				case 18:
					bean.setDATA_CNPJ(readingCell(celula).trim());
					com.setDATA_CNPJ(comentario);
					break;
				case 19:
					bean.setIE(readingCell(celula).trim());
					com.setIE(comentario);
					break;
				case 20:
					bean.setVLR_CAPITAL(readingCell(celula).trim());
					com.setVLR_CAPITAL(comentario);
					break;
				case 21:
					bean.setVL_EXTENSO(readingCell(celula).trim());
					com.setVL_EXTENSO(comentario);
					break;
				case 22:
					bean.setVL_COTA(readingCell(celula).trim());
					com.setVL_COTA(comentario);
					break;
				case 23:
					bean.setVL_EXT_COTA(readingCell(celula).trim());
					com.setVL_EXT_COTA(comentario);
					break;
				case 24:
					bean.setCOTAS_TOTAL(readingCell(celula).trim());
					com.setCOTAS_TOTAL(comentario);
					break;
				case 25:
					bean.setCOTAS_EXTENSO(readingCell(celula).trim());
					com.setCOTAS_EXTENSO(comentario);
					break;
				case 26:
					bean.setPERC_CAP_1(readingCell(celula).trim());
					com.setPERC_CAP_1(comentario);
					break;
				case 27:
					bean.setPERC_CAP_2(readingCell(celula).trim());
					com.setPERC_CAP_2(comentario);
					break;
				case 28:
					bean.setPERC_CAP_DEMAIS(readingCell(celula).trim());
					com.setPERC_CAP_DEMAIS(comentario);
					break;
				case 29:
					bean.setSOMA_PERC_CAP(readingCell(celula).trim());
					com.setSOMA_PERC_CAP(comentario);
					break;
				case 30:
					bean.setCOTAS_01(readingCell(celula).trim());
					com.setCOTAS_01(comentario);
					break;
				case 31:
					bean.setCOTAS_02(readingCell(celula).trim());
					com.setCOTAS_02(comentario);
					break;
				case 32:
					bean.setPREF(readingCell(celula).trim());
					com.setPREF(comentario);
					break;
				case 33:
					bean.setDATA_IM(readingCell(celula).trim());
					com.setDATA_IM(comentario);
					break;
				case 34:
					bean.setN_CONTRIB(readingCell(celula).trim());
					com.setN_CONTRIB(comentario);
					break;
				case 35:
					bean.setALIQUOTA_DE_ISS(readingCell(celula).trim());
					com.setALIQUOTA_DE_ISS(comentario);
					break;
				case 36:
					bean.setENC_MUNIC(readingCell(celula).trim());
					com.setENC_MUNIC(comentario);
					break;
				case 37:
					bean.setDT_DISTRATO(readingCell(celula).trim());
					com.setDT_DISTRATO(comentario);
					break;
				case 38:
					bean.setNIRC(readingCell(celula).trim());
					com.setNIRC(comentario);
					break;
				case 39:
					bean.setN_REG_CART_OU_NIRE(readingCell(celula).trim());
					com.setN_REG_CART_OU_NIRE(comentario);
					break;
				case 40:
					bean.setDATA_REG_CART_OU_JUCESP(readingCell(celula).trim());
					com.setDATA_REG_CART_OU_JUCESP(comentario);
					break;
				case 41:
					bean.setSIND_CNPJ(readingCell(celula).trim());
					com.setSIND_CNPJ(comentario);
					break;
				case 42:
					bean.setATIVIDADE(readingCell(celula).trim());
					com.setATIVIDADE(comentario);
					break;
				case 43:
					bean.setSINDICATO(readingCell(celula).trim());
					com.setSINDICATO(comentario);
					break;
				case 44:
					bean.setCNAE1(readingCell(celula).trim());
					com.setCNAE1(comentario);
					break;
				case 45:
					bean.setCOMP_CNAE1(readingCell(celula).trim());
					com.setCOMP_CNAE1(comentario);
					break;
				case 46:
					bean.setDIG_CNAE1(readingCell(celula).trim());
					com.setDIG_CNAE1(comentario);
					break;
				case 47:
					bean.setCNAE(readingCell(celula).trim());
					com.setCNAE(comentario);
					break;
				case 48:
					bean.setCOMP_CNAE(readingCell(celula).trim());
					com.setCOMP_CNAE(comentario);
					break;
				case 49:
					bean.setDIG_CNAE(readingCell(celula).trim());
					com.setDIG_CNAE(comentario);
					break;
				case 50:
					bean.setFPAS(readingCell(celula).trim());
					com.setFPAS(comentario);
					break;
				case 51:
					bean.setQUANT_SOCIOS(readingCell(celula).trim());
					com.setQUANT_SOCIOS(comentario);
					break;
				case 52:
					bean.setDATA_INSS(readingCell(celula).trim());
					com.setDATA_INSS(comentario);
					break;
				case 53:
					bean.setCONSULTORIA_INICIO(readingCell(celula).trim());
					com.setCONSULTORIA_INICIO(comentario);
					break;
				case 54:
					bean.setALOCADO(readingCell(celula).trim());
					com.setALOCADO(comentario);
					break;
				case 55:
					bean.setINDICADO(readingCell(celula).trim());
					com.setINDICADO(comentario);
					break;
				case 56:
					bean.setCONSULT_ATUAL(readingCell(celula).trim());
					com.setCONSULT_ATUAL(comentario);
					break;
				case 57:
					bean.setAPELIDO_1(readingCell(celula).trim());
					com.setAPELIDO_1(comentario);
					break;
				case 58:
					bean.setEND_CORRESP(readingCell(celula).trim());
					com.setEND_CORRESP(comentario);
					break;
				case 59:
					bean.setBAIRRO_CO(readingCell(celula).trim());
					com.setBAIRRO_CO(comentario);
					break;
				case 60:
					bean.setCID_CO(readingCell(celula).trim());
					com.setCID_CO(comentario);
					break;
				case 61:
					bean.setEST_CO(readingCell(celula).trim());
					com.setEST_CO(comentario);
					break;
				case 62:
					bean.setCEP_CO(readingCell(celula).trim());
					com.setCEP_CO(comentario);
					break;
				case 63:
					bean.setNOME_SOCIO1(readingCell(celula).trim());
					com.setNOME_SOCIO1(comentario);
					break;
				case 64:
					bean.setQUALIDADE1(readingCell(celula).trim());
					com.setQUALIDADE1(comentario);
					break;
				case 65:
					bean.setNAC_01(readingCell(celula).trim());
					com.setNAC_01(comentario);
					break;
				case 66:
					bean.setNATURALIDADE_1(readingCell(celula).trim());
					com.setNATURALIDADE_1(comentario);
					break;
				case 67:
					bean.setEST_CIVEL_01(readingCell(celula).trim());
					com.setEST_CIVEL_01(comentario);
					break;
				case 68:
					bean.setMAIOR_EMANCIPADO_1(readingCell(celula).trim());
					com.setMAIOR_EMANCIPADO_1(comentario);
					break;
				case 69:
					bean.setPROF_01(readingCell(celula).trim());
					com.setPROF_01(comentario);
					break;
				case 70:
					bean.setDIA_NASC1(readingCell(celula).trim());
					com.setDIA_NASC1(comentario);
					break;
				case 71:
					bean.setMES_NASC1(readingCell(celula).trim());
					com.setMES_NASC1(comentario);
					break;
				case 72:
					bean.setANO_NASC1(readingCell(celula).trim());
					com.setANO_NASC1(comentario);
					break;
				case 73:
					bean.setPIS_SOC1(readingCell(celula).trim());
					com.setPIS_SOC1(comentario);
					break;
				case 74:
					bean.setEND_SOC_1(readingCell(celula).trim());
					com.setEND_SOC_1(comentario);
					break;
				case 75:
					bean.setBAIRRO1(readingCell(celula).trim());
					com.setBAIRRO1(comentario);
					break;
				case 76:
					bean.setCIDADE1(readingCell(celula).trim());
					com.setCIDADE1(comentario);
					break;
				case 77:
					bean.setEST1(readingCell(celula).trim());
					com.setEST1(comentario);
					break;
				case 78:
					bean.setCEP1(readingCell(celula).trim());
					com.setCEP1(comentario);
					break;
				case 79:
					bean.setRG1(readingCell(celula).trim());
					com.setRG1(comentario);
					break;
				case 80:
					bean.setORGAO1(readingCell(celula).trim());
					com.setORGAO1(comentario);
					break;
				case 81:
					bean.setESTD1(readingCell(celula).trim());
					com.setESTD1(comentario);
					break;
				case 82:
					bean.setDATA_EXP1(readingCell(celula).trim());
					com.setDATA_EXP1(comentario);
					break;
				case 83:
					bean.setCPF1(readingCell(celula).trim());
					com.setCPF1(comentario);
					break;
				case 84:
					bean.setEMAIL_SOC_1(readingCell(celula).trim());
					com.setEMAIL_SOC_1(comentario);
					break;
				case 85:
					bean.setEMAIL_FINANCEIRO(readingCell(celula).trim());
					com.setEMAIL_FINANCEIRO(comentario);
					break;
				case 86:
					bean.setDDD1(readingCell(celula).trim());
					com.setDDD1(comentario);
					break;
				case 87:
					bean.setFONERES1(readingCell(celula).trim());
					com.setFONERES1(comentario);
					break;
				case 88:
					bean.setRES_RAMAL_1(readingCell(celula).trim());
					com.setRES_RAMAL_1(comentario);
					break;
				case 89:
					bean.setDDD1COML(readingCell(celula).trim());
					com.setDDD1COML(comentario);
					break;
				case 90:
					bean.setFONECOML1(readingCell(celula).trim());
					com.setFONECOML1(comentario);
					break;
				case 91:
					bean.setCOML_RAMAL_1(readingCell(celula).trim());
					com.setCOML_RAMAL_1(comentario);
					break;
				case 92:
					bean.setDDD1CEL(readingCell(celula).trim());
					com.setDDD1CEL(comentario);
					break;
				case 93:
					bean.setCELULAR(readingCell(celula).trim());
					com.setCELULAR(comentario);
					break;
				case 94:
					bean.setDDD1REC(readingCell(celula).trim());
					com.setDDD1REC(comentario);
					break;
				case 95:
					bean.setFONEREC1(readingCell(celula).trim());
					com.setFONEREC1(comentario);
					break;
				case 96:
					bean.setOBSFONE_REC1(readingCell(celula).trim());
					com.setOBSFONE_REC1(comentario);
					break;
				case 97:
					bean.setNOME_SOC_2(readingCell(celula).trim());
					com.setNOME_SOC_2(comentario);
					break;
				case 98:
					bean.setQUALIDADE2(readingCell(celula).trim());
					com.setQUALIDADE2(comentario);
					break;
				case 99:
					bean.setNAC_02(readingCell(celula).trim());
					com.setNAC_02(comentario);
					break;
				case 100:
					bean.setNATURALIDADE_2(readingCell(celula).trim());
					com.setNATURALIDADE_2(comentario);
					break;
				case 101:
					bean.setEST_CIVEL_02(readingCell(celula).trim());
					com.setEST_CIVEL_02(comentario);
					break;
				case 102:
					bean.setMAIOR_EMANCIPADO_2(readingCell(celula).trim());
					com.setMAIOR_EMANCIPADO_2(comentario);
					break;
				case 103:
					bean.setPROF_02(readingCell(celula).trim());
					com.setPROF_02(comentario);
					break;
				case 104:
					bean.setEND_SOC_2(readingCell(celula).trim());
					com.setEND_SOC_2(comentario);
					break;
				case 105:
					bean.setBAIRRO2(readingCell(celula).trim());
					com.setBAIRRO2(comentario);
					break;
				case 106:
					bean.setCIDADE2(readingCell(celula).trim());
					com.setCIDADE2(comentario);
					break;
				case 107:
					bean.setEST2(readingCell(celula).trim());
					com.setEST2(comentario);
					break;
				case 108:
					bean.setCEP2(readingCell(celula).trim());
					com.setCEP2(comentario);
					break;
				case 109:
					bean.setDIA_NASC2(readingCell(celula).trim());
					com.setDIA_NASC2(comentario);
					break;
				case 110:
					bean.setMES_NASC2(readingCell(celula).trim());
					com.setMES_NASC2(comentario);
					break;
				case 111:
					bean.setANO_NASC2(readingCell(celula).trim());
					com.setANO_NASC2(comentario);
					break;
				case 112:
					bean.setPIS_SOC2(readingCell(celula).trim());
					com.setPIS_SOC2(comentario);
					break;
				case 113:
					bean.setRG2(readingCell(celula).trim());
					com.setRG2(comentario);
					break;
				case 114:
					bean.setORGAO2(readingCell(celula).trim());
					com.setORGAO2(comentario);
					break;
				case 115:
					bean.setESTD2(readingCell(celula).trim());
					com.setESTD2(comentario);
					break;
				case 116:
					bean.setDATA_EXP2(readingCell(celula).trim());
					com.setDATA_EXP2(comentario);
					break;
				case 117:
					bean.setCPF2(readingCell(celula).trim());
					com.setCPF2(comentario);
					break;
				case 118:
					bean.setDDD2RES(readingCell(celula).trim());
					com.setDDD2RES(comentario);
					break;
				case 119:
					bean.setFONERES2(readingCell(celula).trim());
					com.setFONERES2(comentario);
					break;
				case 120:
					bean.setDDD2COML(readingCell(celula).trim());
					com.setDDD2COML(comentario);
					break;
				case 121:
					bean.setFONECOM2(readingCell(celula).trim());
					com.setFONECOM2(comentario);
					break;
				case 122:
					bean.setDDD2CEL(readingCell(celula).trim());
					com.setDDD2CEL(comentario);
					break;
				case 123:
					bean.setCELULAR2(readingCell(celula).trim());
					com.setCELULAR2(comentario);
					break;
				case 124:
					bean.setDDD2REC(readingCell(celula).trim());
					com.setDDD2REC(comentario);
					break;
				case 125:
					bean.setFONEREC2(readingCell(celula).trim());
					com.setFONEREC2(comentario);
					break;
				case 126:
					bean.setOBSFONE_RC2(readingCell(celula).trim());
					com.setOBSFONE_RC2(comentario);
					break;
				default:
					break;
				}
			}
			listaComentarios.add(com);
			lista.add(bean);
		}
		try {
			workbook.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		long fim = System.currentTimeMillis();
		tempoDeLeitura = fim - inicio;
		System.out.println("Tempo total: "+(fim-inicio)+"\nTamanho da Lista: "+lista.size());
		return lista;
	}
	private String readingCell(HSSFCell celula){ //metodo usado para tratar as celulas
		switch (celula.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				if(DateUtil.isCellDateFormatted(celula))
					return sdf.format(celula.getDateCellValue());//campo do tipo data formatando no ato
				else{
					return String.valueOf((long)celula.getNumericCellValue());//campo do tipo numerico, convertendo double para long
				}
			case HSSFCell.CELL_TYPE_STRING:
				return String.valueOf(celula.getStringCellValue());//conteudo do tipo texto
			case HSSFCell.CELL_TYPE_BOOLEAN:    
				return "";
			case HSSFCell.CELL_TYPE_BLANK:
				return "";
			default:
				return "";
		}
    }
	@SuppressWarnings("resource")
	public boolean copyWorkbook(ConfExtraBean cb){
//copiando arquivo para um local temporario
		FileInputStream local = null;
		FileOutputStream destino= null;
		FileChannel entrada=null;
		FileChannel saida=null;
		try{
			local = new FileInputStream(cb.getPLANILHA_LOCALIZACAO()+"/"+cb.getPLANILHA_NOME());
			destino = new FileOutputStream(cb.getDIRETORIO_TEMP()+"/"+cb.getPLANILHA_NOME());
			entrada = local.getChannel();
			saida = destino.getChannel();
			entrada.transferTo(0, entrada.size(), saida);
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}finally{
				try {entrada.close();saida.close();
				} catch (IOException e) {
					return false;}
		}
		
	}
	//validando extensao xls do arquivo
	public boolean validateExtension(ConfExtraBean cb){
		if(copyWorkbook(cb)){
			File file = new File(cb.getDIRETORIO_TEMP()+"/"+cb.getPLANILHA_NOME());
			int pos = file.toString().lastIndexOf(".");
	        String extensao = file.toString().substring(pos + 1);
	        if(extensao.equals("xls")){
		        return true;
	        }
		}
		return false;
	}
//deletar arquivo temporario
	public void removeTempWorkbook(ConfExtraBean cb){
		File[] files = new File(cb.getDIRETORIO_TEMP()).listFiles();
		for(File f : files){
			try{
				f.delete();
			}catch(Exception e){
			}
		}
	}
//atualizando dados
	public String insertOrUpdate(List<CadastroBean> lista){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		long inicio = System.currentTimeMillis();
		for(int i=0; i<lista.size(); i++){
			CadastroBean bean = lista.get(i);
			if(bean.getCOD()==0) 
				continue;
			String resultado = factory.saveOrUpdateSession(session, bean);
			if(!resultado.equals("Salvo"))
			{
				builder.append(bean.getCOD()+" = "+resultado+"\n");
				builder.append(System.getProperty("line.separator"));
			}
			if(i % 100 == 0){
				session.flush();
				session.clear();
			}
		}
		
		Connection con = new ConnectionFactory().getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("delete from cliente");
			ps.executeUpdate();
			lista.forEach(c->{
				try{
					ps.clearParameters();
					String sqlCommand = "insert into cliente (id,nome,status) values (?,?,?)";
					PreparedStatement p = con.prepareStatement(sqlCommand);
					p.setInt(1,c.getCOD());
					p.setString(2,c.getEMPRESA());
					p.setString(3,c.getSTATUS());
					p.executeUpdate();
				}catch(SQLException e){
					e.printStackTrace();
				}
			});
		}catch(SQLException e){
			try{con.close();}catch(SQLException sql){}
		}
		
		long fim = System.currentTimeMillis();
		tempoGravacaoBD = fim - inicio;
		factory.closeSession(session);
		System.out.println("Terminei");
		return builder.toString();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CadastroBean> readClients(){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		List object = factory.getList(session, "CadastroBean");
		factory.closeSession(session);
		return (List<CadastroBean>)object;
	}
	//criar um arquivo de log
	public String  createTxtLogFile(File file, StringBuilder builder) throws IOException{
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyy-HHmm");
		File f = new File(file.getAbsolutePath()+"/"+sdf.format(new Date())+".txt");
		f.createNewFile();
		FileWriter fWriter = new FileWriter(f, true);
		fWriter.write(builder.toString());
		fWriter.close();
		return f.getAbsolutePath();
	}
	public String getErros(){
		return builder.toString();
	}
	public long getTempoLeitura(){
		return this.tempoDeLeitura;
	}
	public long getTempoGravacao(){
		return this.tempoGravacaoBD;
	}
}
