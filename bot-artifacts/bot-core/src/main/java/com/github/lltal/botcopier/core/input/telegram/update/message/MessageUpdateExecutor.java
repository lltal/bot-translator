package com.github.lltal.botcopier.core.input.telegram.update.message;

import com.github.lltal.botcopier.core.input.telegram.dto.consumer.ConsumerWithSupplierName;
import com.github.lltal.botcopier.core.services.psql.ConsumerService;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierBeanNames;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierType;
import com.github.lltal.converter.shared.ifc.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MessageUpdateExecutor {
    @Autowired
    private ConsumerService senderService;
    @Autowired
    @Qualifier(SupplierBeanNames.CONVERTER)
    private AbstractConverter<SupplierType> converter;

    public void executeMessageUpdate(TelegramLongPollingEngine engine, Message message) {
        Set<String> updateWords =
                Arrays
                        .stream(
                                message.getText().split(" "))
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());

        List<ConsumerWithSupplierName> consumers = senderService.findAllConsumersByUpdateParam(
                message.getChatId(),
                updateWords);

        consumers.forEach(consumer -> engine
                .executeNotException(
                        createMessage(
                                text(
                                        converter
                                                .convertToStringView(consumer.getSupplierType())
                                                .toLowerCase(),
                                        consumer.getSupplierName(),
                                        message.getText()),
                                consumer.getConsumerId())));
    }

    private SendMessage createMessage(String text, Long chatId) {
        return SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
    }

    private String text(
            String supplierType,
            String title,
            String messageText
    ) {
        return String.format(
                "Сообщение из %sа @%s:\n%s",
                supplierType,
                title,
                messageText);
    }
}
