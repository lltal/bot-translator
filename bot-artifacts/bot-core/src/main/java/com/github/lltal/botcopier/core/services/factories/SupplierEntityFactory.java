package com.github.lltal.botcopier.core.services.factories;

import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.botcopier.core.model.SupplierEntity;
import com.github.lltal.botcopier.core.model.TranslationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierEntityFactory {

    public SupplierEntity createFromTranslation(
            TranslationDTO translationDTO,
            TranslationEntity translationEntity
    ) {
        return new SupplierEntity(
                translationDTO.getSupplierId(),
                translationDTO.getSupplier(),
                translationDTO.getSupplierName(),
                List.of(translationEntity));
    }
}
