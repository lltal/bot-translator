package com.github.lltal.copier.redis.service.redis.factories;

import com.github.lltal.botcopier.shared.constants.action.ActionType;
import com.github.lltal.botcopier.shared.constants.action.ChatActionType;
import com.github.lltal.copier.redis.model.UserActionEntity;
import org.springframework.stereotype.Component;

@Component
public class UserActionEntityFactory {

    public UserActionEntity createDefault(
            Long userId,
            Long chatId,
            ActionType actionType,
            ChatActionType ChatActionType,
            String title
    ) {
        return new UserActionEntity(
                userId,
                chatId,
                actionType,
                ChatActionType,
                title);
    }
}
