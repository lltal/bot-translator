package com.github.lltal.botcopier.core.services.psql;

import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;

public interface TranslationService {
    void save(TranslationDTO dto);

    void delete(Long userId);
}
