package com.pac.contabil.repositories;

import com.pac.contabil.models.EmpresaCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaClienteRepository extends JpaRepository<EmpresaCliente, String> {
}
