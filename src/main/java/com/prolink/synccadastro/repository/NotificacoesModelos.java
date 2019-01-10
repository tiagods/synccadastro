package com.prolink.synccadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolink.synccadastro.model.NotificacaoModelo;

@Repository
public interface NotificacoesModelos extends JpaRepository<NotificacaoModelo, Long>{
}
