package br.com.YagoPrazim.ApiControleContatos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo 'nome' é obrigatório!")
    @Column(nullable = false)
    private String nome;

    private String endereco;

    private String cep;

    private String cidade;

    private String uf;

}
