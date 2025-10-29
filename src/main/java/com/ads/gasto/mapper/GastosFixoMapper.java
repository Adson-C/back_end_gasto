package com.ads.gasto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ads.gasto.dto.GastosFixoResponseDto;
import com.ads.gasto.model.GastosFixoModel;

@Component
public class GastosFixoMapper {

    public GastosFixoResponseDto toDto(GastosFixoModel model) {
        if (model == null) {
            return null;
        }

        GastosFixoResponseDto dto = new GastosFixoResponseDto();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setQuantia(model.getQuantia());
        dto.setFecha(model.getFecha());

        // Handle estado relationship
        if (model.getEstadoId() != null) {
            dto.setEstadoId(model.getEstadoId().getId());
            dto.setEstadoNome(model.getEstadoId().getNome());
        }

        // Handle proveedore relationship
        if (model.getProveedoreId() != null) {
            dto.setProveedoreId(model.getProveedoreId().getId());
            dto.setProveedoreNome(model.getProveedoreId().getNome());
        }

        return dto;
    }

    public List<GastosFixoResponseDto> toDtoList(List<GastosFixoModel> models) {
        if (models == null) {
            return null;
        }

        return models.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
