package br.com.YagoPrazim.ApiControleContatos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pessoas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String endereco;

    private String cep;

    private String cidade;

    private String uf;

    @JsonBackReference
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<ContatoModel> contatos;

}
