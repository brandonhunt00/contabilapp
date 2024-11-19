package com.pac.contabil.services;

import com.pac.contabil.models.SimplesNacional;
import com.pac.contabil.repositories.SimplesNacionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimplesNacionalService {

    @Autowired
    private SimplesNacionalRepository simplesNacionalRepository;

    // Inserir um novo registro de Simples Nacional
    public void inserir(SimplesNacional simplesNacional) {
        simplesNacionalRepository.inserir(simplesNacional);
    }

    // Listar todos os registros de Simples Nacional
    public List<SimplesNacional> listar() {
        return simplesNacionalRepository.listar();
    }

    // Buscar um registro de Simples Nacional pelo CNPJ
    public SimplesNacional buscarPorCnpj(String cnpj) {
        return simplesNacionalRepository.buscarPorCnpj(cnpj);
    }

    // Deletar um registro de Simples Nacional
    public void deletar(String cnpj) {
        simplesNacionalRepository.deletar(cnpj);
    }
}
