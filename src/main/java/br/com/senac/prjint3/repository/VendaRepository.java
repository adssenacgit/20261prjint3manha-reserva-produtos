package br.com.senac.prjint3.repository;

import br.com.senac.prjint3.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findByStatusNot(Integer status);
    List<Venda> findByStatus(Integer status);
    Optional<Venda> findByIdAndStatusNot(Integer id, Integer status);
}
