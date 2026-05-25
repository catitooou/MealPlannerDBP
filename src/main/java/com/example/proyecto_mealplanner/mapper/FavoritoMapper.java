package com.example.proyecto_mealplanner.mapper;

import com.example.proyecto_mealplanner.dto.response.FavoritoResponseDTO;
import com.example.proyecto_mealplanner.entity.Favorito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FavoritoMapper {
    FavoritoMapper INSTANCE = Mappers.getMapper(FavoritoMapper.class);

    @Mapping(source = "receta.idReceta", target = "recetaId")
    @Mapping(source = "receta.titulo", target = "tituloReceta")
    @Mapping(source = "usuario.idUsuario", target = "usuarioId")
    FavoritoResponseDTO toDto(Favorito favorito);
}
