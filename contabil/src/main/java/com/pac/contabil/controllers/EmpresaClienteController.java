package com.pac.contabil.controllers;

import com.pac.contabil.models.EmpresaCliente;
import com.pac.contabil.services.EmpresaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaClienteController {

    @Autowired
    private EmpresaClienteService empresaClienteService;

    @GetMapping
    public List<EmpresaCliente> getAllEmpresas() {
        return empresaClienteService.findAll();
    }

    @PostMapping
    public EmpresaCliente createEmpresa(@RequestBody EmpresaCliente empresa) {
        return empresaClienteService.save(empresa);
    }
}
