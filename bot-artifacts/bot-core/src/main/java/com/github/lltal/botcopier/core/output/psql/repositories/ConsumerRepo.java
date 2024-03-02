package com.github.lltal.botcopier.core.output.psql.repositories;

import com.github.lltal.botcopier.core.input.telegram.dto.consumer.ConsumerWithSupplierName;
import com.github.lltal.botcopier.core.model.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ConsumerRepo extends JpaRepository<ConsumerEntity, Long> {

    @Query(value = "select mc.consumer_id, ms.type, ms.supplier_name from message_consumer mc" +
            " join consumers_translations ct on mc.consumer_id = ct.consumer_id" +
            " join translation t on ct.user_id = t.user_id" +
            " join key_words kw on kw.words_id = t.user_id" +
            " join suppliers_translations st on st.user_id = t.user_id" +
            " join message_supplier ms on ms.supplier_id = st.supplier_id" +
            " and ms.supplier_id = :supplier_id" +
            " and kw.word in :update_words", nativeQuery = true)
    List<ConsumerWithSupplierName> findAllSendersByUpdateParam(
            @Param("supplier_id") Long supplier_id,
            @Param("update_words") Set<String> updateWords);
}
