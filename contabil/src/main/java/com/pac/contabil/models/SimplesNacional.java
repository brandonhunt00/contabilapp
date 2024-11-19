package com.pac.contabil.models;

public class SimplesNacional {

    private String tipoDeCalculo;
    private String fkEmpresaClienteCnpj;

    // Getters e Setters
    public String getTipoDeCalculo() {
        return tipoDeCalculo;
    }

    public void setTipoDeCalculo(String tipoDeCalculo) {
        this.tipoDeCalculo = tipoDeCalculo;
    }

    public String getFkEmpresaClienteCnpj() {
        return fkEmpresaClienteCnpj;
    }

    public void setFkEmpresaClienteCnpj(String fkEmpresaClienteCnpj) {
        this.fkEmpresaClienteCnpj = fkEmpresaClienteCnpj;
    }
}
