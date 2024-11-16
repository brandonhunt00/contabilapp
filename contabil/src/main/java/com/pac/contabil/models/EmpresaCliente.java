package com.pac.contabil.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EmpresaCliente {

    @Id
    private String cnpj;
    private String razaoSocial;
    private String endereco;
    private String telefone;
    private String email;

}
