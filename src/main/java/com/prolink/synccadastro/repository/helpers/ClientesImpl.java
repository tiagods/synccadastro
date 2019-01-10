package com.prolink.synccadastro.repository.helpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.model.ClienteComentario;
import com.prolink.synccadastro.util.LocalDateConversor;

public class ClientesImpl {

	Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private LocalDateConversor conversores;
	
	//a anotação transactional informa que determinado metodo se encarregara de abrir a transação
	@Transactional
	public void save(List<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			try {
			Cliente cliente = clientes.get(i);
			ClienteComentario com = cliente.getComentario();
			if(cliente.getCOD()!=0)
				continue;
			Cliente cli = em.find(Cliente.class, cliente.getCOD());
			if(cli==null) {
				em.persist(cliente);
			}
			else {
				em.merge(cliente);
			}
			}catch(Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Cliente> listarAniversariantes(List<LocalDate> list, List<String> filtroStatus, String param1,
			String param2) {
		List<Cliente> listas = new ArrayList<>();
		for (LocalDate d : list) {
			Criteria criteria = em.unwrap(Session.class).createCriteria(Cliente.class);
			criteria.add(Restrictions.ilike(param1, String.valueOf(d.getDayOfMonth())));
			criteria.add(Restrictions.ilike(param2, conversores.convertMounth(d.getMonthValue())));
			criteria.add(Restrictions.in("STATUS", filtroStatus));
			List<Cliente> object = (List<Cliente>) criteria.list();
			listas.addAll(object);
		}
		return listas;
	}
}
