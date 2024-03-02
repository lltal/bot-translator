package com.github.lltal.botcopier.core.input.telegram.update.subsription;

import com.github.lltal.botcopier.shared.parsers.UpdateParser;
import com.github.lltal.copier.redis.service.redis.UserActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.interfaces.HandlerBotUpdate;

@Component
@RequiredArgsConstructor
public class RemoveFromChatHandlerBotUpdate implements HandlerBotUpdate {
    private final UserActionService actionService;
    private final UpdateParser updateParser;
    private final SubscriptionTypeChecker checker;

    @Override
    public void update(TelegramLongPollingEngine engine, Update update) {

        if (update.getMyChatMember() == null ||
                !checker.isBotRemoved(update)) {
            return;
        }

        actionService.deleteById(
                updateParser.getMyChatMemberUserId(update));
    }

    @Override
    public int priority() {
        return 29;
    }
}
