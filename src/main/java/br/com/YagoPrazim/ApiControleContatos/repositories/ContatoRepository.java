package br.com.YagoPrazim.ApiControleContatos.repositories;

import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {
}
