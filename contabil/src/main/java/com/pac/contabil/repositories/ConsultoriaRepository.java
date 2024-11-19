package com.pac.contabil.repositories;

import com.pac.contabil.models.Consultoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultoriaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inserir(Consultoria consultoria) {
        String sql = "INSERT INTO Consultoria (cod_Consultoria, tipo, data, descricao, fk_Empresa_Cliente_cnpj) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, consultoria.getCodConsultoria(), consultoria.getTipo(),
                consultoria.getData(), consultoria.getDescricao(), consultoria.getFkEmpresaClienteCnpj());
    }

    public List<Consultoria> listar() {
        String sql = "SELECT * FROM Consultoria";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Consultoria.class));
    }

    public Consultoria buscarPorCodConsultoria(String codConsultoria) {
        String sql = "SELECT * FROM Consultoria WHERE cod_Consultoria = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Consultoria.class), codConsultoria);
        } catch (EmptyResultDataAccessException e) {
            return null; // Consultoria não encontrada
        }
    }

    public void atualizar(Consultoria consultoria) {
        String sql = "UPDATE Consultoria SET tipo = ?, data = ?, descricao = ?, fk_Empresa_Cliente_cnpj = ? " +
                "WHERE cod_Consultoria = ?";
        jdbcTemplate.update(sql, consultoria.getTipo(), consultoria.getData(), consultoria.getDescricao(),
                consultoria.getFkEmpresaClienteCnpj(), consultoria.getCodConsultoria());
    }

    public void deletar(String codConsultoria) {
        String sql = "DELETE FROM Consultoria WHERE cod_Consultoria = ?";
        jdbcTemplate.update(sql, codConsultoria);
    }
}

