package com.nova.escola.prova.service;

import com.nova.escola.prova.domain.Cliente;
import com.nova.escola.prova.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Classe responsável pela lógica aplicada
 * Se comunica com o repositório para leitura/gravação de dados
 */
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

    public ClienteSearchResult getClientes(int pagina, int limite) {
        Page<Cliente> page = repo.findAll(PageRequest.of(pagina - 1, limite));
        return new ClienteSearchResult(this.repo.count(), page.getContent());
    }

    public Cliente getCliente(Long id) {
        return this.repo.findById(id).orElse(null);
    }

    public Cliente atualizarCliente(Long id, Cliente novosDados) {
        Optional<Cliente> opt = this.repo.findById(id);
        if (opt.isPresent()) {
            Cliente cliente = opt.get();
            cliente.setNome(novosDados.getNome());
            cliente.setEmail(novosDados.getEmail());
            cliente.setDataNascimento(novosDados.getDataNascimento());
            return this.repo.save(cliente);
        }
        throw new RuntimeException(String.format("Cliente com o id %s não encontrado", id));
    }

    public void deletarCliente(Long id) {
        Optional<Cliente> opt = this.repo.findById(id);
        if (opt.isPresent()) {
            this.repo.delete(opt.get());
        }
    }

    public void setRepo(ClienteRepository repo) {
        this.repo = repo;
    }
}