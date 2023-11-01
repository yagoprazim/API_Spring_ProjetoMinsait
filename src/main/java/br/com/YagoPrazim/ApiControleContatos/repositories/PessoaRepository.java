package br.com.YagoPrazim.ApiControleContatos.repositories;

import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository  extends JpaRepository<PessoaModel, Long> {
}
