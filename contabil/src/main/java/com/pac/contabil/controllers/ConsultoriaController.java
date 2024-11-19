package com.pac.contabil.controllers;

import com.pac.contabil.models.Consultoria;
import com.pac.contabil.repositories.ConsultoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultorias")
public class ConsultoriaController {

    @Autowired
    private ConsultoriaRepository consultoriaRepository;

    @PostMapping
    public ResponseEntity<String> inserir(@RequestBody Consultoria consultoria) {
        consultoriaRepository.inserir(consultoria);
        return new ResponseEntity<>("Consultoria inserida com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Consultoria>> listar() {
        List<Consultoria> consultorias = consultoriaRepository.listar();
        return new ResponseEntity<>(consultorias, HttpStatus.OK);
    }

    @GetMapping("/{codConsultoria}")
    public ResponseEntity<Consultoria> buscarPorCodConsultoria(@PathVariable String codConsultoria) {
        Consultoria consultoria = consultoriaRepository.buscarPorCodConsultoria(codConsultoria);
        if (consultoria != null) {
            return new ResponseEntity<>(consultoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{codConsultoria}")
    public ResponseEntity<String> atualizar(@PathVariable String codConsultoria, @RequestBody Consultoria consultoria) {
        consultoria.setCodConsultoria(codConsultoria);
        consultoriaRepository.atualizar(consultoria);
        return new ResponseEntity<>("Consultoria atualizada com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/{codConsultoria}")
    public ResponseEntity<String> deletar(@PathVariable String codConsultoria) {
        consultoriaRepository.deletar(codConsultoria);
        return new ResponseEntity<>("Consultoria deletada com sucesso!", HttpStatus.NO_CONTENT);
    }
}

