package com.github.lltal.botcopier.core.services.factories;

import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.botcopier.core.model.TranslationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslationFactory {
    private final SupplierEntityFactory supplierFactory;
    private final ConsumerEntityFactory consumerEntityFactory;

    public TranslationEntity createDefaultEntity(TranslationDTO dto) {
        TranslationEntity translation = new TranslationEntity(dto.getUserId(), dto.getKeyWords());

        translation.setConsumers(
                List.of(
                        consumerEntityFactory.createFromTranslation(dto, translation)));

        translation.setSuppliers(
                List.of(
                        supplierFactory.createFromTranslation(dto, translation)));

        return translation;
    }
}
