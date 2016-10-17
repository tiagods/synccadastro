package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import factory.HibernateFactory;

public class AniversarianteDao {
	static AniversarianteDao instance;
	
	LocalDate segunda=null;
	LocalDate terca=null;
	LocalDate quarta=null;
	LocalDate quinta=null;
	LocalDate sexta=null;
	LocalDate sabado=null;
	LocalDate domingo=null;
	
	public static AniversarianteDao getInstance(){
		if(instance==null)
			instance = new AniversarianteDao();
		return instance;
	}
	
	public List<Aniversariante> receberAniversariantes(){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		List<Aniversariante> aniversariantes = new ArrayList<>();
		List<LocalDate> lista = new ArrayList<LocalDate>();
		LocalDate hoje = LocalDate.now();
		
		tratarDias(hoje);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lista.add(segunda);
		lista.add(terca);
		lista.add(quarta);
		lista.add(quinta);
		lista.add(sexta);
		lista.add(sabado);
		lista.add(domingo);
		
		for(LocalDate d : lista){
			
			String hql = "FROM CadastroBean as c where c.DIA_NASC1 "
					+ "= :dayOfMounth "
					+ "and c.MES_NASC1 = :mounth";
			List<CadastroBean> object = (List<CadastroBean>)session.createQuery(hql)
					.setParameter("dayOfMounth", String.valueOf(d.getDayOfMonth()))
					.setParameter("mounth", convertMounth(d.getMonthValue()).toLowerCase()).getResultList();
			if(!object.isEmpty()){
				object.forEach(c->{
					Aniversariante aniversariante = new Aniversariante();
					aniversariante.setId(String.valueOf(c.getCOD()));
					aniversariante.setStatus(c.getSTATUS());
					aniversariante.setEmpresa(c.getEMPRESA());
					aniversariante.setNome(c.getNOME_SOCIO1());
					aniversariante.setAniversario(c.getDIA_NASC1()+"/"+convertMounth(c.getMES_NASC1()));
					aniversariante.setEmail(c.getEMAIL_SOC_1());
					String listaFones = "";
					if(c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
						listaFones = c.getDDD1CEL()+" "+c.getCELULAR();
					else if(!c.getFONECOML1().trim().equals("") && c.getCELULAR().trim().equals(""))
						listaFones = c.getDDD1COML()+" "+c.getFONECOML1();
					else if(!c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
						listaFones = c.getDDD1COML()+" "+c.getFONECOML1()+","+c.getDDD1CEL()+" "+c.getCELULAR();
					aniversariante.setTelefone(listaFones);
					aniversariantes.add(aniversariante);
				});
			} 
			session.clear();
			hql = "FROM CadastroBean as c where c.DIA_NASC2 "
					+ "= :dayOfMounth "
					+ "and c.MES_NASC2 = :mounth";
			List<CadastroBean> object2 = (List<CadastroBean>)session.createQuery(hql)
					.setParameter("dayOfMounth", String.valueOf(d.getDayOfMonth()))
					.setParameter("mounth", convertMounth(d.getMonthValue()).toLowerCase()).getResultList();
			if(!object2.isEmpty()){
				object2.forEach(c->{
					Aniversariante aniversariante = new Aniversariante();
					aniversariante.setId(String.valueOf(c.getCOD()));
					aniversariante.setStatus(c.getSTATUS());
					aniversariante.setEmpresa(c.getEMPRESA());
					aniversariante.setNome(c.getNOME_SOC_2());
					aniversariante.setAniversario(c.getDIA_NASC2()+"/"+convertMounth(c.getMES_NASC2()));
					aniversariante.setEmail(c.getEMAIL_SOC_1());
					String listaFones2 = "";
					if(c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
						listaFones2 = c.getDDD2CEL()+" "+c.getCELULAR2();
					else if(!c.getFONECOM2().trim().equals("") && c.getCELULAR2().trim().equals(""))
						listaFones2 = c.getDDD2COML()+" "+c.getFONECOM2();
					else if(!c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
						listaFones2 = c.getDDD2COML()+" "+c.getFONECOM2()+","+c.getDDD2CEL()+" "+c.getCELULAR2();
					aniversariante.setTelefone(listaFones2);
					aniversariantes.add(aniversariante);
				});
			}
			session.clear();
		}
		factory.closeSession(session);
		return aniversariantes;
	}
	public String convertMounth(int mes){
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
	public String convertMounth(String mes){
		switch(mes.toUpperCase()){
		case "JANEIRO":
			return "01";
		case "FEVEREIRO":
			return "02";
		case "MARÇO":
			return "03";
		case "ABRIL":
			return "04";
		case "MAIO":
			return "05";
		case "JUNHO":
			return "06";
		case "JULHO":
			return "07";
		case "AGOSTO":
			return "08";
		case "SETEMBRO":
			return "09";
		case "OUTUBRO":
			return "10";
		case "NOVEMBRO":
			return "11";
		case "DEZEMBRO":
			return "12";
		}
		return "";
	}
	public void tratarDias(LocalDate hoje){
		switch(hoje.getDayOfWeek()){
		case MONDAY:
			segunda = hoje;
			terca = hoje.plusDays(1);
			quarta = hoje.plusDays(2);
			quinta = hoje.plusDays(3);
			sexta = hoje.plusDays(4);
			sabado = hoje.plusDays(5);
			domingo = hoje.plusDays(6);
			break;
		case TUESDAY:
			segunda = hoje.plusDays(-1);
			terca = hoje;
			quarta = hoje.plusDays(1);
			quinta = hoje.plusDays(2);
			sexta = hoje.plusDays(3);
			sabado = hoje.plusDays(4);
			domingo = hoje.plusDays(5);
			break;
		case WEDNESDAY:
			segunda = hoje.plusDays(-2);
			terca = hoje.plusDays(-1);
			quarta = hoje;
			quinta = hoje.plusDays(1);
			sexta = hoje.plusDays(2);
			sabado = hoje.plusDays(3);
			domingo = hoje.plusDays(4);
			break;
		case THURSDAY:
			segunda = hoje.plusDays(-3);
			terca = hoje.plusDays(-2);
			quarta = hoje.plusDays(-1);
			quinta = hoje;
			sexta = hoje.plusDays(1);
			sabado = hoje.plusDays(2);
			domingo = hoje.plusDays(3);
			break;
		case FRIDAY:
			segunda = hoje.plusDays(-4);
			terca = hoje.plusDays(-3);
			quarta = hoje.plusDays(-2);
			quinta = hoje.plusDays(-1);
			sexta = hoje;
			sabado = hoje.plusDays(1);
			domingo = hoje.plusDays(2);
			break;
		case SATURDAY:
			segunda = hoje.plusDays(-5);
			terca = hoje.plusDays(-4);
			quarta = hoje.plusDays(-3);
			quinta = hoje.plusDays(-2);
			sexta = hoje.plusDays(-1);
			sabado = hoje;
			domingo = hoje.plusDays(1);
			break;
		case SUNDAY:
			segunda = hoje.plusDays(-6);
			terca = hoje.plusDays(-5);
			quarta = hoje.plusDays(-4);
			quinta = hoje.plusDays(-3);
			sexta = hoje.plusDays(-2);
			sabado = hoje.plusDays(-1);
			domingo = hoje;
			break;
	}		
	}
	
}