package br.com.YagoPrazim.ApiControleContatos.repositories;

import br.com.YagoPrazim.ApiControleContatos.models.ContatoModel;
import br.com.YagoPrazim.ApiControleContatos.models.PessoaModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, Long> {

    Page<ContatoModel> findByPessoa(PessoaModel pessoa, Pageable pageable);
}
