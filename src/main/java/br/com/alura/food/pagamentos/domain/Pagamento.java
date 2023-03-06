package br.com.alura.food.pagamentos.domain;

import br.com.alura.food.pagamentos.domain.enuns.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotBlank
    @Size(min = 10, max = 150)
    private String nome;
    @NotBlank
    @Size(max = 19)
    private String numero;
    @NotBlank
    @Size(max = 7)
    private String expiracao;
    @NotBlank
    @Size(min = 3, max = 3)
    private String codigo;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    private Long pedidoId;
    @NotNull
    private Long formaDePagamentoId;


}