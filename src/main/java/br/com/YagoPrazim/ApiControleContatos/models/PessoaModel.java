package br.com.YagoPrazim.ApiControleContatos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "tb_pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String endereco;

    private String cep;

    private String cidade;

    private String uf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<ContatoModel> contatos;

}
