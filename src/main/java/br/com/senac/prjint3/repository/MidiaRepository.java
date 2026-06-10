package br.com.senac.prjint3.repository;

import br.com.senac.prjint3.model.Midia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MidiaRepository extends JpaRepository<Midia, Integer> {
    List<Midia> findByStatusNot(Integer status);
    List<Midia> findByStatus(Integer status);
    Optional<Midia> findByIdAndStatusNot(Integer id, Integer status);
}
