package br.com.senac.prjint3.repository;

import br.com.senac.prjint3.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
    List<Funcionario> findByStatusNot(Integer status);
    List<Funcionario> findByStatus(Integer status);
    Optional<Funcionario> findByLoginAndStatusNot(String login, Integer status);
}
