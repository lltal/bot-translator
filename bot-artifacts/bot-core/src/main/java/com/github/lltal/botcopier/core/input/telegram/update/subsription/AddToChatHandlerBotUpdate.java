package com.github.lltal.botcopier.core.input.telegram.update.subsription;

import com.github.lltal.botcopier.shared.constants.action.ConsumerActionBeanNames;
import com.github.lltal.botcopier.shared.constants.consumer.ConsumerBeanNames;
import com.github.lltal.botcopier.shared.parsers.UpdateParser;
import com.github.lltal.converter.shared.ifc.AbstractConverter;
import com.github.lltal.botcopier.shared.constants.action.ActionType;
import com.github.lltal.botcopier.shared.constants.action.ChatActionType;
import com.github.lltal.copier.redis.service.redis.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.interfaces.HandlerBotUpdate;

@Component
public class AddToChatHandlerBotUpdate implements HandlerBotUpdate {
    @Autowired
    private UserActionService actionService;
    @Autowired
    private UpdateParser updateParser;
    @Autowired
    private SubscriptionTypeChecker checker;
    @Autowired
    @Qualifier(ConsumerActionBeanNames.CONVERTER)
    private AbstractConverter<ChatActionType> converter;

    @Override
    public void update(TelegramLongPollingEngine engine, Update update) {

        if (update.getMyChatMember() == null ||
                !checker.isBotAdded(update)) {
            return;
        }

        ChatActionType ChatActionType = converter
                .convertToEnumValue(
                        updateParser.getMyChatMemberChatActionType(update));

        actionService.saveUserAction(
                updateParser.getMyChatMemberUserId(update),
                updateParser.getMyChatMemberChatId(update),
                ActionType.ADDED,
                ChatActionType,
                updateParser.getUserName(update));
    }

    @Override
    public int priority() {
        return 30;
    }
}
