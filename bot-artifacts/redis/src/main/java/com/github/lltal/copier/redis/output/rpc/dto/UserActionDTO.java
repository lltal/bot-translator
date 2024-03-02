package com.github.lltal.copier.redis.output.rpc.dto;


import com.github.lltal.botcopier.shared.constants.action.ActionType;
import com.github.lltal.botcopier.shared.constants.action.ChatActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActionDTO {
    private Long userId;
    private Long chatId;
    private ActionType actionType;
    private ChatActionType ChatActionType;
    private String chatTitle;
}
