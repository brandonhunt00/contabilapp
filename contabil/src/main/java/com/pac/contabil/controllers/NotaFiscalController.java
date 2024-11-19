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

    @PostMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody NotaFiscal notaFiscal) {
        notaFiscalRepository.atualizar(notaFiscal);
        return ResponseEntity.ok("Nota fiscal atualizada com sucesso");
    }

    @GetMapping("/buscar/{codNota}/{cnpj}")
    public ResponseEntity<NotaFiscal> buscar(@PathVariable String codNota, @PathVariable String cnpj) {
        NotaFiscal nota = notaFiscalRepository.buscarPorCodigoECnpj(codNota, cnpj);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deletar/{codNota}/{cnpj}")
    public ResponseEntity<String> deletar(@PathVariable String codNota, @PathVariable String cnpj) {
        notaFiscalRepository.deletar(codNota, cnpj);
        return ResponseEntity.ok("Nota fiscal deletada com sucesso");
    }
}

