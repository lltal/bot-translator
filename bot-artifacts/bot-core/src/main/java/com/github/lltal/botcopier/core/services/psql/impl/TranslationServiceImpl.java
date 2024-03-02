package com.github.lltal.botcopier.core.services.psql.impl;

import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.botcopier.core.model.TranslationEntity;
import com.github.lltal.botcopier.core.output.psql.repositories.TranslationRepo;
import com.github.lltal.botcopier.core.services.factories.TranslationFactory;
import com.github.lltal.botcopier.core.services.psql.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {
    private final TranslationRepo translationRepo;
    private final TranslationFactory translationFactory;

    @Override
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void save(TranslationDTO dto) {
        TranslationEntity entity = translationFactory.createDefaultEntity(dto);
        translationRepo.save(entity);
    }

    @Override
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void delete(Long userId) {
        translationRepo.deleteById(userId);
    }
}
