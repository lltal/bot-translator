package com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.fillees;

import com.github.lltal.botcopier.shared.constants.supplier.SupplierBeanNames;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierType;
import com.github.lltal.botcopier.shared.constants.supplier.SupplierTypeStringView;
import com.github.lltal.botcopier.shared.constants.translation.TranslationBeanNames;
import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.converter.shared.ifc.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.lltal.botcopier.shared.parsers.ContextParser;
import com.github.lltal.filler.shared.ifc.AbstractFiller;
import com.github.lltal.filler.shared.ifc.AbstractSender;
import com.github.lltal.filler.shared.ifc.CustomFilleeHandler;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

@Component(SupplierFilleeHandler.HANDLER_BEAN_NAME)
public class SupplierFilleeHandler implements CustomFilleeHandler<TranslationDTO> {
    public static final String HANDLER_BEAN_NAME = "supplierFilleeHandler";

    @Qualifier(TranslationBeanNames.FILLER_BEAN_NAME)
    @Autowired
    private AbstractFiller filler;
    @Qualifier(TranslationBeanNames.SENDER_BEAN_NAME)
    @Autowired
    private AbstractSender sender;
    @Autowired
    private ContextParser parser;
    @Autowired
    @Qualifier(SupplierBeanNames.CONVERTER)
    private AbstractConverter<SupplierType> converter;

    @Override
    public void handleField(TranslationDTO dto, CommandContext context) {

        context.getEngine().executeNotException(sender.closeCb(context));

        filler.fill(dto, converter.convertToEnumValue(context.getName()));

        String text = text(context.getName());
        BotApiMethod message = sender.getNextMessage(dto, parser.getChatId(context), text);

        context.getEngine().executeNotException(message);
    }

    private String text(String supplierType) {
        return String.format(
                "Добавь бота в %s, из которого надо читать сообщения." +
                        " После добавления, нажми на кнопку ниже",
                supplierType.toLowerCase());
    }
}