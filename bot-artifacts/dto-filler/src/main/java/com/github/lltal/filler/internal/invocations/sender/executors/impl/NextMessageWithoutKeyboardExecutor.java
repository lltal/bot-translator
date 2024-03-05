package com.github.lltal.filler.internal.invocations.sender.executors.impl;

import com.github.lltal.filler.internal.invocations.common.ifc.ReturnableMethodExecutor;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.sender.extractors.RuntimeTextExtractor;
import com.github.lltal.filler.shared.ifc.Countable;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.util.Map;

public class NextMessageWithoutKeyboardExecutor implements ReturnableMethodExecutor<BotApiMethod> {
    private final Map<Integer, DtoFieldInfo> fields;

    public NextMessageWithoutKeyboardExecutor(
            Map<Integer, DtoFieldInfo> fields
    ) {
        this.fields = fields;
    }

    @Override
    public BotApiMethod execute(Object[] args) {

        Countable dto = (Countable) args[0];

        int currentCount = dto.getCount();
        DtoFieldInfo fieldInfo = fields.get(currentCount);

        String runtimeText = RuntimeTextExtractor.extractRuntimeText(args);

        return fieldInfo
                .getMessageCreatorWithoutKeyboard()
                .create((Long) args[1], runtimeText);
    }
}
