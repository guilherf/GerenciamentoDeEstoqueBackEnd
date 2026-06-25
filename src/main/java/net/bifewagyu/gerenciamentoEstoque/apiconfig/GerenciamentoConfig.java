package net.bifewagyu.gerenciamentoEstoque.apiconfig;


import jakarta.servlet.annotation.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class GerenciamentoConfig {


    @Bean
    public DateTimeFormatter formatoDataEntrada(){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }




}
