package com.example.proyecto_mealplanner.mapper;

import com.example.proyecto_mealplanner.dto.request.IngredienteRequestDTO;
import com.example.proyecto_mealplanner.dto.response.IngredienteResponseDTO;
import com.example.proyecto_mealplanner.entity.Ingrediente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {
    IngredienteMapper INSTANCE = Mappers.getMapper(IngredienteMapper.class);

    @Mapping(target = "idIngrediente", ignore = true)
    @Mapping(target = "recetaIngredientes", ignore = true)
    Ingrediente toEntity(IngredienteRequestDTO dto);

    IngredienteResponseDTO toDto(Ingrediente ingrediente);
}
