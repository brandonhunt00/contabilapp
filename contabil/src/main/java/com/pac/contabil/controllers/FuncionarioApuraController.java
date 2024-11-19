package com.pac.contabil.controllers;

import com.pac.contabil.models.FuncionarioApura;
import com.pac.contabil.services.FuncionarioApuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios-apura")
public class FuncionarioApuraController {

    @Autowired
    private FuncionarioApuraService funcionarioApuraService;

    // Endpoint para listar todos os funcionários
    @GetMapping
    public List<FuncionarioApura> listarFuncionarios() {
        return funcionarioApuraService.listar();
    }

    // Endpoint para buscar um funcionário pelo CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioApura> buscarFuncionario(@PathVariable String cpf) {
        FuncionarioApura funcionario = funcionarioApuraService.buscarPorCpf(cpf);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para cadastrar um novo funcionário
    @PostMapping
    public ResponseEntity<Void> cadastrarFuncionario(@RequestBody FuncionarioApura funcionarioApura) {
        try {
            funcionarioApuraService.inserir(funcionarioApura);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint para deletar um funcionário
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable String cpf) {
        FuncionarioApura funcionario = funcionarioApuraService.buscarPorCpf(cpf);
        if (funcionario != null) {
            funcionarioApuraService.deletar(cpf);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
