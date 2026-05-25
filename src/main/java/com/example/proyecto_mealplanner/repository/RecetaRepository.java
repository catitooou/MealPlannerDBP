package com.example.proyecto_mealplanner.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecetaRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void shouldSaveAndFindReceta() {
        com.example.proyecto_mealplanner.entity.UsuarioMapper user = usuarioRepository.save(com.example.proyecto_mealplanner.entity.UsuarioMapper.builder().nombre("Test").email("test@test.com").password("pass").build());
        com.example.proyecto_mealplanner.entity.RecetaMapper recetaMapper = com.example.proyecto_mealplanner.entity.RecetaMapper.builder().titulo("Pasta").usuario(user).build();
        com.example.proyecto_mealplanner.entity.RecetaMapper saved = recetaRepository.save(recetaMapper);
        assertThat(recetaRepository.findById(saved.getIdReceta())).isPresent();
    }
}
