package com.github.lltal.botcopier.core.input.telegram.dto.translation;

import com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.fillees.ConsumerFilleeHandler;
import com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.buttons.MeButtonHandler;
import com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.fillees.KeyWordFilleeHandler;
import com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.fillees.PreparedFilleeHandler;
import com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.fillees.SupplierFilleeHandler;
import com.github.lltal.botcopier.shared.constants.consumer.ConsumerType;
import com.github.lltal.botcopier.shared.constants.consumer.ConsumerTypeStringView;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierType;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierTypeStringView;
import com.github.lltal.botcopier.shared.constants.translation.TranslationBeanNames;
import com.github.lltal.filler.shared.annotation.Button;
import com.github.lltal.filler.shared.annotation.Fillee;
import com.github.lltal.filler.shared.annotation.FilleeField;
import com.github.lltal.filler.shared.annotation.Keyboard;
import com.github.lltal.filler.shared.ifc.Countable;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
@Fillee(
        senderBeanName = TranslationBeanNames.SENDER_BEAN_NAME,
        fillerBeanName = TranslationBeanNames.FILLER_BEAN_NAME,
        resolverBeanName = TranslationBeanNames.RESOLVER_BEAN_NAME
)
public class TranslationDTO implements Countable {
    @NonNull
    private Long userId;

    @Keyboard(buttons = {
            @Button(userView = "Мне", cbValue = ConsumerTypeStringView.ME, customClickHandler = MeButtonHandler.HANDLER_BEAN_NAME),
            @Button(userView = "В канал", cbValue = ConsumerTypeStringView.CHANNEL),
            @Button(userView = "В чат", cbValue = ConsumerTypeStringView.SUPERGROUP)
    })
    @FilleeField(text = "Куда отправлять сообщения?", customFillHandler = ConsumerFilleeHandler.HANDLER_BEAN_NAME)
    private ConsumerType consumer;

    @Keyboard(buttons = {
            @Button(userView = "Готово", cbValue = "prepared")
    })
    @FilleeField(customFillHandler = PreparedFilleeHandler.HANDLER_BEAN_NAME)
    private Long consumerId;

    @FilleeField
    private String consumerName;

    @Keyboard(buttons = {
            @Button(userView = "Из канала", cbValue = SupplierTypeStringView.CHANNEL),
            @Button(userView = "Из чата", cbValue = SupplierTypeStringView.SUPERGROUP)
    })
    @FilleeField(text = "Откуда пересылать сообщения?", customFillHandler = SupplierFilleeHandler.HANDLER_BEAN_NAME)
    private SupplierType supplier;

    @Keyboard(buttons = {
            @Button(userView = "Готово", cbValue = "prepared")
    })
    @FilleeField(customFillHandler = PreparedFilleeHandler.HANDLER_BEAN_NAME)
    private Long supplierId;

    @FilleeField
    private String supplierName;

    @FilleeField(text = "Пришли ключевые слова, на которые будет срабатывать бот.\n" +
            "Пример ввода: слово1 слово2\n" +
            "Слова пиши одной строчкой через пробел.\n" +
            "Бот будет срабатывать на слова с любым регистром букв.", customFillHandler = KeyWordFilleeHandler.HANDLER_BEAN_NAME)
    private Set<String> keyWords;

    // TODO добавить откуда куда
    @FilleeField(text = "Отлично!\nБот начал транслирование сообщений.")
    private String completionMessage;

    private int fillCounter;

    @Override
    public int getCount() {
        return fillCounter;
    }

    @Override
    public void setCount(int count) {
        fillCounter = count;
    }
}
