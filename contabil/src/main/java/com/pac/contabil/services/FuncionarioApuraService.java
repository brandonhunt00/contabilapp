package com.pac.contabil.services;

import com.pac.contabil.models.FuncionarioApura;
import com.pac.contabil.repositories.FuncionarioApuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioApuraService {

    @Autowired
    private FuncionarioApuraRepository funcionarioApuraRepository;

    // Inserir um novo funcionário na apuração
    public void inserir(FuncionarioApura funcionarioApura) {
        funcionarioApuraRepository.inserir(funcionarioApura);
    }

    // Listar todos os funcionários
    public List<FuncionarioApura> listar() {
        return funcionarioApuraRepository.listar();
    }

    // Buscar um funcionário pelo CPF
    public FuncionarioApura buscarPorCpf(String cpf) {
        return funcionarioApuraRepository.buscarPorCpf(cpf);
    }

    // Deletar um funcionário
    public void deletar(String cpf) {
        funcionarioApuraRepository.deletar(cpf);
    }
}
