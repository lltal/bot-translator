package com.github.lltal.botcopier.core.services.factories;

import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.botcopier.core.model.ConsumerEntity;
import com.github.lltal.botcopier.core.model.TranslationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerEntityFactory {

    public ConsumerEntity createFromTranslation(
            TranslationDTO translationDTO,
            TranslationEntity translationEntity
    ) {
        return new ConsumerEntity(
                translationDTO.getConsumerId(),
                translationDTO.getConsumer(),
                translationDTO.getConsumerName(),
                List.of(translationEntity));
    }
}
