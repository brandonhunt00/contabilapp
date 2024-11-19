package com.pac.contabil.controllers;

import com.pac.contabil.models.LucroPresumido;
import com.pac.contabil.repositories.LucroPresumidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lucro-presumido")
public class LucroPresumidoController {

    @Autowired
    private LucroPresumidoRepository lucroPresumidoRepository;

    // Inserir novo lucro presumido
    @PostMapping("/inserir")
    public ResponseEntity<String> inserir(@RequestBody LucroPresumido lucroPresumido) {
        try {
            lucroPresumidoRepository.inserir(lucroPresumido);
            return ResponseEntity.status(HttpStatus.CREATED).body("Lucro Presumido inserido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir Lucro Presumido");
        }
    }

    // Listar todos os lucros presumidos
    @GetMapping("/listar")
    public ResponseEntity<List<LucroPresumido>> listar() {
        List<LucroPresumido> lucros = lucroPresumidoRepository.listar();
        if (lucros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(lucros);
    }

    // Buscar lucro presumido por CNPJ
    @GetMapping("/buscar/{cnpj}")
    public ResponseEntity<LucroPresumido> buscar(@PathVariable String cnpj) {
        LucroPresumido lucroPresumido = lucroPresumidoRepository.buscarPorCnpj(cnpj);
        if (lucroPresumido != null) {
            return ResponseEntity.ok(lucroPresumido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Atualizar lucro presumido
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody LucroPresumido lucroPresumido) {
        try {
            lucroPresumidoRepository.atualizar(lucroPresumido);
            return ResponseEntity.ok("Lucro Presumido atualizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar Lucro Presumido");
        }
    }

    // Deletar lucro presumido por CNPJ
    @DeleteMapping("/deletar/{cnpj}")
    public ResponseEntity<String> deletar(@PathVariable String cnpj) {
        try {
            if (lucroPresumidoRepository.deletar(cnpj)) {
                return ResponseEntity.ok("Lucro Presumido deletado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lucro Presumido não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar Lucro Presumido");
        }
    }
}
