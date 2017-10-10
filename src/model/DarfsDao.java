package model;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DarfsDao{
	private Map<File,File> mapa = new HashMap<>();;
	private File pathTo;
	public void iniciar(LocalDateTime localDate2){
		int mes = localDate2.getMonthValue();
		pathTo = new File("\\\\plkserver\\Todos Departamentos\\_DARF\\"+localDate2.getYear()+"\\"+
				(mes<10?"0"+mes:mes));
		if(!pathTo.exists()) pathTo.mkdirs();
		
		File dir = new File("\\\\plkserver\\Todos Departamentos\\DeptoPessoal\\EMPRESAS FOLHA");
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return !name.contains("_EMPRESAS DIVS_2014 E 2015");
			}
		});
		for(File f : files) {
			String[] empresa = f.getName().split(" ");
			String nomeEmpresa = empresa[empresa.length-1];
			if(f.isDirectory()) {
				buscar(f,nomeEmpresa,(mes<10?"0"+mes:""+mes),localDate2.getYear());
			}
			else
				continue;
		}
		FileWriter fw;
		try {
			File fRelat = new File(pathTo.getAbsolutePath()+"\\relatorios");
			if(!fRelat.exists())
				fRelat.mkdir();
			fw = new FileWriter(new File(fRelat.getAbsolutePath()+"\\resultado "+localDate2.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+".csv"));
			StringBuilder builder = new StringBuilder();
			builder.append("Localização");
			builder.append(";");
			builder.append("Destino");
			builder.append(System.getProperty("line.separator"));
			mapa.keySet().forEach(c->{
				builder.append(c.getAbsolutePath());
				builder.append(";");
				builder.append(mapa.get(c).getAbsolutePath());
				builder.append(System.getProperty("line.separator"));
			});
			fw.write(builder.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void buscar(File file,String nomeEmpresa,String mes,int ano) {
		File[] files = file.listFiles();
		for(File f : files) {
			if(f.isDirectory()) {
				buscar(f,nomeEmpresa,mes,ano);
			}
			else if(f.getName().toUpperCase().contains("DARF")){
				if(f.getAbsolutePath().contains(mes+"."+ano)) {
					String arquivoNome = f.getName().contains(nomeEmpresa)?f.getName():nomeEmpresa+" - "+f.getName();
					File fileFinal = new File(pathTo+"\\"+arquivoNome);
					
					if(!fileFinal.exists() || f.lastModified()>fileFinal.lastModified()) {
						Path pathI = Paths.get(f.getAbsolutePath());
			            Path pathO = Paths.get(fileFinal.getAbsolutePath());
			            try {
							Files.copy(pathI, pathO, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							e.printStackTrace();
						}
			            mapa.put(f, fileFinal);
					}
				}
				else 
					continue;
			}
		}
	}

}
