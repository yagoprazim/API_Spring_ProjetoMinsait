package br.com.YagoPrazim.ApiControleContatos.models;

import br.com.YagoPrazim.ApiControleContatos.validations.constraints.TipoContato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contatos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Você deve escolher um tipo de contato.")
    @TipoContato
    private Integer tipoContato;

    @NotBlank(message = "Digite um número de telefone ou celular.")
    @Column(nullable = false)
    private String contato;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @NotNull(message = "O contato deve estar associado a uma pessoa.")
    private PessoaModel pessoaModel;








}
