package com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.buttons;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

@Component(MeButtonHandler.HANDLER_BEAN_NAME)
public class MeButtonHandler implements CustomButtonHandler<TranslationDTO> {

    public static final String HANDLER_BEAN_NAME = "meButtonHandler";

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
    public void handleCLick(TranslationDTO dto, String cbData, CommandContext context) {

        context.getEngine().executeNotException(sender.closeCb(context));

        filler.fill(dto, converter.convertToEnumValue(cbData));
        filler.fill(dto, parser.getUserId(context));
        filler.fill(dto, ConsumerTypeStringView.ME_ENGLISH);

        BotApiMethod messages = sender.getNextMessage(dto, parser.getChatId(context));

        context.getEngine().executeNotException(messages);
    }
}
