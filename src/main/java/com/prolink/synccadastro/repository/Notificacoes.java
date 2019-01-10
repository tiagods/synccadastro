package com.prolink.synccadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolink.synccadastro.model.Notificacao;
import com.prolink.synccadastro.repository.helpers.queries.NotificacoesQueries;

@Repository
public interface Notificacoes extends JpaRepository<Notificacao, Long>,NotificacoesQueries {

}
