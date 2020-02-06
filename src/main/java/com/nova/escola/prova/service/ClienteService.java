package com.nova.escola.prova.service;

import com.nova.escola.prova.domain.Cliente;
import com.nova.escola.prova.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente inserir(String nome, String email, Date dataNascimento) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setDataNascimento(dataNascimento);
        this.repo.save(cliente);
        return cliente;
    }

}
