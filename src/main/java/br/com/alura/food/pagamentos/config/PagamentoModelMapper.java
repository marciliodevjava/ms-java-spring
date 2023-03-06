package br.com.alura.food.pagamentos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoModelMapper {

    @Bean
    public ModelMapper metodoQueCriaUmModelMapper(){
        return new ModelMapper();

    }
}
