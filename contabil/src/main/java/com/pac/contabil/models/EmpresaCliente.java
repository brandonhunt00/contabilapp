package com.pac.contabil.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class EmpresaCliente {

    @Id
    private String cnpj;
    private String razaoSocial;
    private String endereco;
    private String telefone;
    private String email;
}
