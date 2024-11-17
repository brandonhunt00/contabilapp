package com.pac.contabil.controllers;

import com.pac.contabil.models.EmpresaCliente;
import com.pac.contabil.repositories.EmpresaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empresa")
public class EmpresaClienteController {

    @Autowired
    private EmpresaClienteRepository empresaClienteRepository;

    @PostMapping("/inserir")
    public ResponseEntity<String> inserir(@RequestBody EmpresaCliente empresaCliente) {
        empresaClienteRepository.inserir(empresaCliente);
        return ResponseEntity.ok("Empresa inserida com sucesso");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmpresaCliente>> listar() {
        return ResponseEntity.ok(empresaClienteRepository.listar());
    }

    @GetMapping("/buscar/{cnpj}")
    public ResponseEntity<EmpresaCliente> buscar(@PathVariable String cnpj) {
        EmpresaCliente empresa = empresaClienteRepository.buscarPorCnpj(cnpj);
        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody EmpresaCliente empresaCliente) {
        empresaClienteRepository.atualizar(empresaCliente);
        return ResponseEntity.ok("Empresa atualizada com sucesso");
    }

    @DeleteMapping("/deletar/{cnpj}")
    public ResponseEntity<String> deletar(@PathVariable String cnpj) {
        empresaClienteRepository.deletar(cnpj);
        return ResponseEntity.ok("Empresa deletada com sucesso");
    }

}

