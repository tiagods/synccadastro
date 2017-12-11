package com.prolink.sync.job;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cascade {
	
	public static void main(String[] args) {
		File file = new File("\\\\plkserver\\Todos Departamentos\\DeptoPessoal\\EMPRESAS EM GERAL - EXTEMPORÂNEA");
		File[] f = file.listFiles();
		
		StringBuilder builder = new StringBuilder();
		for(File fi : f) {
			if(fi.isDirectory() && fi.listFiles().length==0) {
				System.out.println("Pasta vazia: "+fi.getAbsolutePath());
				//fi.delete();
				builder.append(fi.getAbsolutePath());
				builder.append(System.getProperty("line.separator"));
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(new File("c:/temp/resultadoClean.txt"));
			fw.write(builder.toString());
			fw.flush();
			fw.close();
			System.out.println("Pronto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
