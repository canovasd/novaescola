package com.nova.escola.prova.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nova.escola.prova.ProvaApplication;
import com.nova.escola.prova.domain.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProvaApplication.class)
@WebAppConfiguration
class ClienteRestControllerTest {

    private static final String NOME = "Canovas";

    private static final String EMAIL = "marciocanovas@gmail.com";

    private static final String DATA = "05/02/2020";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Transactional
    @Test
    public void inserirCliente() throws Exception {
        MvcResult mvcResult = insercao();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    private MvcResult insercao() throws Exception {
        String uri = "/cliente/";
        return mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("nome", NOME)
                .param("email", EMAIL)
                .param("data", DATA)
        ).andReturn();
    }

    @Transactional
    @Test
    public void listarClientes() throws Exception {
        this.insercao();

        String uri = "/cliente/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        Assertions.assertTrue(content.contains("\"total\":1"));
//        ClienteSearchResult clienteSearchResult = this.mapFromJson(content, ClienteSearchResult.class); -- Erro ao serializar =/
//        Assertions.assertEquals(clienteSearchResult.getTotal(), 1);
//        assertEquals(NOME, clienteSearchResult.getLista().get(0).getNome());
    }

    @Transactional
    @Test
    public void getCliente() throws Exception {
        String insertcaoContent = this.insercao().getResponse().getContentAsString();
        Cliente clienteInserido = this.mapFromJson(insertcaoContent, Cliente.class);

        String uri = "/cliente/" + clienteInserido.getId();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Cliente cliente = this.mapFromJson(content, Cliente.class);
        assertEquals(clienteInserido.getId(), cliente.getId());
        assertEquals(NOME, cliente.getNome());
    }

    @Transactional
    @Test
    public void atualizarCliente() throws Exception {
        this.insercao();
        String uri = "/cliente/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).param("id", "1")
                .content("{\"nome\":\"Outra Pessoa\"}").contentType("application/json")
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Cliente cliente = this.mapFromJson(content, Cliente.class);
        assertEquals(new Long(1), cliente.getId());
        assertEquals("Outra Pessoa", cliente.getNome());
    }

    @Transactional
    @Test
    public void deletarCliente() throws Exception {
        String insertcaoContent = this.insercao().getResponse().getContentAsString();
        Cliente clienteInserido = this.mapFromJson(insertcaoContent, Cliente.class);

        String uri = "/cliente/" + clienteInserido.getId();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).param("id", "6" + clienteInserido.getId())
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    private <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}