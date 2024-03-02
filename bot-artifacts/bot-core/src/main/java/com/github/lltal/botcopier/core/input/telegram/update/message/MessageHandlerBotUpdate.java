package com.github.lltal.botcopier.core.input.telegram.update.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.interfaces.HandlerBotUpdate;

@Component
@RequiredArgsConstructor
public class MessageHandlerBotUpdate implements HandlerBotUpdate {
    private final MessageUpdateExecutor updateExecutor;

    @Override
    public void update(TelegramLongPollingEngine engine, Update update) {

        if (update.getMessage() != null &&
                update.getMessage().getText() != null) {
            updateExecutor
                    .executeMessageUpdate(
                            engine,
                            update.getMessage());
        }
    }

    @Override
    public int priority() {
        return 28;
    }
}
