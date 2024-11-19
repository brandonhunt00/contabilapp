package com.pac.contabil.controllers;

import com.pac.contabil.models.LucroReal;
import com.pac.contabil.repositories.LucroRealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lucro-real")
public class LucroRealController {

    @Autowired
    private LucroRealRepository lucroRealRepository;

    // Inserir novo lucro real
    @PostMapping("/inserir")
    public ResponseEntity<String> inserir(@RequestBody LucroReal lucroReal) {
        try {
            lucroRealRepository.inserir(lucroReal);
            return ResponseEntity.status(HttpStatus.CREATED).body("Lucro Real inserido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir Lucro Real");
        }
    }

    // Listar todos os lucros reais
    @GetMapping("/listar")
    public ResponseEntity<List<LucroReal>> listar() {
        List<LucroReal> lucros = lucroRealRepository.listar();
        if (lucros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(lucros);
    }

    // Buscar lucro real por CNPJ
    @GetMapping("/buscar/{cnpj}")
    public ResponseEntity<LucroReal> buscar(@PathVariable String cnpj) {
        LucroReal lucroReal = lucroRealRepository.buscarPorCnpj(cnpj);
        if (lucroReal != null) {
            return ResponseEntity.ok(lucroReal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Atualizar lucro real
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody LucroReal lucroReal) {
        try {
            lucroRealRepository.atualizar(lucroReal);
            return ResponseEntity.ok("Lucro Real atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Lucro Real");
        }
    }

    // Deletar lucro real por CNPJ
    @DeleteMapping("/deletar/{cnpj}")
    public ResponseEntity<String> deletar(@PathVariable String cnpj) {
        try {
            if (lucroRealRepository.deletar(cnpj)) {
                return ResponseEntity.ok("Lucro Real deletado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lucro Real não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar Lucro Real");
        }
    }
}
