package factory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.CadastroBean;

public class IniciandoAniversario2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		
		List<Aniversariante> aniversariantes = new ArrayList<>();
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
			String hql = "FROM CadastroBean as c where c.DIA_NASC1 "
					+ "like :dayOfMounth "
					+ "and c.MES_NASC1 like :mounth";
			List<CadastroBean> object = (List<CadastroBean>)session.createQuery(hql)
					.setParameter("dayOfMounth", String.valueOf(d.getDayOfMonth()))
					.setParameter("mounth", convertMounth(d.getMonthValue())).getResultList();
			if(!object.isEmpty()){
				object.forEach(c->{
					Aniversariante aniversariante = new Aniversariante();
					aniversariante.setId(String.valueOf(c.getCOD()));
					aniversariante.setStatus(c.getSTATUS());
					aniversariante.setEmpresa(c.getEMPRESA());
					aniversariante.setNome(c.getNOME_SOCIO1());
					aniversariante.setAniversario(c.getDIA_NASC1()+"/"+c.getMES_NASC1());
					aniversariantes.add(aniversariante);
				});
			} 
			session.clear();
			hql = "FROM CadastroBean as c where c.DIA_NASC2 "
					+ "like :dayOfMounth "
					+ "and c.MES_NASC2 like :mounth";
			List<CadastroBean> object2 = (List<CadastroBean>)session.createQuery(hql)
					.setParameter("dayOfMounth", String.valueOf(d.getDayOfMonth()))
					.setParameter("mounth", convertMounth(d.getMonthValue())).getResultList();
			if(!object2.isEmpty()){
				object2.forEach(c->{
					Aniversariante aniversariante = new Aniversariante();
					aniversariante.setId(String.valueOf(c.getCOD()));
					aniversariante.setStatus(c.getSTATUS());
					aniversariante.setEmpresa(c.getEMPRESA());
					aniversariante.setNome(c.getNOME_SOC_2());
					aniversariante.setAniversario(c.getDIA_NASC2()+"/"+c.getMES_NASC2());
					aniversariantes.add(aniversariante);
				});
			}
			session.clear();
		}
		factory.closeSession(session);
		//				ps = con.prepareStatement("select COD, STATUS, EMPRESA, NOME_SOC_2, DIA_NASC2, MES_NASC2 FROM CLIENTE "
		//						+ "where DIA_NASC2 like ? and MES_NASC2 like ?");
		
		aniversariantes.forEach(c->{
			System.out.println("ID: "+c.getId()+"\t\tStatus: "+c.getStatus()+"\t\tEmpresa: "+c.getEmpresa()
			+"\t\tNome: "+c.getNome()+"\t\tAniversario: "+c.getAniversario());
		});
		System.out.println("\tTotal de registros: "+aniversariantes.size());
		System.exit(0);
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