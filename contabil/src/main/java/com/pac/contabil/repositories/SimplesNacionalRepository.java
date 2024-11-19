package com.pac.contabil.repositories;

import com.pac.contabil.models.SimplesNacional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimplesNacionalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para inserir um registro de Simples Nacional
    public void inserir(SimplesNacional simplesNacional) {
        String sql = "INSERT INTO Simples_Nacional (tipo_de_calculo, fk_Empresa_Cliente_cnpj) VALUES (?, ?)";
        jdbcTemplate.update(sql, simplesNacional.getTipoDeCalculo(), simplesNacional.getFkEmpresaClienteCnpj());
    }

    // Método para listar todos os registros de Simples Nacional
    public List<SimplesNacional> listar() {
        String sql = "SELECT * FROM Simples_Nacional";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SimplesNacional.class));
    }

    // Método para buscar um registro de Simples Nacional pelo CNPJ
    public SimplesNacional buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM Simples_Nacional WHERE fk_Empresa_Cliente_cnpj = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(SimplesNacional.class), cnpj);
        } catch (EmptyResultDataAccessException e) {
            return null; // Registro não encontrado
        }
    }

    // Método para deletar um registro de Simples Nacional pelo CNPJ
    public void deletar(String cnpj) {
        String sql = "DELETE FROM Simples_Nacional WHERE fk_Empresa_Cliente_cnpj = ?";
        jdbcTemplate.update(sql, cnpj);
    }
}
