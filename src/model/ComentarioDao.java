package model;

import java.util.List;

import org.hibernate.Session;

import factory.HibernateFactory;

public class ComentarioDao {
	
	private StringBuilder builder = new StringBuilder();
	long tempoGravacaoBD=0;
	
	public String insertOrUpdate(List<ComentarioBean> lista){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		long inicio = System.currentTimeMillis();
		for(int i=0; i<lista.size(); i++){
			ComentarioBean bean = lista.get(i);
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
		long fim = System.currentTimeMillis();
		tempoGravacaoBD = fim - inicio;
		factory.closeSession(session);
		System.out.println("Terminei os comentarios");
		return builder.toString();
	}
	public long getTempo(){
		return this.tempoGravacaoBD;
	}
}
