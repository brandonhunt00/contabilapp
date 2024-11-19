package com.pac.contabil.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Consultoria {

    private String codConsultoria;
    private String tipo;
    private Date data;
    private String descricao;
    private String fkEmpresaClienteCnpj;

    // Getters e Setters


}
