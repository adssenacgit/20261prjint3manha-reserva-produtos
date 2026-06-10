package br.com.senac.prjint3.repository;

import br.com.senac.prjint3.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByStatusNot(Integer status);
    List<Reserva> findByStatus(Integer status);
    Optional<Reserva> findByIdAndStatusNot(Integer id, Integer status);
}
