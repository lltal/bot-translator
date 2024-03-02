package com.github.lltal.copier.redis.service.redis.impl;

import com.github.lltal.botcopier.shared.constants.action.ActionType;
import com.github.lltal.botcopier.shared.constants.action.ChatActionType;
import com.github.lltal.copier.redis.model.UserActionEntity;
import com.github.lltal.copier.redis.service.redis.factories.UserActionEntityFactory;
import com.github.lltal.copier.redis.output.repositories.UserActionRepository;
import com.github.lltal.copier.redis.output.rpc.dto.UserActionDTO;
import com.github.lltal.copier.redis.output.rpc.dto.UserActionDTOFactory;
import com.github.lltal.copier.redis.service.redis.UserActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActionServiceImpl implements UserActionService {
    private final UserActionRepository userActionRepo;
    private final UserActionEntityFactory userActionEntityFactory;
    private final UserActionDTOFactory userActionDTOFactory;

    @Override
    public void saveUserAction(
            Long userId,
            Long chatId,
            ActionType actionType,
            ChatActionType ChatActionType,
            String title
    ) {
        userActionRepo.save(
                userActionEntityFactory.createDefault(
                        userId, chatId, actionType, ChatActionType, title));
    }

    @Override
    public UserActionDTO getUserById(Long userId) {
        UserActionEntity userActionEntity = userActionRepo.findById(userId).orElseThrow();
        return userActionDTOFactory.createFromEntity(userActionEntity);
    }

    @Override
    public void deleteById(Long userId) {
        userActionRepo.deleteById(userId);
    }
}
