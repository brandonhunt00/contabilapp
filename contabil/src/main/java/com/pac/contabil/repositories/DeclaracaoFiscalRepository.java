package com.pac.contabil.repositories;

import com.pac.contabil.models.DeclaracaoFiscal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeclaracaoFiscalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inserir(DeclaracaoFiscal declaracaoFiscal) {
        String sql = "INSERT INTO Declaracao_Fiscal (cod_Declaracao_Fiscal, tipo, periodo_de_referencia, data_de_envio, situacao, fk_Empresa_Cliente_cnpj) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, declaracaoFiscal.getCodDeclaracaoFiscal(), declaracaoFiscal.getTipo(),
                declaracaoFiscal.getPeriodoDeReferencia(), declaracaoFiscal.getDataDeEnvio(),
                declaracaoFiscal.getSituacao(), declaracaoFiscal.getFkEmpresaClienteCnpj());
    }

    public List<DeclaracaoFiscal> listar() {
        String sql = "SELECT * FROM Declaracao_Fiscal";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DeclaracaoFiscal.class));
    }

    public DeclaracaoFiscal buscarPorCodDeclaracaoFiscal(String codDeclaracaoFiscal) {
        String sql = "SELECT * FROM Declaracao_Fiscal WHERE cod_Declaracao_Fiscal = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(DeclaracaoFiscal.class), codDeclaracaoFiscal);
        } catch (EmptyResultDataAccessException e) {
            return null; // Declaração Fiscal não encontrada
        }
    }

    public void atualizar(DeclaracaoFiscal declaracaoFiscal) {
        String sql = "UPDATE Declaracao_Fiscal SET tipo = ?, periodo_de_referencia = ?, data_de_envio = ?, situacao = ?, fk_Empresa_Cliente_cnpj = ? " +
                "WHERE cod_Declaracao_Fiscal = ?";
        jdbcTemplate.update(sql, declaracaoFiscal.getTipo(), declaracaoFiscal.getPeriodoDeReferencia(),
                declaracaoFiscal.getDataDeEnvio(), declaracaoFiscal.getSituacao(),
                declaracaoFiscal.getFkEmpresaClienteCnpj(), declaracaoFiscal.getCodDeclaracaoFiscal());
    }

    public void deletar(String codDeclaracaoFiscal) {
        String sql = "DELETE FROM Declaracao_Fiscal WHERE cod_Declaracao_Fiscal = ?";
        jdbcTemplate.update(sql, codDeclaracaoFiscal);
    }
}

