package model;

import java.util.List;

import org.hibernate.Session;

import factory.HibernateFactory;

public class ConfigDao {

	public ConfigBean readConfigurations(){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		List<Object> object = factory.getList(session, ConfigBean.class);
		int last = object.size() - 1; 
		return (ConfigBean)object.get(last);
	}

}
