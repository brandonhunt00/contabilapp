package com.pac.contabil.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmpresaCliente {
    private String cnpj;
    private String razaoSocial;
    private String telefone;
    private String telefone2;
    private String email;
    private String rua;
    private String numero;
    private String municipio;

    // Getters e Setters
}

