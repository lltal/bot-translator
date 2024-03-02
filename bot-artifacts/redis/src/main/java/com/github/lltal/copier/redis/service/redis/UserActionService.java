package com.github.lltal.copier.redis.service.redis;

import com.github.lltal.botcopier.shared.constants.action.ActionType;
import com.github.lltal.botcopier.shared.constants.action.ChatActionType;
import com.github.lltal.copier.redis.output.rpc.dto.UserActionDTO;

public interface UserActionService {
    void saveUserAction(
            Long userId,
            Long chatId,
            ActionType actionType,
            ChatActionType ChatActionType,
            String title
    );

    UserActionDTO getUserById(Long userId);

    void deleteById(Long userId);
}
