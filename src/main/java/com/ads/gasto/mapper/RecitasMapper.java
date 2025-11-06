package com.ads.gasto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ads.gasto.dto.ReceitaResponseDto;
import com.ads.gasto.model.ReceitasModel;

@Component
public class RecitasMapper {



    public ReceitaResponseDto toDto(ReceitasModel model) {
        if (model == null) {
            return null;
        }

        ReceitaResponseDto dto = new ReceitaResponseDto(
                model.getId(),
                model.getTipoReceita(),
                model.getValor(),
                model.getDescricao(),
                model.getData()
        );

        return dto;
    }

    public List<ReceitaResponseDto> toDtoList(List<ReceitasModel> models) {
        if (models == null) {
            return null;
        }

        return models.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
