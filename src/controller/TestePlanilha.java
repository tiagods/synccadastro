package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.DateUtil;

import model.CadastroBean;

public class TestePlanilha {
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) throws IOException{
		
		File file = new File("C:/Users/Tiago/Documents/Cadastro.xls");
		Biff8EncryptionKey.setCurrentUserPassword("PLKCONTRATOS");
		NPOIFSFileSystem fs = new NPOIFSFileSystem(file, true);
		HSSFWorkbook workbook = new HSSFWorkbook(fs.getRoot(), true);
		Biff8EncryptionKey.setCurrentUserPassword(null);

		HSSFSheet sheet = workbook.getSheetAt(0);
		
		System.out.println("Iniciando a leitura da planilha XLS:");
		long inicio = System.currentTimeMillis();
		
		List<CadastroBean> lista = new ArrayList<CadastroBean>();
		
		Iterator linha = sheet.rowIterator();
		boolean stop = false;//parar cas
		
		int i = 0;
		while(linha.hasNext()){
			if(stop)break;
			HSSFRow row = (HSSFRow)linha.next();
			if(row==null) break;
			if(row.getRowNum()>0){
				CadastroBean bean = new CadastroBean();
				Iterator columns = row.cellIterator();
				
				System.out.println("linha "+i);
				int c=0;
				while(columns.hasNext()){
					HSSFCell celula = (HSSFCell)columns.next();
					//	System.out.print(tratarTipo(cell)+"\t");
					System.out.println("coluna "+c);
					
					if(row.getRowNum()!=0 && celula.getColumnIndex()==0){
						try{
							Integer.parseInt(readingCell(celula));//se não conseguir o cast, o campo codigo estara em branco, nesse caso o for ira fechar
						}catch(NumberFormatException e){
							stop=true;
							break;
						}
					}
					switch(celula.getColumnIndex()){//retorna o numero das colunas
				        case 0:
				        	bean.setCOD(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 1:
				        	bean.setSTATUS(readingCell(celula));
				        	break;
				        case 2:
				        	bean.setCOMPL_STS(celula.getDateCellValue());
				        	break;
				        case 3:
				        	bean.setATENDIMENTO(readingCell(celula));
				        	break;
				        case 4:
				        	bean.setPROCESSOS(readingCell(celula));
				        	break;
				        case 5:
				        	bean.setSISTEMA(readingCell(celula));
				        	break;
				        case 6:
				        	bean.setMP(readingCell(celula));
				        	break;
				        case 7:
				        	bean.setTIPO(readingCell(celula));
				        	break;
				        case 8:
				        	bean.setREGIME_TRIBUTARIO(readingCell(celula));
				        	break;
				        case 9:
				        	bean.setQUANT_SOC_GER(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 10:
				        	bean.setEMPRESA(readingCell(celula));
				        	break;
				        case 11:
				        	bean.setNOME(readingCell(celula));
				        	break;
				        case 12:
				        	bean.setENDERECO(readingCell(celula));
				        	break;
				        case 13:
				        	bean.setBAIRRO(readingCell(celula));
				        	break;
				        case 14:
				        	bean.setCIDADE(readingCell(celula));
				        	break;
				        case 15:
				        	bean.setEST(readingCell(celula));
				        	break;
				        case 16:
				        	bean.setCEP(readingCell(celula));
				        	break;
				        case 17:
				        	bean.setCNPJ(readingCell(celula));
				        	break;
				        case 18:
				        	bean.setDATA_CNPJ(celula.getDateCellValue());
				        	break;
				        case 19:
				        	bean.setIE(readingCell(celula));
				        	break;
				        case 20:
				        	bean.setVLR_CAPITAL(Double.valueOf(readingCell(celula)));
				        	break;
				        case 21:
				        	bean.setVL_EXTENSO(readingCell(celula));
				        	break;
				        case 22:
				        	bean.setVL_COTA(Double.valueOf(readingCell(celula)));
				        	break;
				        case 23:
				        	bean.setVL_EXT_COTA(readingCell(celula));
				        	break;
				        case 24:
				        	bean.setCOTAS_TOTAL(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 25:
				        	bean.setCOTAS_EXTENSO(readingCell(celula));
				        	break;
				        case 26:
				        	bean.setPERC_CAP_1(readingCell(celula));
				        	break;
				        case 27:
				        	bean.setPERC_CAP_2(readingCell(celula));
				        	break;
				        case 28:
				        	bean.setPERC_CAP_DEMAIS(readingCell(celula));
				        	break;
				        case 29:
				        	bean.setSOMA_PERC_CAP(readingCell(celula));
				        	break;
				        case 30:
				        	bean.setCOTAS_01(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 31:
				        	bean.setCOTAS_02(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 32:
				        	bean.setPREF(readingCell(celula));
				        	break;
				        case 33:
				        	bean.setDATA_IM(celula.getDateCellValue());
				        	break;
				        case 34:
				        	bean.setN_CONTRIB(readingCell(celula));
				        	break;
				        case 35:
				        	bean.setALIQUOTA_DE_ISS(readingCell(celula));
				        	break;
				        case 36:
				        	bean.setENC_MUNIC(celula.getDateCellValue());
				        	break;
				        case 37:
				        	bean.setDT_DISTRATO(celula.getDateCellValue());
				        	break;
				        case 38:
				        	bean.setNIRC(readingCell(celula));
				        	break;
				        case 39:
				        	bean.setN_REG_CART_OU_NIRE(readingCell(celula));
				        	break;
				        case 40:
				        	bean.setDATA_REG_CART_OU_JUCESP(celula.getDateCellValue());
				        	break;
				        case 41:
				        	bean.setSIND_CNPJ(readingCell(celula));
				        	break;
				        case 42:
				        	bean.setATIVIDADE(readingCell(celula));
				        	break;
				        case 43:
				        	bean.setSINDICATO(readingCell(celula));
				        	break;
				        case 44:
				        	bean.setCNAE1(readingCell(celula));
				        	break;
				        case 45:
				        	bean.setCOMP_CNAE1(readingCell(celula));
				        	break;
				        case 46:
				        	bean.setDIG_CNAE1(readingCell(celula));
				        	break;
				        case 47:
				        	bean.setCNAE(readingCell(celula));
				        	break;
				        case 48:
				        	bean.setCOMP_CNAE(readingCell(celula));
				        	break;
				        case 49:
				        	bean.setDIG_CNAE(readingCell(celula));
				        	break;
				        case 50:
				        	bean.setFPAS(readingCell(celula));
				        	break;
				        case 51:
				        	bean.setQUANT_SOCIOS(readingCell(celula));
				        	break;
				        case 52:
				        	bean.setDATA_INSS(celula.getDateCellValue());
				        	break;
				        case 53:
				        	bean.setCONSULTORIA_INICIO(readingCell(celula));
				        	break;
				        case 54:
				        	bean.setALOCADO(readingCell(celula));
				        	break;
				        case 55:
				        	bean.setINDICADO(readingCell(celula));
				        	break;
				        case 56:
				        	bean.setCONSULT_ATUAL(readingCell(celula));
				        	break;
				        case 57:
				        	bean.setAPELIDO_1(readingCell(celula));
				        	break;
				        case 58:
				        	bean.setEND_CORRESP(readingCell(celula));
				        	break;
				        case 59:
				        	bean.setBAIRRO_CO(readingCell(celula));
				        	break;
				        case 60:
				        	bean.setCID_CO(readingCell(celula));
				        	break;
				        case 61:
				        	bean.setEST_CO(readingCell(celula));
				        	break;
				        case 62:
				        	bean.setCEP_CO(readingCell(celula));
				        	break;
				        case 63:
				        	bean.setNOME_SOCIO1(readingCell(celula));
				        	break;
				        case 64:
				        	bean.setQUALIDADE1(readingCell(celula));
				        	break;
				        case 65:
				        	bean.setNAC_01(readingCell(celula));
				        	break;
				        case 66:
				        	bean.setNATURALIDADE_1(readingCell(celula));
				        	break;
				        case 67:
				        	bean.setEST_CIVEL_01(readingCell(celula));
				        	break;
				        case 68:
				        	bean.setMAIOR_EMANCIPADO_1(readingCell(celula));
				        	break;
				        case 69:
				        	bean.setPROF_01(readingCell(celula));
				        	break;
				        case 70:
				        	bean.setDIA_NASC1(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 71:
				        	bean.setMES_NASC1(readingCell(celula));
				        	break;
				        case 72:
				        	bean.setANO_NASC1(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 73:
				        	bean.setPIS_SOC1(readingCell(celula));
				        	break;
				        case 74:
				        	bean.setEND_SOC_1(readingCell(celula));
				        	break;
				        case 75:
				        	bean.setBAIRRO1(readingCell(celula));
				        	break;
				        case 76:
				        	bean.setCIDADE1(readingCell(celula));
				        	break;
				        case 77:
				        	bean.setEST1(readingCell(celula));
				        	break;
				        case 78:
				        	bean.setCEP1(readingCell(celula));
				        	break;
				        case 79:
				        	bean.setRG1(readingCell(celula));
				        	break;
				        case 80:
				        	bean.setORGAO1(readingCell(celula));
				        	break;
				        case 81:
				        	bean.setESTD1(readingCell(celula));
				        	break;
				        case 82:
				        	bean.setDATA_EXP1(celula.getDateCellValue());
				        	break;
				        case 83:
				        	bean.setCPF1(readingCell(celula));
				        	break;
				        case 84:
				        	bean.setEMAIL_SOC_1(readingCell(celula));
				        	break;
				        case 85:
				        	bean.setEMAIL_FINANCEIRO(readingCell(celula));
				        	break;
				        case 86:
				        	bean.setDDD1(readingCell(celula));
				        	break;
				        case 87:
				        	bean.setFONERES1(readingCell(celula));
				        	break;
				        case 88:
				        	bean.setRES_RAMAL_1(readingCell(celula));
				        	break;
				        case 89:
				        	bean.setDDD1COML(readingCell(celula));
				        	break;
				        case 90:
				        	bean.setFONECOML1(readingCell(celula));
				        	break;
				        case 91:
				        	bean.setCOML_RAMAL_1(readingCell(celula));
				        	break;
				        case 92:
				        	bean.setDDD1CEL(readingCell(celula));
				        	break;
				        case 93:
				        	bean.setCELULAR(readingCell(celula));
				        	break;
				        case 94:
				        	bean.setDDD1REC(readingCell(celula));
				        	break;
				        case 95:
				        	bean.setFONEREC1(readingCell(celula));
				        	break;
				        case 96:
				        	bean.setOBSFONE_REC1(readingCell(celula));
				        	break;
				        case 97:
				        	bean.setNOME_SOC_2(readingCell(celula));
				        	break;
				        case 98:
				        	bean.setQUALIDADE2(readingCell(celula));
				        	break;
				        case 99:
				        	bean.setNAC_02(readingCell(celula));
				        	break;
				        case 100:
				        	bean.setNATURALIDADE_2(readingCell(celula));
				        	break;
				        case 101:
				        	bean.setEST_CIVEL_02(readingCell(celula));
				        	break;
				        case 102:
				        	bean.setMAIOR_EMANCIPADO_2(readingCell(celula));
				        	break;
				        case 103:
				        	bean.setPROF_02(readingCell(celula));
				        	break;
				        case 104:
				        	bean.setEND_SOC_2(readingCell(celula));
				        	break;
				        case 105:
				        	bean.setBAIRRO2(readingCell(celula));
				        	break;
				        case 106:
				        	bean.setCIDADE2(readingCell(celula));
				        	break;
				        case 107:
				        	bean.setEST2(readingCell(celula));
				        	break;
				        case 108:
				        	bean.setCEP2(readingCell(celula));
				        	break;
				        case 109:
				        	bean.setDIA_NASC2(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 110:
				        	bean.setMES_NASC2(readingCell(celula));
				        	break;
				        case 111:
				        	bean.setANO_NASC2(Integer.parseInt(readingCell(celula)));
				        	break;
				        case 112:
				        	bean.setPIS_SOC2(readingCell(celula));
				        	break;
				        case 113:
				        	bean.setRG2(readingCell(celula));
				        	break;
				        case 114:
				        	bean.setORGAO2(readingCell(celula));
				        	break;
				        case 115:
				        	bean.setESTD2(readingCell(celula));
				        	break;
				        case 116:
				        	bean.setDATA_EXP2(celula.getDateCellValue());
				        	break;
				        case 117:
				        	bean.setCPF2(readingCell(celula));
				        	break;
				        case 118:
				        	bean.setDDD2RES(readingCell(celula));
				        	break;
				        case 119:
				        	bean.setFONERES2(readingCell(celula));
				        	break;
				        case 120:
				        	bean.setDDD2COML(readingCell(celula));
				        	break;
				        case 121:
				        	bean.setFONECOM2(readingCell(celula));
				        	break;
				        case 122:
				        	bean.setDDD2CEL(readingCell(celula));
				        	break;
				        case 123:
				        	bean.setCELULAR2(readingCell(celula));
				        	break;
				        case 124:
				        	bean.setDDD2REC(readingCell(celula));
				        	break;
				        case 125:
				        	bean.setFONEREC2(readingCell(celula));
				        	break;
				        case 126:
				        	bean.setOBSFONE_RC2(readingCell(celula));
				        	break;
				        default:
				        	break;
					}
					c++;
				}
			lista.add(bean);
			}
			i++;
		}
		workbook.close();
		long fim = System.currentTimeMillis();
		System.out.println("Tempo total: "+(fim-inicio));
		
		for(CadastroBean  value : lista){
			System.out.print(""+value.getCOD());
		}
}
	public static String readingCell(HSSFCell celula){ //metodo usado para tratar as celulas
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
}
