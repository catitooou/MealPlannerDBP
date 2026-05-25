package com.example.proyecto_mealplanner.mapper;

import com.example.proyecto_mealplanner.dto.response.VideoResponseDTO;
import com.example.proyecto_mealplanner.entity.Video;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);

    @Mapping(source = "transcripcion.idTranscripcion", target = "transcripcionId")
    @Mapping(source = "recetaGenerada.idReceta", target = "recetaGeneradaId")
    VideoResponseDTO toDto(Video video);
}