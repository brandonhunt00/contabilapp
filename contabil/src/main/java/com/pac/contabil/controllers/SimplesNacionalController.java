package com.pac.contabil.controllers;

import com.pac.contabil.models.SimplesNacional;
import com.pac.contabil.services.SimplesNacionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simples-nacional")
public class SimplesNacionalController {

    @Autowired
    private SimplesNacionalService simplesNacionalService;

    // Endpoint para listar todos os registros de Simples Nacional
    @GetMapping
    public List<SimplesNacional> listarSimplesNacional() {
        return simplesNacionalService.listar();
    }

    // Endpoint para buscar um registro de Simples Nacional pelo CNPJ
    @GetMapping("/{cnpj}")
    public ResponseEntity<SimplesNacional> buscarSimplesNacional(@PathVariable String cnpj) {
        SimplesNacional simplesNacional = simplesNacionalService.buscarPorCnpj(cnpj);
        if (simplesNacional != null) {
            return ResponseEntity.ok(simplesNacional);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para cadastrar um novo registro de Simples Nacional
    @PostMapping
    public ResponseEntity<Void> cadastrarSimplesNacional(@RequestBody SimplesNacional simplesNacional) {
        try {
            simplesNacionalService.inserir(simplesNacional);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint para deletar um registro de Simples Nacional pelo CNPJ
    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deletarSimplesNacional(@PathVariable String cnpj) {
        SimplesNacional simplesNacional = simplesNacionalService.buscarPorCnpj(cnpj);
        if (simplesNacional != null) {
            simplesNacionalService.deletar(cnpj);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
