package com.github.lltal.botcopier.shared.constants.action;

import com.github.lltal.converter.shared.annotation.Convertee;
import com.github.lltal.converter.shared.annotation.ConverteeField;
import lombok.Getter;

@Getter
@Convertee(converterBeanName = ConsumerActionBeanNames.CONVERTER)
public enum ChatActionType {
    @ConverteeField(stringView = ConsumerActionTypeStringView.SUPERGROUP)
    SUPERGROUP,
    @ConverteeField(stringView = ConsumerActionTypeStringView.CHANNEL)
    CHANNEL
}
