package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(MainSpringBoot.class, args);
    }

//    // Beans para Unidade
//    @Bean
//    public UnidadeRepository unidadeRepository() {
//        return new UnidadeRepositoryImpl();
//    }
//
//    @Bean
//    public UnidadeApplication unidadeApplication(UnidadeRepository repository) {
//        return new UnidadeApplication(repository);
//    }
//
//    @Bean
//    public UnidadeFacade unidadeFacade(UnidadeApplication application) {
//        return new UnidadeFacade(application);
//    }
//
//    // Beans para Usuario
//    @Bean
//    public UsuarioRepository usuarioRepository() {
//        return new UsuarioRepositoryImpl();
//    }
//
//    @Bean
//    public UsuarioApplication usuarioApplication(UsuarioRepository repository) {
//        return new UsuarioApplication(repository);
//    }
//
//    @Bean
//    public UsuarioFacade usuarioFacade(UsuarioApplication application) {
//        return new UsuarioFacade(application);
//    }
}