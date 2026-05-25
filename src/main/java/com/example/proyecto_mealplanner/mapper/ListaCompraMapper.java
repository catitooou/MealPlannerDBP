package com.example.proyecto_mealplanner.mapper;

import com.example.proyecto_mealplanner.dto.response.ListaCompraResponseDTO;
import com.example.proyecto_mealplanner.entity.ListaCompra;
import com.example.proyecto_mealplanner.entity.ListaCompraItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ListaCompraMapper {
    ListaCompraMapper INSTANCE = Mappers.getMapper(ListaCompraMapper.class);

    @Mapping(source = "usuario.idUsuario", target = "usuarioId")
    ListaCompraResponseDTO toDto(ListaCompra lista);

    ListaCompraResponseDTO.ListaCompraItemDTO itemToDto(ListaCompraItem item);
}
