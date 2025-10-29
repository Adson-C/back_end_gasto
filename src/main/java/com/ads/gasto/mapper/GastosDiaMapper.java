package com.ads.gasto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ads.gasto.dto.GastosDiaResponseDto;
import com.ads.gasto.model.GastosPorDiaModel;

@Component
public class GastosDiaMapper {

    public GastosDiaResponseDto toDto(GastosPorDiaModel model) {
        if (model == null) {
            return null;
        }

        GastosDiaResponseDto dto = new GastosDiaResponseDto();
        dto.setId(model.getId());
        dto.setLiquido(model.getLiquido());
        dto.setIva(model.getIva());
        dto.setTotal(model.getTotal());
        dto.setFecha(model.getFecha());
        dto.setDescripcion(model.getDescripcion());
        dto.setProveedoreId(model.getProveedoreId().getId());
        dto.setProveedoreNome(model.getProveedoreId().getNome());

        return dto;
    }

    public List<GastosDiaResponseDto> toDtoList(List<GastosPorDiaModel> models) {
        if (models == null) {
            return null;
        }

        return models.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
}
