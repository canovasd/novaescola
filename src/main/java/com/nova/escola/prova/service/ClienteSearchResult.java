package com.nova.escola.prova.service;

import com.nova.escola.prova.domain.Cliente;

import java.util.List;

/**
 * Representa o resultado de uma busca, sendo  o total o número de clientes disponível no BD
 * e a lista o resultado de acordo com a paginação
 */
public class ClienteSearchResult {

    private long total;

    private List<Cliente> lista;

    public ClienteSearchResult(long total, List<Cliente> lista) {
        this.total = total;
        this.lista = lista;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }
}
