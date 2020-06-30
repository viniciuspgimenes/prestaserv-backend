package br.com.prestaserv.backend.repository;

import br.com.prestaserv.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Procedure("obter_total_de_clientes")
    int obterTotalDeClientes();

    @Query(nativeQuery = true,value = "call obter_cliente_por_cidade(:in_cidade)")
    List<Cliente> obterClientesPorCidade(@Param("in_cidade") String cidade);
}
