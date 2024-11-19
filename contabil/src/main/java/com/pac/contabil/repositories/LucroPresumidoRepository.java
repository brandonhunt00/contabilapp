package com.pac.contabil.repositories;

import com.pac.contabil.models.LucroPresumido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LucroPresumidoRepository {

    private List<LucroPresumido> lucrosPresumidos = new ArrayList<>();

    public void inserir(LucroPresumido lucroPresumido) {
        lucrosPresumidos.add(lucroPresumido);
    }

    public List<LucroPresumido> listar() {
        return lucrosPresumidos;
    }

    public LucroPresumido buscarPorCnpj(String cnpj) {
        return lucrosPresumidos.stream()
                .filter(lucro -> lucro.getFkEmpresaClienteCnpj().equals(cnpj))
                .findFirst()
                .orElse(null);
    }

    public void atualizar(LucroPresumido lucroPresumido) {
        for (int i = 0; i < lucrosPresumidos.size(); i++) {
            if (lucrosPresumidos.get(i).getFkEmpresaClienteCnpj().equals(lucroPresumido.getFkEmpresaClienteCnpj())) {
                lucrosPresumidos.set(i, lucroPresumido);
                return;
            }
        }
    }

    public boolean deletar(String cnpj) {
        return lucrosPresumidos.removeIf(lucro -> lucro.getFkEmpresaClienteCnpj().equals(cnpj));
    }
}
