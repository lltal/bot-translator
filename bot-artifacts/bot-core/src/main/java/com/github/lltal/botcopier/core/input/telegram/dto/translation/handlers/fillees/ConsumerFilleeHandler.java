package com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.fillees;

import com.github.lltal.botcopier.shared.constants.consumer.ConsumerBeanNames;
import com.github.lltal.botcopier.shared.constants.consumer.ConsumerType;
import com.github.lltal.botcopier.shared.constants.consumer.ConsumerTypeStringView;
import com.github.lltal.botcopier.shared.constants.translation.TranslationBeanNames;
import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.botcopier.shared.parsers.ContextParser;
import com.github.lltal.converter.shared.ifc.AbstractConverter;
import com.github.lltal.filler.shared.ifc.AbstractFiller;
import com.github.lltal.filler.shared.ifc.AbstractSender;
import com.github.lltal.filler.shared.ifc.CustomButtonHandler;
import com.github.lltal.filler.shared.ifc.CustomFilleeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

@Component(ConsumerFilleeHandler.HANDLER_BEAN_NAME)
public class ConsumerFilleeHandler implements CustomFilleeHandler<TranslationDTO> {
    public static final String HANDLER_BEAN_NAME = "ConsumerFilleeHandler";

    @Qualifier(TranslationBeanNames.FILLER_BEAN_NAME)
    @Autowired
    private AbstractFiller filler;
    @Qualifier(TranslationBeanNames.SENDER_BEAN_NAME)
    @Autowired
    private AbstractSender sender;
    @Autowired
    private ContextParser parser;
    @Autowired
    @Qualifier(ConsumerBeanNames.CONVERTER)
    private AbstractConverter<ConsumerType> converter;

    @Override
    public void handleField(TranslationDTO dto, CommandContext context) {

        context.getEngine().executeNotException(sender.closeCb(context));

        filler.fill(dto, converter.convertToEnumValue(context.getName()));

        String text = text(context.getName());
        BotApiMethod messages = sender.getNextMessage(dto, parser.getChatId(context), text);

        context.getEngine().executeNotException(messages);
    }

    private String text(String consumerType) {
        return String.format(
                "Добавь бота в %s, в который пересылать сообщения." +
                " После добавления, нажми на кнопку ниже",
                consumerType.toLowerCase());
    }
}
