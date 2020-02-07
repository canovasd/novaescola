package com.nova.escola.prova.controller;

import com.nova.escola.prova.domain.Cliente;
import com.nova.escola.prova.service.ClienteSearchResult;
import com.nova.escola.prova.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Classe responsável pela interface com o usuário
 * <p>
 * Especifica as chamadas Rest possíveis, formata as entradas e seta valores default
 */
@RestController
@RequestMapping("cliente")
public class ClienteRestController {

    private static final String FORMATO_DATA = "dd/MM/yyyy";

    private static final String PADRAO_PAGINA = "1";

    private static final String PADRAO_LIMITE = "10";

    @Autowired
    private ClienteService clienteService;

    /**
     * Insere um novo cliente de acordo com os parâmetros informados e retorna HTTP Status 201 e o valor inserido em caso de sucesso
     */
    @PostMapping
    public ResponseEntity inserirCliente(@RequestParam String nome,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) @DateTimeFormat(pattern = FORMATO_DATA) Date data) {
        Cliente clienteInserido = this.clienteService.inserir(nome, email, data);
        return new ResponseEntity(clienteInserido, CREATED);
    }

    /**
     * Lista os clientes no Banco de Dados, possibilitando paginação
     *
     * @param pagina - Opcional - default 1 - Página do resultado
     * @param limite - Opcional - default 10 - Limite de resultados por página
     * @return
     */
    @GetMapping(produces = "application/json")
    public ClienteSearchResult listarClientes(@RequestParam(required = false, defaultValue = PADRAO_PAGINA)
                                                     int pagina, @RequestParam(required = false, defaultValue = PADRAO_LIMITE) int limite) {
        return this.clienteService.getClientes(pagina, limite);
    }

    /**
     * Retorna as informações referentes ao Cliente de ID informado
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    public Cliente getCliente(@PathVariable Long id) {
        return this.clienteService.getCliente(id);
    }

    /**
     * Atualiza o Cliente de ID informado no parâmetro utilizando os dados do JSON informado no Body
     */
    @PutMapping(produces = "application/json", consumes = "application/json")
    public Cliente atualizarCliente(@RequestParam Long id, @RequestBody Cliente novosDados) {
        return this.clienteService.atualizarCliente(id, novosDados);
    }

    /**
     * Remove o Cliente de ID informado
     */
    @DeleteMapping(path = "/{id}")
    public void deletarCliente(@PathVariable Long id) {
        this.clienteService.deletarCliente(id);
    }

}
