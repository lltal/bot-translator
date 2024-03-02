package com.github.lltal.botcopier.shared.parsers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

@Component
@RequiredArgsConstructor
public class ContextParser {
    private final UpdateParser updateParser;

    public Long getChatId(CommandContext context) {
        return context.getChatBotSession().getChatId();
    }

    public Long getUserId(CommandContext context) {
        return context.getUserBotSession().getUserId();
    }

    public Long getForwardChatId(CommandContext context) {
        return updateParser.getForwardChatId(context.getUpdate());
    }
}
