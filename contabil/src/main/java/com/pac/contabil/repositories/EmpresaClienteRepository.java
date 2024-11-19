package com.pac.contabil.repositories;

import com.pac.contabil.models.EmpresaCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpresaClienteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void inserir(EmpresaCliente empresaCliente) {
        String sql = "INSERT INTO Empresa_Cliente (cnpj, razao_social, telefone, telefone2, email, rua, numero, municipio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, empresaCliente.getCnpj(), empresaCliente.getRazaoSocial(),
                empresaCliente.getTelefone(), empresaCliente.getTelefone2(),
                empresaCliente.getEmail(), empresaCliente.getRua(),
                empresaCliente.getNumero(), empresaCliente.getMunicipio());
    }

    public List<EmpresaCliente> listar() {
        String sql = "SELECT * FROM Empresa_Cliente";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmpresaCliente.class));
    }

    public EmpresaCliente buscarPorCnpj(String cnpj) {
        String sql = "SELECT * FROM Empresa_Cliente WHERE cnpj = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EmpresaCliente.class), cnpj);
        } catch (EmptyResultDataAccessException e) {
            return null; // Empresa não encontrada
        }
    }

    public void atualizar(EmpresaCliente empresaCliente) {
        String sql = "UPDATE Empresa_Cliente SET razao_social = ?, telefone = ?, telefone2 = ?, email = ?, rua = ?, numero = ?, municipio = ? WHERE cnpj = ?";
        jdbcTemplate.update(sql, empresaCliente.getRazaoSocial(), empresaCliente.getTelefone(),
                empresaCliente.getTelefone2(), empresaCliente.getEmail(), empresaCliente.getRua(),
                empresaCliente.getNumero(), empresaCliente.getMunicipio(), empresaCliente.getCnpj());
    }

    public void deletar(String cnpj) {
        String sql = "DELETE FROM Empresa_Cliente WHERE cnpj = ?";
        jdbcTemplate.update(sql, cnpj);
    }

}
