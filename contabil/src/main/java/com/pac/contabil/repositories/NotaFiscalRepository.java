package com.pac.contabil.repositories;

import com.pac.contabil.models.NotaFiscal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotaFiscalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inserir(NotaFiscal notaFiscal) {
        String sql = "INSERT INTO Nota_Fiscal (cod_Nota, data_de_emissao, valor_total, descricao, fk_Empresa_Cliente_cnpj) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, notaFiscal.getCodNota(), notaFiscal.getDataDeEmissao(),
                notaFiscal.getValorTotal(), notaFiscal.getDescricao(), notaFiscal.getFkEmpresaClienteCnpj());
    }

    public List<NotaFiscal> listar() {
        String sql = "SELECT * FROM Nota_Fiscal";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NotaFiscal.class));
    }

    public NotaFiscal buscarPorCodigo(String codNota) {
        String sql = "SELECT * FROM Nota_Fiscal WHERE cod_Nota = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(NotaFiscal.class), codNota);
        } catch (EmptyResultDataAccessException e) {
            return null; // Nota não encontrada
        }
    }

    public void atualizar(NotaFiscal notaFiscal) {
        String sql = "UPDATE Nota_Fiscal SET data_de_emissao = ?, valor_total = ?, descricao = ?, fk_Empresa_Cliente_cnpj = ? WHERE cod_Nota = ?";
        jdbcTemplate.update(sql, notaFiscal.getDataDeEmissao(), notaFiscal.getValorTotal(),
                notaFiscal.getDescricao(), notaFiscal.getFkEmpresaClienteCnpj(), notaFiscal.getCodNota());
    }

    public void deletar(String codNota) {
        String sql = "DELETE FROM Nota_Fiscal WHERE cod_Nota = ?";
        jdbcTemplate.update(sql, codNota);
    }
}

