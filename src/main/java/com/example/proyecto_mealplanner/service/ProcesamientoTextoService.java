package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.entity.Ingrediente;
import com.example.proyecto_mealplanner.entity.Paso;
import com.example.proyecto_mealplanner.entity.Receta;
import com.example.proyecto_mealplanner.entity.Usuario;
import com.example.proyecto_mealplanner.enums.UnidadMedida;
import com.example.proyecto_mealplanner.repository.IngredienteRepository;
import com.example.proyecto_mealplanner.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ProcesamientoTextoService {
    private final RecetaRepository recetaRepository;
    private final IngredienteRepository ingredienteRepository;

    public Receta generarRecetaDesdeTexto(String texto, Usuario usuario) {
        // Parseo muy básico: busca líneas que contengan "ingredientes" y "preparación"
        String[] lineas = texto.split("\\n");
        List<String> listaIngredientes = new ArrayList<>();
        List<String> listaPasos = new ArrayList<>();
        boolean modoIngredientes = false, modoPasos = false;

        for (String linea : lineas) {
            String lower = linea.toLowerCase();
            if (lower.contains("ingrediente")) {
                modoIngredientes = true;
                modoPasos = false;
                continue;
            } else if (lower.contains("preparación") || lower.contains("pasos")) {
                modoIngredientes = false;
                modoPasos = true;
                continue;
            }
            if (modoIngredientes && !linea.trim().isEmpty()) {
                listaIngredientes.add(linea.trim());
            } else if (modoPasos && !linea.trim().isEmpty()) {
                listaPasos.add(linea.trim());
            }
        }

        // Crear receta
        Receta receta = Receta.builder()
                .titulo("Receta generada automáticamente")
                .usuario(usuario)
                .publica(false)
                .build();
        receta = recetaRepository.save(receta);

        // Crear pasos
        List<Paso> pasos = new ArrayList<>();
        for (int i = 0; i < listaPasos.size(); i++) {
            pasos.add(Paso.builder()
                    .orden(i + 1)
                    .descripcion(listaPasos.get(i))
                    .receta(receta)
                    .build());
        }
        // (Falta guardar pasos, pero puedes inyectar PasoRepository)

        // Procesar ingredientes (simplificado)
        // Se recomienda implementar una lógica más robusta o usar NLP.

        return receta;
    }
}