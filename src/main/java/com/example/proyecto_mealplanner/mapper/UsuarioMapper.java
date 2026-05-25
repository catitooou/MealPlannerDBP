package com.example.proyecto_mealplanner.mapper;

import com.example.proyecto_mealplanner.dto.response.UsuarioResponseDTO;
import com.example.proyecto_mealplanner.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    UsuarioResponseDTO toDto(Usuario usuario);
}
