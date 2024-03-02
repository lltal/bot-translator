package com.github.lltal.botcopier.core.input.telegram.dto.about;

import com.github.lltal.botcopier.shared.constants.about.AboutBeanNames;
import com.github.lltal.filler.shared.annotation.Fillee;
import com.github.lltal.filler.shared.annotation.FilleeField;
import com.github.lltal.filler.shared.ifc.Countable;
import lombok.Data;

@Fillee(
        senderBeanName = AboutBeanNames.SENDER_BEAN_NAME,
        fillerBeanName = AboutBeanNames.FILLER_BEAN_NAME,
        resolverBeanName = AboutBeanNames.RESOLVER_BEAN_NAME
)
@Data
public class AboutDTO implements Countable {
    @FilleeField(text = "Бот для копирования сообщений.")
    private Object aboutMessage;

    private int fillCounter;

    @Override
    public int getCount() {
        return fillCounter;
    }

    @Override
    public void setCount(int count) {
        fillCounter = count;
    }
}
