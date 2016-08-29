package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

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
		
		Iterator linha = sheet.rowIterator();
		
		boolean stop = false;
		while(linha.hasNext()){
			
			if(stop)break;
			
			HSSFRow row = (HSSFRow)linha.next();
			if(row==null) break;
			
			Iterator columns = row.cellIterator();
			while(columns.hasNext()){
				HSSFCell cell = (HSSFCell)columns.next();
				System.out.print(tratarTipo(cell)+"\t");
				if(row.getRowNum()!=0 && cell.getColumnIndex()==0){
					try{
						Integer.parseInt(tratarTipo(cell));
					}catch(NumberFormatException e){
						stop=true;
						break;
					}
					
				}
			}
			System.out.println("");
		}
		workbook.close();
		
		long fim = System.currentTimeMillis();
		System.out.println("Tempo total: "+(fim-inicio));
		
	}
	
	public static String tratarTipo(HSSFCell celula){ //metodo usado para tratar as celulas
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
	public void enviarValores(HSSFCell celula, CadastroBean bean){
        switch(celula.getColumnIndex()){//retorna o numero das colunas, nesse caso defini como 5 colunas
        case 0:
        	bean.setCOD(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 1:
        	bean.setSTATUS(tratarTipo(celula));
        	break;
        case 2:
        	bean.setCOMPL_STS(celula.getDateCellValue());
        	break;
        case 3:
        	bean.setATENDIMENTO(tratarTipo(celula));
        	break;
        case 4:
        	bean.setPROCESSOS(tratarTipo(celula));
        	break;
        case 5:
        	bean.setSISTEMA(tratarTipo(celula));
        	break;
        case 6:
        	bean.setMP(tratarTipo(celula));
        	break;
        case 7:
        	bean.setTIPO(tratarTipo(celula));
        	break;
        case 8:
        	bean.setREGIME_TRIBUTARIO(tratarTipo(celula));
        	break;
        case 9:
        	bean.setQUANT_SOC_GER(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 10:
        	bean.setEMPRESA(tratarTipo(celula));
        	break;
        case 11:
        	bean.setNOME(tratarTipo(celula));
        	break;
        case 12:
        	bean.setENDERECO(tratarTipo(celula));
        	break;
        case 13:
        	bean.setBAIRRO(tratarTipo(celula));
        	break;
        case 14:
        	bean.setCIDADE(tratarTipo(celula));
        	break;
        case 15:
        	bean.setEST(tratarTipo(celula));
        	break;
        case 16:
        	bean.setCEP(tratarTipo(celula));
        	break;
        case 17:
        	bean.setCNPJ(tratarTipo(celula));
        	break;
        case 18:
        	bean.setDATA_CNPJ(celula.getDateCellValue());
        	break;
        case 19:
        	bean.setIE(tratarTipo(celula));
        	break;
        case 20:
        	bean.setVLR_CAPITAL(Double.valueOf(tratarTipo(celula)));
        	break;
        case 21:
        	bean.setVL_EXTENSO(tratarTipo(celula));
        	break;
        case 22:
        	bean.setVL_COTA(Double.valueOf(tratarTipo(celula)));
        	break;
        case 23:
        	bean.setVL_EXT_COTA(tratarTipo(celula));
        	break;
        case 24:
        	bean.setCOTAS_TOTAL(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 25:
        	bean.setCOTAS_EXTENSO(tratarTipo(celula));
        	break;
        case 26:
        	bean.setPERC_CAP_1(tratarTipo(celula));
        	break;
        case 27:
        	bean.setPERC_CAP_2(tratarTipo(celula));
        	break;
        case 28:
        	bean.setPERC_CAP_DEMAIS(tratarTipo(celula));
        	break;
        case 29:
        	bean.setSOMA_PERC_CAP(tratarTipo(celula));
        	break;
        case 30:
        	bean.setCOTAS_01(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 31:
        	bean.setCOTAS_02(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 32:
        	bean.setPREF(tratarTipo(celula));
        	break;
        case 33:
        	bean.setDATA_IM(celula.getDateCellValue());
        	break;
        case 34:
        	bean.setN_CONTRIB(tratarTipo(celula));
        	break;
        case 35:
        	bean.setALIQUOTA_DE_ISS(tratarTipo(celula));
        	break;
        case 36:
        	bean.setENC_MUNIC(celula.getDateCellValue());
        	break;
        case 37:
        	bean.setDT_DISTRATO(celula.getDateCellValue());
        	break;
        case 38:
        	bean.setNIRC(tratarTipo(celula));
        	break;
        case 39:
        	bean.setN_REG_CART_OU_NIRE(tratarTipo(celula));
        	break;
        case 40:
        	bean.setDATA_REG_CART_OU_JUCESP(celula.getDateCellValue());
        	break;
        case 41:
        	bean.setSIND_CNPJ(tratarTipo(celula));
        	break;
        case 42:
        	bean.setATIVIDADE(tratarTipo(celula));
        	break;
        case 43:
        	bean.setSINDICATO(tratarTipo(celula));
        	break;
        case 44:
        	bean.setCNAE1(tratarTipo(celula));
        	break;
        case 45:
        	bean.setCOMP_CNAE1(tratarTipo(celula));
        	break;
        case 46:
        	bean.setDIG_CNAE1(tratarTipo(celula));
        	break;
        case 47:
        	bean.setCNAE(tratarTipo(celula));
        	break;
        case 48:
        	bean.setCOMP_CNAE(tratarTipo(celula));
        	break;
        case 49:
        	bean.setDIG_CNAE(tratarTipo(celula));
        	break;
        case 50:
        	bean.setFPAS(tratarTipo(celula));
        	break;
        case 51:
        	bean.setQUANT_SOCIOS(tratarTipo(celula));
        	break;
        case 52:
        	bean.setDATA_INSS(celula.getDateCellValue());
        	break;
        case 53:
        	bean.setCONSULTORIA_INICIO(tratarTipo(celula));
        	break;
        case 54:
        	bean.setALOCADO(tratarTipo(celula));
        	break;
        case 55:
        	bean.setINDICADO(tratarTipo(celula));
        	break;
        case 56:
        	bean.setCONSULT_ATUAL(tratarTipo(celula));
        	break;
        case 57:
        	bean.setAPELIDO_1(tratarTipo(celula));
        	break;
        case 58:
        	bean.setEND_CORRESP(tratarTipo(celula));
        	break;
        case 59:
        	bean.setBAIRRO_CO(tratarTipo(celula));
        	break;
        case 60:
        	bean.setCID_CO(tratarTipo(celula));
        	break;
        case 61:
        	bean.setEST_CO(tratarTipo(celula));
        	break;
        case 62:
        	bean.setCEP_CO(tratarTipo(celula));
        	break;
        case 63:
        	bean.setNOME_SOCIO1(tratarTipo(celula));
        	break;
        case 64:
        	bean.setQUALIDADE1(tratarTipo(celula));
        	break;
        case 65:
        	bean.setNAC_01(tratarTipo(celula));
        	break;
        case 66:
        	bean.setNATURALIDADE_1(tratarTipo(celula));
        	break;
        case 67:
        	bean.setEST_CIVEL_01(tratarTipo(celula));
        	break;
        case 68:
        	bean.setMAIOR_EMANCIPADO_1(tratarTipo(celula));
        	break;
        case 69:
        	bean.setPROF_01(tratarTipo(celula));
        	break;
        case 70:
        	bean.setDIA_NASC1(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 71:
        	bean.setMES_NASC1(tratarTipo(celula));
        	break;
        case 72:
        	bean.setANO_NASC1(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 73:
        	bean.setPIS_SOC1(tratarTipo(celula));
        	break;
        case 74:
        	bean.setEND_SOC_1(tratarTipo(celula));
        	break;
        case 75:
        	bean.setBAIRRO1(tratarTipo(celula));
        	break;
        case 76:
        	bean.setCIDADE1(tratarTipo(celula));
        	break;
        case 77:
        	bean.setEST1(tratarTipo(celula));
        	break;
        case 78:
        	bean.setCEP1(tratarTipo(celula));
        	break;
        case 79:
        	bean.setRG1(tratarTipo(celula));
        	break;
        case 80:
        	bean.setORGAO1(tratarTipo(celula));
        	break;
        case 81:
        	bean.setESTD1(tratarTipo(celula));
        	break;
        case 82:
        	bean.setDATA_EXP1(celula.getDateCellValue());
        	break;
        case 83:
        	bean.setCPF1(tratarTipo(celula));
        	break;
        case 84:
        	bean.setEMAIL_SOC_1(tratarTipo(celula));
        	break;
        case 85:
        	bean.setEMAIL_FINANCEIRO(tratarTipo(celula));
        	break;
        case 86:
        	bean.setDDD1(tratarTipo(celula));
        	break;
        case 87:
        	bean.setFONERES1(tratarTipo(celula));
        	break;
        case 88:
        	bean.setRES_RAMAL_1(tratarTipo(celula));
        	break;
        case 89:
        	bean.setDDD1COML(tratarTipo(celula));
        	break;
        case 90:
        	bean.setFONECOML1(tratarTipo(celula));
        	break;
        case 91:
        	bean.setCOML_RAMAL_1(tratarTipo(celula));
        	break;
        case 92:
        	bean.setDDD1CEL(tratarTipo(celula));
        	break;
        case 93:
        	bean.setCELULAR(tratarTipo(celula));
        	break;
        case 94:
        	bean.setDDD1REC(tratarTipo(celula));
        	break;
        case 95:
        	bean.setFONEREC1(tratarTipo(celula));
        	break;
        case 96:
        	bean.setOBSFONE_REC1(tratarTipo(celula));
        	break;
        case 97:
        	bean.setNOME_SOC_2(tratarTipo(celula));
        	break;
        case 98:
        	bean.setQUALIDADE2(tratarTipo(celula));
        	break;
        case 99:
        	bean.setNAC_02(tratarTipo(celula));
        	break;
        case 100:
        	bean.setNATURALIDADE_2(tratarTipo(celula));
        	break;
        case 101:
        	bean.setEST_CIVEL_02(tratarTipo(celula));
        	break;
        case 102:
        	bean.setMAIOR_EMANCIPADO_2(tratarTipo(celula));
        	break;
        case 103:
        	bean.setPROF_02(tratarTipo(celula));
        	break;
        case 104:
        	bean.setEND_SOC_2(tratarTipo(celula));
        	break;
        case 105:
        	bean.setBAIRRO2(tratarTipo(celula));
        	break;
        case 106:
        	bean.setCIDADE2(tratarTipo(celula));
        	break;
        case 107:
        	bean.setEST2(tratarTipo(celula));
        	break;
        case 108:
        	bean.setCEP2(tratarTipo(celula));
        	break;
        case 109:
        	bean.setDIA_NASC2(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 110:
        	bean.setMES_NASC2(tratarTipo(celula));
        	break;
        case 111:
        	bean.setANO_NASC2(Integer.parseInt(tratarTipo(celula)));
        	break;
        case 112:
        	bean.setPIS_SOC2(tratarTipo(celula));
        	break;
        case 113:
        	bean.setRG2(tratarTipo(celula));
        	break;
        case 114:
        	bean.setORGAO2(tratarTipo(celula));
        	break;
        case 115:
        	bean.setESTD2(tratarTipo(celula));
        	break;
        case 116:
        	bean.setDATA_EXP2(celula.getDateCellValue());
        	break;
        case 117:
        	bean.setCPF2(tratarTipo(celula));
        	break;
        case 118:
        	bean.setDDD2RES(tratarTipo(celula));
        	break;
        case 119:
        	bean.setFONERES2(tratarTipo(celula));
        	break;
        case 120:
        	bean.setDDD2COML(tratarTipo(celula));
        	break;
        case 121:
        	bean.setFONECOM2(tratarTipo(celula));
        	break;
        case 122:
        	bean.setDDD2CEL(tratarTipo(celula));
        	break;
        case 123:
        	bean.setCELULAR2(tratarTipo(celula));
        	break;
        case 124:
        	bean.setDDD2REC(tratarTipo(celula));
        	break;
        case 125:
        	bean.setFONEREC2(tratarTipo(celula));
        	break;
        case 126:
        	bean.setOBSFONE_RC2(tratarTipo(celula));
        	break;
        default:
        	break;
        }
    }
}
