package com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.fillees;

import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.botcopier.core.services.factories.ConsumerEntityFactory;
import com.github.lltal.botcopier.core.services.psql.TranslationService;
import com.github.lltal.botcopier.shared.constants.translation.TranslationBeanNames;
import com.github.lltal.botcopier.shared.parsers.ContextParser;
import com.github.lltal.filler.shared.ifc.AbstractFiller;
import com.github.lltal.filler.shared.ifc.AbstractSender;
import com.github.lltal.filler.shared.ifc.CustomFilleeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component(KeyWordFilleeHandler.HANDLER_BEAN_NAME)
public class KeyWordFilleeHandler implements CustomFilleeHandler<TranslationDTO> {
    public static final String HANDLER_BEAN_NAME = "keyWordFilleeHandler";

    @Qualifier(TranslationBeanNames.FILLER_BEAN_NAME)
    @Autowired
    private AbstractFiller filler;
    @Qualifier(TranslationBeanNames.SENDER_BEAN_NAME)
    @Autowired
    private AbstractSender sender;
    @Autowired
    private ContextParser parser;
    @Autowired
    private ConsumerEntityFactory factory;
    @Autowired
    private TranslationService translationService;


    @Override
    public void handleField(TranslationDTO dto, CommandContext context) {
        Set<String> keyWords = Arrays.stream(
                (String[])(context.getData()))
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        filler.fill(dto, keyWords);

        translationService.save(dto);

        context.getEngine().executeNotException(
                sender.getNextMessage(
                        dto, parser.getChatId(context)));
    }
}
