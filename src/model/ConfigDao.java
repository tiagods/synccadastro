package model;

import java.util.List;

import org.hibernate.Session;

import factory.HibernateFactory;

public class ConfigDao {

	public static void main(String[] args){
		new ConfigDao();
	}
	public ConfigBean readConfigurations(){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		List<Object> object = factory.getList(session, "ConfigBean");
		factory.closeSession(session);
		int last = object.size() - 1; 
		return (ConfigBean)object.get(last);
	}

}
