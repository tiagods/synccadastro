package com.prolink.synccadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolink.synccadastro.model.NotificacaoEnvio;

@Repository
public interface NotificacoesEnvios extends JpaRepository<NotificacaoEnvio, Long>{
}
