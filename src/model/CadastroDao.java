package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;

import factory.HibernateFactory;

public class CadastroDao {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	long tempo;
	
	public List<CadastroBean> readWorkbook(String caminho) throws IOException{
		
		File file = new File(caminho);
		Biff8EncryptionKey.setCurrentUserPassword("PLKCONTRATOS");
		@SuppressWarnings("resource")
		NPOIFSFileSystem fs = new NPOIFSFileSystem(file, true);
		HSSFWorkbook workbook = new HSSFWorkbook(fs.getRoot(), true);
		
		Biff8EncryptionKey.setCurrentUserPassword(null);

		HSSFSheet sheet = workbook.getSheetAt(0);
		
		System.out.println("Iniciando a leitura da planilha XLS:");
		long inicio = System.currentTimeMillis();
		
		List<CadastroBean> lista = new ArrayList<CadastroBean>();
		
		Iterator<Row> linha = sheet.rowIterator();
		boolean stop = false;//parar cas
		
		while(linha.hasNext()){
			if(stop) break;
			HSSFRow row = (HSSFRow)linha.next();
			if(row==null) break;
			if(row.getRowNum()==0) continue;//pular 1ª linha
			CadastroBean bean = new CadastroBean();
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
				switch(celula.getColumnIndex()){//retorna o numero das colunas
				case 0:
					bean.setCOD(Integer.parseInt(readingCell(celula).trim()));
					break;
				case 1:
					bean.setSTATUS(readingCell(celula).trim());
					break;
				case 2:
					bean.setCOMPL_STS(readingCell(celula).trim());
					break;
				case 3:
					bean.setATENDIMENTO(readingCell(celula).trim());
					break;
				case 4:
					bean.setPROCESSOS(readingCell(celula).trim());
					break;
				case 5:
					bean.setSISTEMA(readingCell(celula).trim());
					break;
				case 6:
					bean.setMP(readingCell(celula).trim());
					break;
				case 7:
					bean.setTIPO(readingCell(celula).trim());
					break;
				case 8:
					bean.setREGIME_TRIBUTARIO(readingCell(celula).trim());
					break;
				case 9:
					bean.setQUANT_SOC_GER(readingCell(celula).trim());
					break;
				case 10:
					bean.setEMPRESA(readingCell(celula).trim());
					break;
				case 11:
					bean.setNOME(readingCell(celula).trim());
					break;
				case 12:
					bean.setENDERECO(readingCell(celula).trim());
					break;
				case 13:
					bean.setBAIRRO(readingCell(celula).trim());
					break;
				case 14:
					bean.setCIDADE(readingCell(celula).trim());
					break;
				case 15:
					bean.setEST(readingCell(celula).trim());
					break;
				case 16:
					bean.setCEP(readingCell(celula).trim());
					break;
				case 17:
					bean.setCNPJ(readingCell(celula).trim());
					break;
				case 18:
					bean.setDATA_CNPJ(readingCell(celula).trim());
					break;
				case 19:
					bean.setIE(readingCell(celula).trim());
					break;
				case 20:
					bean.setVLR_CAPITAL(readingCell(celula).trim());
					break;
				case 21:
					bean.setVL_EXTENSO(readingCell(celula).trim());
					break;
				case 22:
					bean.setVL_COTA(readingCell(celula).trim());
					break;
				case 23:
					bean.setVL_EXT_COTA(readingCell(celula).trim());
					break;
				case 24:
					bean.setCOTAS_TOTAL(readingCell(celula).trim());
					break;
				case 25:
					bean.setCOTAS_EXTENSO(readingCell(celula).trim());
					break;
				case 26:
					bean.setPERC_CAP_1(readingCell(celula).trim());
					break;
				case 27:
					bean.setPERC_CAP_2(readingCell(celula).trim());
					break;
				case 28:
					bean.setPERC_CAP_DEMAIS(readingCell(celula).trim());
					break;
				case 29:
					bean.setSOMA_PERC_CAP(readingCell(celula).trim());
					break;
				case 30:
					bean.setCOTAS_01(readingCell(celula).trim());
					break;
				case 31:
					bean.setCOTAS_02(readingCell(celula).trim());
					break;
				case 32:
					bean.setPREF(readingCell(celula).trim());
					break;
				case 33:
					bean.setDATA_IM(readingCell(celula).trim());
					break;
				case 34:
					bean.setN_CONTRIB(readingCell(celula).trim());
					break;
				case 35:
					bean.setALIQUOTA_DE_ISS(readingCell(celula).trim());
					break;
				case 36:
					bean.setENC_MUNIC(readingCell(celula).trim());
					break;
				case 37:
					bean.setDT_DISTRATO(readingCell(celula).trim());
					break;
				case 38:
					bean.setNIRC(readingCell(celula).trim());
					break;
				case 39:
					bean.setN_REG_CART_OU_NIRE(readingCell(celula).trim());
					break;
				case 40:
					bean.setDATA_REG_CART_OU_JUCESP(readingCell(celula).trim());
					break;
				case 41:
					bean.setSIND_CNPJ(readingCell(celula).trim());
					break;
				case 42:
					bean.setATIVIDADE(readingCell(celula).trim());
					break;
				case 43:
					bean.setSINDICATO(readingCell(celula).trim());
					break;
				case 44:
					bean.setCNAE1(readingCell(celula).trim());
					break;
				case 45:
					bean.setCOMP_CNAE1(readingCell(celula).trim());
					break;
				case 46:
					bean.setDIG_CNAE1(readingCell(celula).trim());
					break;
				case 47:
					bean.setCNAE(readingCell(celula).trim());
					break;
				case 48:
					bean.setCOMP_CNAE(readingCell(celula).trim());
					break;
				case 49:
					bean.setDIG_CNAE(readingCell(celula).trim());
					break;
				case 50:
					bean.setFPAS(readingCell(celula).trim());
					break;
				case 51:
					bean.setQUANT_SOCIOS(readingCell(celula).trim());
					break;
				case 52:
					bean.setDATA_INSS(readingCell(celula).trim());
					break;
				case 53:
					bean.setCONSULTORIA_INICIO(readingCell(celula).trim());
					break;
				case 54:
					bean.setALOCADO(readingCell(celula).trim());
					break;
				case 55:
					bean.setINDICADO(readingCell(celula).trim());
					break;
				case 56:
					bean.setCONSULT_ATUAL(readingCell(celula).trim());
					break;
				case 57:
					bean.setAPELIDO_1(readingCell(celula).trim());
					break;
				case 58:
					bean.setEND_CORRESP(readingCell(celula).trim());
					break;
				case 59:
					bean.setBAIRRO_CO(readingCell(celula).trim());
					break;
				case 60:
					bean.setCID_CO(readingCell(celula).trim());
					break;
				case 61:
					bean.setEST_CO(readingCell(celula).trim());
					break;
				case 62:
					bean.setCEP_CO(readingCell(celula).trim());
					break;
				case 63:
					bean.setNOME_SOCIO1(readingCell(celula).trim());
					break;
				case 64:
					bean.setQUALIDADE1(readingCell(celula).trim());
					break;
				case 65:
					bean.setNAC_01(readingCell(celula).trim());
					break;
				case 66:
					bean.setNATURALIDADE_1(readingCell(celula).trim());
					break;
				case 67:
					bean.setEST_CIVEL_01(readingCell(celula).trim());
					break;
				case 68:
					bean.setMAIOR_EMANCIPADO_1(readingCell(celula).trim());
					break;
				case 69:
					bean.setPROF_01(readingCell(celula).trim());
					break;
				case 70:
					bean.setDIA_NASC1(readingCell(celula).trim());
					break;
				case 71:
					bean.setMES_NASC1(readingCell(celula).trim());
					break;
				case 72:
					bean.setANO_NASC1(readingCell(celula).trim());
					break;
				case 73:
					bean.setPIS_SOC1(readingCell(celula).trim());
					break;
				case 74:
					bean.setEND_SOC_1(readingCell(celula).trim());
					break;
				case 75:
					bean.setBAIRRO1(readingCell(celula).trim());
					break;
				case 76:
					bean.setCIDADE1(readingCell(celula).trim());
					break;
				case 77:
					bean.setEST1(readingCell(celula).trim());
					break;
				case 78:
					bean.setCEP1(readingCell(celula).trim());
					break;
				case 79:
					bean.setRG1(readingCell(celula).trim());
					break;
				case 80:
					bean.setORGAO1(readingCell(celula).trim());
					break;
				case 81:
					bean.setESTD1(readingCell(celula).trim());
					break;
				case 82:
					bean.setDATA_EXP1(readingCell(celula).trim());
					break;
				case 83:
					bean.setCPF1(readingCell(celula).trim());
					break;
				case 84:
					bean.setEMAIL_SOC_1(readingCell(celula).trim());
					break;
				case 85:
					bean.setEMAIL_FINANCEIRO(readingCell(celula).trim());
					break;
				case 86:
					bean.setDDD1(readingCell(celula).trim());
					break;
				case 87:
					bean.setFONERES1(readingCell(celula).trim());
					break;
				case 88:
					bean.setRES_RAMAL_1(readingCell(celula).trim());
					break;
				case 89:
					bean.setDDD1COML(readingCell(celula).trim());
					break;
				case 90:
					bean.setFONECOML1(readingCell(celula).trim());
					break;
				case 91:
					bean.setCOML_RAMAL_1(readingCell(celula).trim());
					break;
				case 92:
					bean.setDDD1CEL(readingCell(celula).trim());
					break;
				case 93:
					bean.setCELULAR(readingCell(celula).trim());
					break;
				case 94:
					bean.setDDD1REC(readingCell(celula).trim());
					break;
				case 95:
					bean.setFONEREC1(readingCell(celula).trim());
					break;
				case 96:
					bean.setOBSFONE_REC1(readingCell(celula).trim());
					break;
				case 97:
					bean.setNOME_SOC_2(readingCell(celula).trim());
					break;
				case 98:
					bean.setQUALIDADE2(readingCell(celula).trim());
					break;
				case 99:
					bean.setNAC_02(readingCell(celula).trim());
					break;
				case 100:
					bean.setNATURALIDADE_2(readingCell(celula).trim());
					break;
				case 101:
					bean.setEST_CIVEL_02(readingCell(celula).trim());
					break;
				case 102:
					bean.setMAIOR_EMANCIPADO_2(readingCell(celula).trim());
					break;
				case 103:
					bean.setPROF_02(readingCell(celula).trim());
					break;
				case 104:
					bean.setEND_SOC_2(readingCell(celula).trim());
					break;
				case 105:
					bean.setBAIRRO2(readingCell(celula).trim());
					break;
				case 106:
					bean.setCIDADE2(readingCell(celula).trim());
					break;
				case 107:
					bean.setEST2(readingCell(celula).trim());
					break;
				case 108:
					bean.setCEP2(readingCell(celula).trim());
					break;
				case 109:
					bean.setDIA_NASC2(readingCell(celula).trim());
					break;
				case 110:
					bean.setMES_NASC2(readingCell(celula).trim());
					break;
				case 111:
					bean.setANO_NASC2(readingCell(celula).trim());
					break;
				case 112:
					bean.setPIS_SOC2(readingCell(celula).trim());
					break;
				case 113:
					bean.setRG2(readingCell(celula).trim());
					break;
				case 114:
					bean.setORGAO2(readingCell(celula).trim());
					break;
				case 115:
					bean.setESTD2(readingCell(celula).trim());
					break;
				case 116:
					bean.setDATA_EXP2(readingCell(celula).trim());
					break;
				case 117:
					bean.setCPF2(readingCell(celula).trim());
					break;
				case 118:
					bean.setDDD2RES(readingCell(celula).trim());
					break;
				case 119:
					bean.setFONERES2(readingCell(celula).trim());
					break;
				case 120:
					bean.setDDD2COML(readingCell(celula).trim());
					break;
				case 121:
					bean.setFONECOM2(readingCell(celula).trim());
					break;
				case 122:
					bean.setDDD2CEL(readingCell(celula).trim());
					break;
				case 123:
					bean.setCELULAR2(readingCell(celula).trim());
					break;
				case 124:
					bean.setDDD2REC(readingCell(celula).trim());
					break;
				case 125:
					bean.setFONEREC2(readingCell(celula).trim());
					break;
				case 126:
					bean.setOBSFONE_RC2(readingCell(celula).trim());
					break;
				default:
					break;
				}
			}
			lista.add(bean);
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long fim = System.currentTimeMillis();
		tempo = fim - inicio;
		//System.out.println("Tempo total: "+(fim-inicio)+"\nTamanho da Lista: "+lista.size());
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
	public void removeTempWorkbook(ConfExtraBean cb){
		File[] files = new File(cb.getDIRETORIO_TEMP()).listFiles();
		for(File f : files)
			f.delete();
	}
	public String insertOrUpdate(List<CadastroBean> lista){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		StringBuilder builder = new StringBuilder();
		lista.forEach(c->{
			if(c.getCOD()!=0){
				builder.append(factory.saveOrUpdateSession(session, c));
				builder.append(System.getProperty("line.separator"));
			}
		});
		factory.closeSession(session);
		return builder.toString();
	}
	public String  createTxtLogFile(File file, StringBuilder builder) throws IOException{
		File f = new File(file.getAbsolutePath()+"/"+new Date()+".txt");
		f.createNewFile();
		FileWriter fWriter = new FileWriter(f, true);
		fWriter.write(builder.toString());
		fWriter.close();
		return f.getAbsolutePath();
	}
	
	//tempo de execuração do processo
	public long getTempo(){
		return this.tempo;
	}
}
