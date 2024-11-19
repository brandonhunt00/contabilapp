package com.pac.contabil.repositories;

import com.pac.contabil.models.LucroReal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LucroRealRepository {

    private List<LucroReal> lucrosReais = new ArrayList<>();

    public void inserir(LucroReal lucroReal) {
        lucrosReais.add(lucroReal);
    }

    public List<LucroReal> listar() {
        return lucrosReais;
    }

    public LucroReal buscarPorCnpj(String cnpj) {
        return lucrosReais.stream()
                .filter(lucro -> lucro.getFkEmpresaClienteCnpj().equals(cnpj))
                .findFirst()
                .orElse(null);
    }

    public void atualizar(LucroReal lucroReal) {
        for (int i = 0; i < lucrosReais.size(); i++) {
            if (lucrosReais.get(i).getFkEmpresaClienteCnpj().equals(lucroReal.getFkEmpresaClienteCnpj())) {
                lucrosReais.set(i, lucroReal);
                return;
            }
        }
    }

    public boolean deletar(String cnpj) {
        return lucrosReais.removeIf(lucro -> lucro.getFkEmpresaClienteCnpj().equals(cnpj));
    }
}
