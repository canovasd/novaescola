package com.nova.escola.prova.repository;

import com.nova.escola.prova.domain.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 * Classe responsável por prover métodos de criação/leitura/alteração/deleção de Clientes
 * Afeta o BD
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
