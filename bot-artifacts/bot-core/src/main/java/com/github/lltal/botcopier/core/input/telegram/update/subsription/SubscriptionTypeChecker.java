package com.github.lltal.botcopier.core.input.telegram.update.subsription;

import com.github.lltal.botcopier.shared.parsers.UpdateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SubscriptionTypeChecker {
    private final UpdateParser updateParser;
    private final Set<String> additionBindingStates = Set.of("administrator", "member");
    private final Set<String> removeBindingStates = Set.of("kicked", "left");

    public boolean isBotAdded(Update update) {
        String status = updateParser.getNewChatMemberStatus(update);
        return additionBindingStates.contains(status);
    }

    public boolean isBotRemoved(Update update) {
        String status = updateParser.getNewChatMemberStatus(update);
        return removeBindingStates.contains(status);
    }
}
