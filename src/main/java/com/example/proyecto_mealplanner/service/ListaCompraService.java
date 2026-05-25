package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.ListaCompraRequestDTO;
import com.example.proyecto_mealplanner.dto.response.ListaCompraResponseDTO;
import com.example.proyecto_mealplanner.mapper.ListaCompra;
import com.example.proyecto_mealplanner.repository.ListaCompraRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ListaCompraService {

    private final ListaCompraRepository listaCompraRepository;

    public ListaCompraResponseDTO crearItem(ListaCompraRequestDTO dto) {

        ListaCompra item = ListaCompra.builder()
                .nombreIngrediente(dto.getNombreIngrediente())
                .cantidad(dto.getCantidad())
                .build();

        listaCompraRepository.save(item);

        return ListaCompraResponseDTO.builder()
                .idLista(item.getIdLista())
                .nombreIngrediente(item.getNombreIngrediente())
                .cantidad(item.getCantidad())
                .build();
    }
}