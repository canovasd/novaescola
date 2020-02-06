package com.nova.escola.prova.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nova.escola.prova.domain.Cliente;
import com.nova.escola.prova.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("cliente")
public class ClientRestController {

    @Autowired
    private ClienteService clientService;

    @PostMapping
    public ResponseEntity postPlace(@RequestParam String nome,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date data) {
        Cliente clienteInserido = this.clientService.inserir(nome, email, data);
        return new ResponseEntity(clienteInserido, CREATED);
    }

}
