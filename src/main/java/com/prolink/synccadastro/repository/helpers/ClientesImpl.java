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
import com.prolink.synccadastro.util.LocalDateConversor;

public class ClientesImpl {
	Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private LocalDateConversor conversores;
	
	//a anotação transactional informa que determinado metodo se encarregara de abrir a transação
	@Transactional
	public void salvarTudo(List<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			try {
			Cliente cliente = clientes.get(i);
			//ClienteComentario com = cliente.getComentario();
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
	public List<Cliente> listarAniversariantes(List<LocalDate> list, List<String> filtroStatus, String dia,
			String mes) {
		List<Cliente> listas = new ArrayList<>();
		for (LocalDate d : list) {
			List<Cliente> object = em.unwrap(Session.class).createCriteria(Cliente.class)
					.add(Restrictions.ilike(dia, String.valueOf(d.getDayOfMonth())))
					.add(Restrictions.ilike(mes, conversores.convertMounth(d.getMonthValue())))
					.add(Restrictions.not(Restrictions.in("STATUS", filtroStatus)))
					.list();
			listas.addAll(object);
		}
		return listas;
	}
}
