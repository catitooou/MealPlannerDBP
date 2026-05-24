package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.response.FavoritoResponseDTO;
import com.example.proyecto_mealplanner.model.Favorito;
import com.example.proyecto_mealplanner.repository.FavoritoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoResponseDTO guardarFavorito(Favorito favorito) {

        favoritoRepository.save(favorito);

        return FavoritoResponseDTO.builder()
                .idFavorito(favorito.getIdFavorito())
                .build();
    }
}