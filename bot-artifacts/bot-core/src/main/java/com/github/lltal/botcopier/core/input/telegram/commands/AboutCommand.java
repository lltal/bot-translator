package com.github.lltal.botcopier.core.input.telegram.commands;

import com.github.lltal.botcopier.shared.constants.about.AboutBeanNames;
import com.github.lltal.botcopier.core.input.telegram.dto.about.AboutDTO;
import com.github.lltal.filler.shared.ifc.AbstractSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.command.CommandContext;
import ru.wdeath.telegram.bot.starter.session.UserBotSession;

@CommandNames("/about")
@Component
public final class AboutCommand {
    @Autowired
    @Qualifier(AboutBeanNames.SENDER_BEAN_NAME)
    private AbstractSender sender;

    @CommandFirst
    public void execStart(
            CommandContext context,
            @ParamName("chatId") Long chatId,
            UserBotSession userBotSession
    ) {
        AboutDTO dto = new AboutDTO();
        userBotSession.setData(dto);
        BotApiMethod message = sender.getNextMessage(dto, chatId);
        context.getEngine().executeNotException(message);
    }
}
