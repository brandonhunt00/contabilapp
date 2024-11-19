package com.pac.contabil.repositories;

import com.pac.contabil.models.Imposto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ImpostoRepository {

    // Exemplo de banco de dados em memória
    private List<Imposto> impostos = new ArrayList<>();

    public void inserir(Imposto imposto) {
        impostos.add(imposto);  // Inserir imposto no banco de dados (ou lista)
    }

    public List<Imposto> listar() {
        return impostos;  // Retorna todos os impostos
    }

    public Imposto buscarPorCodigo(String codImposto) {
        return impostos.stream()
                .filter(imposto -> imposto.getCodImposto().equals(codImposto))
                .findFirst()
                .orElse(null);
    }

    public void atualizar(Imposto imposto) {
        for (int i = 0; i < impostos.size(); i++) {
            if (impostos.get(i).getCodImposto().equals(imposto.getCodImposto())) {
                impostos.set(i, imposto);
                return;
            }
        }
    }

    public boolean deletar(String codImposto) {
        return impostos.removeIf(imposto -> imposto.getCodImposto().equals(codImposto));
    }
}
