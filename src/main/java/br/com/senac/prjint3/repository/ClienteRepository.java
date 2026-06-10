package br.com.senac.prjint3.repository;

import br.com.senac.prjint3.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByStatusNot(Integer status);
    List<Cliente> findByStatus(Integer status);
    Optional<Cliente> findByIdAndStatusNot(Integer id, Integer status);
}
