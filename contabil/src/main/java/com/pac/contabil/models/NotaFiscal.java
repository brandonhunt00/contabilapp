package com.pac.contabil.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class NotaFiscal {
    private String codNota;
    private Date dataDeEmissao;
    private double valorTotal;
    private String descricao;
    private String fkEmpresaClienteCnpj; // Chave estrangeira para EmpresaCliente

    // Getters e Setters
}
