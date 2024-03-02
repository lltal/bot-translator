package com.github.lltal.botcopier.core.services.psql;

import com.github.lltal.botcopier.core.input.telegram.dto.consumer.ConsumerWithSupplierName;

import java.util.List;
import java.util.Set;

public interface ConsumerService {

    List<ConsumerWithSupplierName> findAllConsumersByUpdateParam(
            Long supplierId,
            Set<String> updateWords
    );
}
