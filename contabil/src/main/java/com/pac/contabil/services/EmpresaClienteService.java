package com.pac.contabil.services;

import com.pac.contabil.models.EmpresaCliente;
import com.pac.contabil.repositories.EmpresaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaClienteService {

    @Autowired
    private EmpresaClienteRepository repository;

    public List<EmpresaCliente> findAll() {
        return repository.findAll();
    }

    public EmpresaCliente save(EmpresaCliente empresa) {
        return repository.save(empresa);
    }
}
