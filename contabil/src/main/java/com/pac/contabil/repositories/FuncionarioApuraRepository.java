package com.pac.contabil.repositories;

import com.pac.contabil.models.FuncionarioApura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FuncionarioApuraRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para inserir um novo funcionário na apuração
    public void inserir(FuncionarioApura funcionarioApura) {
        String sql = "INSERT INTO Funcionario_Apura (cpf, nome, departamento, telefone, email, cpf_gerente, fk_Nota_Fiscal_cod, fk_Nota_Fiscal_cnpj, fk_Imposto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, funcionarioApura.getCpf(), funcionarioApura.getNome(),
                funcionarioApura.getDepartamento(), funcionarioApura.getTelefone(), funcionarioApura.getEmail(),
                funcionarioApura.getCpfGerente(), funcionarioApura.getFkNotaFiscalCod(),
                funcionarioApura.getFkNotaFiscalCnpj(), funcionarioApura.getFkImposto());
    }

    // Método para listar todos os funcionários
    public List<FuncionarioApura> listar() {
        String sql = "SELECT * FROM Funcionario_Apura";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FuncionarioApura.class));
    }

    // Método para buscar um funcionário pelo CPF
    public FuncionarioApura buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM Funcionario_Apura WHERE cpf = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(FuncionarioApura.class), cpf);
        } catch (EmptyResultDataAccessException e) {
            return null; // Funcionário não encontrado
        }
    }

    // Método para deletar um funcionário
    public void deletar(String cpf) {
        String sql = "DELETE FROM Funcionario_Apura WHERE cpf = ?";
        jdbcTemplate.update(sql, cpf);
    }
}
