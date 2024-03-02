package com.github.lltal.copier.redis.model;

import com.github.lltal.botcopier.shared.constants.action.ActionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("UserActionEntity")
@Getter
@AllArgsConstructor
public class UserActionEntity {
    @Id
    private Long userId;
    private Long chatId;
    private ActionType actionType;
    private com.github.lltal.botcopier.shared.constants.action.ChatActionType ChatActionType;
    private String chatTitle;
}
