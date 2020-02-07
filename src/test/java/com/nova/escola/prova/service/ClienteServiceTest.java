package com.nova.escola.prova.service;

import com.nova.escola.prova.domain.Cliente;
import com.nova.escola.prova.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteServiceTest {

    private ClienteService service;

    private ClienteRepository repo;

    @BeforeEach
    public void prepare() {
        this.service = new ClienteService();
        this.repo = Mockito.mock(ClienteRepository.class);
        this.service.setRepo(this.repo);
    }

    @Test
    public void inserir() {
        Date data = new Date(2020, 2, 7);
        Cliente result = this.service.inserir("canovas", "marciocanovas@gmail.com", data);

        Mockito.verify(this.repo).save(result);
        assertEquals("canovas", result.getNome());
        assertEquals("marciocanovas@gmail.com", result.getEmail());
        assertEquals(data, result.getDataNascimento());
    }

    @Test
    public void getClientes() {
        Mockito.when(this.repo.findAll(PageRequest.of(0, 1))).thenReturn(Mockito.mock(Page.class));

        this.service.getClientes(1, 1);

        Mockito.verify(this.repo).count();
        Mockito.verify(this.repo).findAll(PageRequest.of(0, 1));
    }

    @Test
    public void atualizarCliente() {
        long id = 2l;
        String outroNome = "Outro Nome";
        String outroEmail = "outro@outroEmail.com";
        Date outraData = new Date(2022, 2, 2);

        Cliente camposAtualizados = new Cliente();
        camposAtualizados.setNome(outroNome);
        camposAtualizados.setEmail(outroEmail);
        camposAtualizados.setDataNascimento(outraData);

        Cliente cliente = new Cliente();
        Optional<Cliente> opt = Optional.of(cliente);
        Mockito.when(repo.findById(id)).thenReturn(opt);
        Mockito.when(repo.save(cliente)).thenReturn(cliente);

        Cliente result = this.service.atualizarCliente(id, camposAtualizados);
        Mockito.verify(this.repo).save(cliente);
        assertEquals(outroNome, result.getNome());
        assertEquals(outroEmail, result.getEmail());
        assertEquals(outraData, result.getDataNascimento());
    }

    @Test
    public void atualizarClienteInexistente() {
        long id = 2l;

        Cliente camposAtualizados = new Cliente();
        Optional<Cliente> opt = Optional.empty();
        Mockito.when(repo.findById(id)).thenReturn(opt);

        try {
            this.service.atualizarCliente(id, camposAtualizados);
            Assertions.fail("Esperada exceção");
        } catch (RuntimeException e) {
            assertEquals("Cliente com o id 2 não encontrado", e.getMessage());
        }
    }

    @Test
    public void deletarCliente() {
        long id = 2l;
        Cliente cliente = new Cliente();
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(cliente));
        this.service.deletarCliente(id);

        Mockito.verify(this.repo).delete(cliente);
    }

}