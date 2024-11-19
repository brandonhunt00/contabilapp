package com.pac.contabil.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioApura {

    private String cpf;
    private String nome;
    private String departamento;
    private String telefone;
    private String email;
    private String cpfGerente;
    private String fkNotaFiscalCod;
    private String fkNotaFiscalCnpj;
    private String fkImposto;

    // Getters e Setters

}
