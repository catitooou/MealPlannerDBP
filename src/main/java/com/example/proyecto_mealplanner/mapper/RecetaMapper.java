package com.example.proyecto_mealplanner.mapper;

import com.example.proyecto_mealplanner.dto.request.RecetaRequestDTO;
import com.example.proyecto_mealplanner.dto.response.RecetaResponseDTO;
import com.example.proyecto_mealplanner.entity.Paso;
import com.example.proyecto_mealplanner.entity.Receta;
import com.example.proyecto_mealplanner.entity.RecetaIngrediente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecetaMapper {

    RecetaMapper INSTANCE = Mappers.getMapper(RecetaMapper.class);

    @Mapping(target = "idReceta", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "pasos", ignore = true)
    @Mapping(target = "recetaIngredientes", ignore = true)
    @Mapping(target = "favoritos", ignore = true)
    Receta toEntity(RecetaRequestDTO dto);

    @Mapping(source = "usuario.idUsuario", target = "usuarioId")
    @Mapping(source = "usuario.nombre", target = "nombreUsuario")
    @Mapping(target = "pasos", source = "pasos")
    @Mapping(target = "ingredientes", source = "recetaIngredientes")
    RecetaResponseDTO toDto(Receta receta);

    RecetaResponseDTO.PasoDTO pasoToPasoDto(Paso paso);

    RecetaResponseDTO.IngredienteCantidadDTO ingredienteToDto(RecetaIngrediente ri);
}