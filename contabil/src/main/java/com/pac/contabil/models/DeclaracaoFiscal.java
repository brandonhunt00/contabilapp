package com.pac.contabil.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DeclaracaoFiscal {

    private String codDeclaracaoFiscal;
    private String tipo;
    private String periodoDeReferencia;
    private Date dataDeEnvio;
    private String situacao;
    private String fkEmpresaClienteCnpj;

    // Getters e Setters


}

