package com.prolink.synccadastro.repository.helpers.queries;

import java.util.List;

import com.prolink.synccadastro.model.Notificacao;
import com.prolink.synccadastro.model.NotificacaoEnvio;

public interface NotificacoesQueries {

	List<NotificacaoEnvio> notificacoesPendentes();

	List<Notificacao> buscarHoje();

	boolean verSeExiste(Notificacao notificacao);

}
