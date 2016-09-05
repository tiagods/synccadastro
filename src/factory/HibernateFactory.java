package factory;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
	//recebendo a sessao
	public Session getSession(){
		SessionFactory fabrica = new Configuration()
        		.configure()
        		.buildSessionFactory();
		return fabrica.openSession();
	}
	//returnando a lista de objetos da sessao
	public List<Object> getList(Session session, Object object){
		return session.createQuery("from "+object).list();
	}
	//retornando apenas 1 objeto

	public Object getObject(Session session, Object object){
		return session.createQuery("from "+object).getFirstResult();
	}
	//salvar ou atualiza
	public String  saveOrUpdateSession(Session session, Object object){
		try{
			session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
			return "Salvo com sucesso";
		}catch(Exception e){
			return "Erro ao Salvar";
		}
	}
	
	//fechando a sessao
	public void closeSession(Session session){
		session.close();
	}

}
