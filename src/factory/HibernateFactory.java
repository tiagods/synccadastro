package factory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.ConfigBean;

public class HibernateFactory {
	//recebendo a sessao
	public Session getSession(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		return sf.getCurrentSession();
	}
	//returnando a lista de objetos da sessao
	public List<Object> getList(Session session, Object object){
		return session.createQuery("from "+object).list();
	}
	
	//salvar ou atualiza
	public void saveOrUpdateSession(Session session, Object object){
		session.beginTransaction();
		session.saveOrUpdate(object);
		session.getTransaction().commit();
	}
	
	//fechando a sessao
	public void closeSession(Session session){
		session.close();
	}

}
