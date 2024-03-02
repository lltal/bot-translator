package com.github.lltal.botcopier.shared.constants.consumer;

import com.github.lltal.converter.shared.annotation.Convertee;
import com.github.lltal.converter.shared.annotation.ConverteeField;
import com.github.lltal.botcopier.shared.constants.consumer.ConsumerBeanNames;
import com.github.lltal.botcopier.shared.constants.consumer.ConsumerTypeStringView;

@Convertee(converterBeanName = ConsumerBeanNames.CONVERTER)
public enum ConsumerType {
    @ConverteeField(stringView = ConsumerTypeStringView.ME)
    ME,
    @ConverteeField(stringView = ConsumerTypeStringView.SUPERGROUP)
    SUPERGROUP,
    @ConverteeField(stringView = ConsumerTypeStringView.CHANNEL)
    CHANNEL
}
