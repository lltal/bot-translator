package com.github.lltal.botcopier.core.input.telegram.dto.consumer;

import com.github.lltal.botcopier.shared.constants.consumer.ConsumerType;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierType;
import org.springframework.beans.factory.annotation.Value;

public interface ConsumerWithSupplierName {

    @Value("#{target.consumer_id}")
    Long getConsumerId();
    @Value("#{target.type}")
    SupplierType getSupplierType();
    @Value("#{target.supplier_name}")
    String getSupplierName();
}
