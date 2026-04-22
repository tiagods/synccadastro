package com.prolink.synccadastro.repository.helpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.util.LocalDateConverter;

public class ClientesImpl {
    Logger logger = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void salvarTudo(List<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            try {
                Cliente cliente = clientes.get(i);
                if (cliente.getCOD() != 0)
                    continue;
                Cliente cli = em.find(Cliente.class, cliente.getCOD());
                if (cli == null) {
                    em.persist(cliente);
                } else {
                    em.merge(cliente);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public List<Cliente> listarAniversariantes(List<LocalDate> list, List<String> filtroStatus,
            String diaField, String mesField) {
        List<Cliente> listas = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        for (LocalDate d : list) {
            CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
            Root<Cliente> root = cq.from(Cliente.class);

            Predicate diaPredicate = cb.like(
                    cb.lower(root.get(diaField)),
                    String.valueOf(d.getDayOfMonth()).toLowerCase());
            Predicate mesPredicate = cb.like(
                    cb.lower(root.get(mesField)),
                    LocalDateConverter.ConvertMonth(d.getMonthValue()).toLowerCase());
            Predicate statusPredicate = root.get("STATUS").in(filtroStatus).not();

            cq.select(root).where(cb.and(diaPredicate, mesPredicate, statusPredicate));
            listas.addAll(em.createQuery(cq).getResultList());
        }
        return listas;
    }
}
