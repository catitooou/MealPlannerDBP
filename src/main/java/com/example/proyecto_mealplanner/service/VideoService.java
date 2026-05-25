package com.example.proyecto_mealplanner.service;

import com.example.proyecto_mealplanner.dto.request.VideoRequestDTO;
import com.example.proyecto_mealplanner.dto.response.VideoResponseDTO;
import com.example.proyecto_mealplanner.enums.EstadoVideo;
import com.example.proyecto_mealplanner.mapper.Video;
import com.example.proyecto_mealplanner.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class VideoService {

    private final VideoRepository videoRepository;

    public VideoResponseDTO subirVideo(VideoRequestDTO dto) {

        Video video = Video.builder()
                .nombreArchivo(dto.getNombreArchivo())
                .urlVideo(dto.getUrlVideo())
                .estado(EstadoVideo.PENDIENTE)
                .build();

        videoRepository.save(video);

        return VideoResponseDTO.builder()
                .idVideo(video.getIdVideo())
                .nombreArchivo(video.getNombreArchivo())
                .estado(video.getEstado())
                .build();
    }
}