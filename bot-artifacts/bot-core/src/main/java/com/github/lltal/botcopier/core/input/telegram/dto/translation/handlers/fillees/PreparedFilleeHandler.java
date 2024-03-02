package com.github.lltal.botcopier.core.input.telegram.dto.translation.handlers.fillees;

import com.github.lltal.botcopier.shared.constants.translation.TranslationBeanNames;
import com.github.lltal.botcopier.core.input.telegram.dto.translation.TranslationDTO;
import com.github.lltal.botcopier.shared.constants.action.ActionType;
import com.github.lltal.copier.redis.output.rpc.dto.UserActionDTO;
import com.github.lltal.copier.redis.service.redis.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.lltal.botcopier.shared.parsers.ContextParser;
import com.github.lltal.filler.shared.ifc.AbstractFiller;
import com.github.lltal.filler.shared.ifc.AbstractSender;
import com.github.lltal.filler.shared.ifc.CustomFilleeHandler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

@Component(PreparedFilleeHandler.HANDLER_BEAN_NAME)
public class PreparedFilleeHandler implements CustomFilleeHandler<TranslationDTO> {
    public static final String HANDLER_BEAN_NAME = "PreparedFilleeHandlerHandler";

    @Qualifier(TranslationBeanNames.FILLER_BEAN_NAME)
    @Autowired
    private AbstractFiller filler;
    @Qualifier(TranslationBeanNames.SENDER_BEAN_NAME)
    @Autowired
    private AbstractSender sender;
    @Autowired
    private ContextParser parser;
    @Autowired
    private UserActionService actionService;

    @Override
    public void handleField(TranslationDTO dto, CommandContext context) {
        context.getEngine().executeNotException(sender.closeCb(context));

        UserActionDTO userActionDTO = actionService.getUserById(parser.getUserId(context));
        BotApiMethod message;

        if (userActionDTO.getActionType() == ActionType.ADDED) {

            filler.fill(dto, userActionDTO.getChatId());
            filler.fill(dto, userActionDTO.getChatTitle());

            message = sender.getNextMessage(dto, parser.getChatId(context));
        } else {

            message = createErrorMessage(userActionDTO.getChatId());
        }

        context.getEngine().executeNotException(message);
    }

    private SendMessage createErrorMessage(Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text("Ошибка!\nДобавьте бота в чат")
                .build();
    }
}
