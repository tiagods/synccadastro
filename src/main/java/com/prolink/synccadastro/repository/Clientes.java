package com.prolink.synccadastro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolink.synccadastro.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer>{
	void salvarTudo(List<Cliente> clientes);
	List<Cliente> listarAniversariantes(List<LocalDate> list, List<String> filtroStatus, String param1, String param2);
}
