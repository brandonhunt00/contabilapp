package com.pac.contabil.models;

public class Imposto {
    private String codImposto;
    private String tipo;
    private float aliquota;
    private double baseDeCalculo;

    // Getters and setters
    public String getCodImposto() {
        return codImposto;
    }

    public void setCodImposto(String codImposto) {
        this.codImposto = codImposto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getAliquota() {
        return aliquota;
    }

    public void setAliquota(float aliquota) {
        this.aliquota = aliquota;
    }

    public double getBaseDeCalculo() {
        return baseDeCalculo;
    }

    public void setBaseDeCalculo(double baseDeCalculo) {
        this.baseDeCalculo = baseDeCalculo;
    }
}
