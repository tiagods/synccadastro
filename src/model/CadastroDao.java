package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CadastroDao {
	
	void readWorkbook(){
		
	}
	
	@SuppressWarnings("resource")
	boolean copyWorkbook(ConfExtraBean cb){
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
	        if(!extensao.equals("xls")){
		        return false;
	        }
		}
		return false;
	}
	void removeTempWorkbook(ConfExtraBean cb){
		File[] files = new File(cb.getDIRETORIO_TEMP()).listFiles();
		for(File f : files)
			f.delete();
	}
	void insertOrUpdate(CadastroBean cb){
		
	}
}
