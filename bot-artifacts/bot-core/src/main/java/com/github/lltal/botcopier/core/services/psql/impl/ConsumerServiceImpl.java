package com.github.lltal.botcopier.core.services.psql.impl;

import com.github.lltal.botcopier.core.input.telegram.dto.consumer.ConsumerWithSupplierName;
import com.github.lltal.botcopier.core.output.psql.repositories.ConsumerRepo;
import com.github.lltal.botcopier.core.services.psql.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final ConsumerRepo consumerRepo;

    @Override
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    public List<ConsumerWithSupplierName> findAllConsumersByUpdateParam(
            Long supplierId,
            Set<String> updateWords
    ) {
       return consumerRepo
                .findAllSendersByUpdateParam(
                        supplierId,
                        updateWords);
    }
}
