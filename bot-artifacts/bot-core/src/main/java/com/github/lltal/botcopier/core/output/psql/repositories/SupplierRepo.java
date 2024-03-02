package com.github.lltal.botcopier.core.output.psql.repositories;

import com.github.lltal.botcopier.core.model.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<SupplierEntity, Long> {
}
