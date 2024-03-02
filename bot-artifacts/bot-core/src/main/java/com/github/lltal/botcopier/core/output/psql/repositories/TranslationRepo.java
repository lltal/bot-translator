package com.github.lltal.botcopier.core.output.psql.repositories;

import com.github.lltal.botcopier.core.model.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepo extends JpaRepository<TranslationEntity, Long> {
}
