package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IniciandoAniversario {
	
	static List<Aniversariante> aniversariantes = new ArrayList<>();
	
	public static void main(String[] args) {
		List<LocalDate> lista = new ArrayList<LocalDate>();
		LocalDate hoje = LocalDate.now();
		LocalDate segunda = hoje.plusDays(1);
		LocalDate terca = hoje.plusDays(2);
		LocalDate quarta = hoje.plusDays(3);
		LocalDate quinta = hoje.plusDays(4);
		LocalDate sexta = hoje.plusDays(5);
		LocalDate sabado = hoje.plusDays(6);
		LocalDate domingo = hoje.plusDays(7);		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lista.add(segunda);
		lista.add(terca);
		lista.add(quarta);
		lista.add(quinta);
		lista.add(sexta);
		lista.add(sabado);
		lista.add(domingo);
		
		for(LocalDate d : lista){
			executarConsulta("select COD, STATUS, EMPRESA, NOME_SOCIO1, DIA_NASC1, MES_NASC1 FROM CLIENTE "
					+ "where DIA_NASC1 = '"+d.getDayOfMonth()+"' and MES_NASC1 = '"+convertMounth(d.getMonthValue())+"'");

			executarConsulta("select COD, STATUS, EMPRESA, NOME_SOC_2, DIA_NASC2, MES_NASC2 FROM CLIENTE "
					+ "where DIA_NASC2 = '"+d.getDayOfMonth()+"' and MES_NASC2 = '"+convertMounth(d.getMonthValue())+"'");
		}
		aniversariantes.forEach(c->{
			System.out.println("ID: "+c.getId()+"\t\tStatus: "+c.getStatus()+"\t\tEmpresa: "+c.getEmpresa()
			+"\t\tNome: "+c.getNome()+"\t\tAniversario: "+c.getAniversario());
		});
	}
	public static Connection getCon(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection("jdbc:sqlserver://200.207.224.87:1433;databaseName=cadastro", "sa", "B0qbxRw9TL3xYTrHXOULROnH1cMu9JRx");
		}catch(SQLException e){
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	public static void executarConsulta(String comando){
		Connection con=getCon();
		try{
			PreparedStatement ps = con.prepareStatement(comando);
			ResultSet rs = ps.executeQuery();
			if(rs!=null){
				if(rs.next()){
					Aniversariante aniversariante = new Aniversariante();
					aniversariante.setId(rs.getString(1));
					aniversariante.setStatus(rs.getString(2));
					aniversariante.setEmpresa(rs.getString(3));
					aniversariante.setNome(rs.getString(4));
					aniversariante.setAniversario(rs.getString(5)+"/"+rs.getString(6));
					aniversariantes.add(aniversariante);
					
				} 
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){}
			}
		}
	}
	public static String convertMounth(int mes){
		switch(mes){
		case 1:
			return "JANEIRO";
		case 2:
			return "FEVEREIRO";
		case 3:
			return "MARÇO";
		case 4:
			return "ABRIL";
		case 5:
			return "MAIO";
		case 6:
			return "JUNHO";
		case 7:
			return "JULHO";
		case 8:
			return "AGOSTO";
		case 9:
			return "SETEMBRO";
		case 10:
			return "OUTUBRO";
		case 11:
			return "NOVEMBRO";
		case 12:
			return "DEZEMBRO";
		}
		return "";
	}
	
}