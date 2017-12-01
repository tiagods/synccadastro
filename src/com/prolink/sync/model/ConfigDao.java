package com.prolink.sync.model;

import java.util.List;

import org.hibernate.Session;

import com.prolink.sync.factory.HibernateFactory;

public class ConfigDao {
//realizando consulta e retornando o ultimo objeto

	public ConfigBean readConfigurations(){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		List<Object> object = factory.getList(session, "ConfigBean");
		factory.closeSession(session);
		int last = object.size() - 1; 
		return (ConfigBean)object.get(last);
	}

}
