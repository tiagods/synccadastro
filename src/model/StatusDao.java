package model;

import org.hibernate.Session;

import factory.HibernateFactory;

public class StatusDao {

	public void salvar(Status bean){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		factory.saveOrUpdateSession(session, bean);
		factory.closeSession(session);
	}
}
