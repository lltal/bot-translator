package com.github.lltal.copier.redis.output.rpc.dto;

import com.github.lltal.copier.redis.model.UserActionEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserActionDTOFactory {

    public UserActionDTO createFromEntity(UserActionEntity userActionEntity) {
        UserActionDTO userActionDTO = new UserActionDTO();
        BeanUtils.copyProperties(userActionEntity, userActionDTO);

        return userActionDTO;
    }
}
