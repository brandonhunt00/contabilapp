package com.pac.contabil.controllers;

import com.pac.contabil.models.Imposto;
import com.pac.contabil.repositories.ImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imposto")
public class ImpostoController {

    @Autowired
    private ImpostoRepository impostoRepository;

    // Inserir um novo imposto
    @PostMapping("/inserir")
    public ResponseEntity<String> inserir(@RequestBody Imposto imposto) {
        try {
            impostoRepository.inserir(imposto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Imposto inserido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir o imposto");
        }
    }

    // Listar todos os impostos
    @GetMapping("/listar")
    public ResponseEntity<List<Imposto>> listar() {
        List<Imposto> impostos = impostoRepository.listar();
        if (impostos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(impostos);
    }

    // Buscar um imposto por código
    @GetMapping("/buscar/{codImposto}")
    public ResponseEntity<Imposto> buscar(@PathVariable String codImposto) {
        Imposto imposto = impostoRepository.buscarPorCodigo(codImposto);
        if (imposto != null) {
            return ResponseEntity.ok(imposto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Atualizar um imposto
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody Imposto imposto) {
        try {
            impostoRepository.atualizar(imposto);
            return ResponseEntity.ok("Imposto atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o imposto");
        }
    }

    // Deletar um imposto por código
    @DeleteMapping("/deletar/{codImposto}")
    public ResponseEntity<String> deletar(@PathVariable String codImposto) {
        try {
            if (impostoRepository.deletar(codImposto)) {
                return ResponseEntity.ok("Imposto deletado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imposto não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar o imposto");
        }
    }
}
