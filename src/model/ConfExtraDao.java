package model;

import java.util.List;

import org.hibernate.Session;

import factory.HibernateFactory;

public class ConfExtraDao {
	public ConfExtraBean readConfigurations(){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		List<Object> object = factory.getList(session, "ConfExtraBean");
		factory.closeSession(session);
		int last = object.size() - 1; 
		return (ConfExtraBean)object.get(last);
	}
}
