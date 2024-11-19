package com.pac.contabil.controllers;

import com.pac.contabil.models.DeclaracaoFiscal;
import com.pac.contabil.repositories.DeclaracaoFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/declaracoes-fiscais")
public class DeclaracaoFiscalController {

    @Autowired
    private DeclaracaoFiscalRepository declaracaoFiscalRepository;

    @PostMapping
    public ResponseEntity<String> inserir(@RequestBody DeclaracaoFiscal declaracaoFiscal) {
        declaracaoFiscalRepository.inserir(declaracaoFiscal);
        return new ResponseEntity<>("Declaração Fiscal inserida com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeclaracaoFiscal>> listar() {
        List<DeclaracaoFiscal> declaracoesFiscais = declaracaoFiscalRepository.listar();
        return new ResponseEntity<>(declaracoesFiscais, HttpStatus.OK);
    }

    @GetMapping("/{codDeclaracaoFiscal}")
    public ResponseEntity<DeclaracaoFiscal> buscarPorCodDeclaracaoFiscal(@PathVariable String codDeclaracaoFiscal) {
        DeclaracaoFiscal declaracaoFiscal = declaracaoFiscalRepository.buscarPorCodDeclaracaoFiscal(codDeclaracaoFiscal);
        if (declaracaoFiscal != null) {
            return new ResponseEntity<>(declaracaoFiscal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{codDeclaracaoFiscal}")
    public ResponseEntity<String> atualizar(@PathVariable String codDeclaracaoFiscal, @RequestBody DeclaracaoFiscal declaracaoFiscal) {
        declaracaoFiscal.setCodDeclaracaoFiscal(codDeclaracaoFiscal);
        declaracaoFiscalRepository.atualizar(declaracaoFiscal);
        return new ResponseEntity<>("Declaração Fiscal atualizada com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/{codDeclaracaoFiscal}")
    public ResponseEntity<String> deletar(@PathVariable String codDeclaracaoFiscal) {
        declaracaoFiscalRepository.deletar(codDeclaracaoFiscal);
        return new ResponseEntity<>("Declaração Fiscal deletada com sucesso!", HttpStatus.NO_CONTENT);
    }
}

