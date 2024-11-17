package com.pac.contabil.controllers;

import com.pac.contabil.models.NotaFiscal;
import com.pac.contabil.repositories.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @PostMapping("/inserir")
    public ResponseEntity<String> inserir(@RequestBody NotaFiscal notaFiscal) {
        notaFiscalRepository.inserir(notaFiscal);
        return ResponseEntity.ok("Nota fiscal inserida com sucesso");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NotaFiscal>> listar() {
        return ResponseEntity.ok(notaFiscalRepository.listar());
    }

    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<NotaFiscal> buscar(@PathVariable String codigo) {
        NotaFiscal nota = notaFiscalRepository.buscarPorCodigo(codigo);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody NotaFiscal notaFiscal) {
        notaFiscalRepository.atualizar(notaFiscal);
        return ResponseEntity.ok("Nota fiscal atualizada com sucesso");
    }

    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<String> deletar(@PathVariable String codigo) {
        notaFiscalRepository.deletar(codigo);
        return ResponseEntity.ok("Nota fiscal deletada com sucesso");
    }
}

