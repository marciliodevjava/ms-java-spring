package br.com.alura.food.pagamentos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagamentos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pagamento {

}