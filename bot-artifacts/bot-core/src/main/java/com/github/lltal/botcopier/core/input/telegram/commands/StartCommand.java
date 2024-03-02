package com.github.lltal.botcopier.core.input.telegram.commands;

import com.github.lltal.botcopier.shared.constants.translation.TranslationBeanNames;
import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.filler.shared.ifc.AbstractResolver;
import com.github.lltal.filler.shared.ifc.AbstractSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.CommandOther;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.command.CommandContext;
import ru.wdeath.telegram.bot.starter.session.UserBotSession;

@CommandNames("/start")
@Component
public final class StartCommand {
    @Autowired
    @Qualifier(TranslationBeanNames.RESOLVER_BEAN_NAME)
    private AbstractResolver resolver;
    @Autowired
    @Qualifier(TranslationBeanNames.SENDER_BEAN_NAME)
    private AbstractSender sender;

    @CommandFirst
    public void execStart(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            @ParamName("userId") Long userId,
            UserBotSession userBotSession
    ) {
        TranslationDTO dto = new TranslationDTO(userId);
        userBotSession.setData(dto);
        BotApiMethod message = sender.getNextMessage(dto, chatId);
        context.getEngine().executeNotException(message);
    }

    @CommandOther
    public void execOther(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            UserBotSession userBotSession
    ) {
        TranslationDTO dto = (TranslationDTO) userBotSession.getData();

        resolver.resolve(dto, context);
    }
}