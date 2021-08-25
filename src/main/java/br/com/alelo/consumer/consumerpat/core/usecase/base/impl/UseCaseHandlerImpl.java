package br.com.alelo.consumer.consumerpat.core.usecase.base.impl;

import br.com.alelo.consumer.consumerpat.core.exception.InternalException;
import br.com.alelo.consumer.consumerpat.core.usecase.UseCase;
import br.com.alelo.consumer.consumerpat.core.usecase.UseCaseHandler;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;
import br.com.alelo.consumer.consumerpat.core.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
@Log4j2
public class UseCaseHandlerImpl implements UseCaseHandler {

    private final ApplicationContext context;

    private final Map<Class<?>, UseCase<Command<?, ?>>> useCases = new LinkedHashMap<>();

    @PostConstruct
    public void load() {
        useCases.clear();
        context.getBeansOfType(UseCase.class).values()
                .forEach(it -> {
                    Class<?> clazz = (Class<?>) ((ParameterizedType) ((Class<?>) it.getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0]).getActualTypeArguments()[0];
                    useCases.put(clazz, it);
                });
    }

    @Override
    public void execute(Command<?, ?> dto) {
        try {
            useCases.get(dto.getClass()).execute(dto);
        } catch (Exception ex) {
            log.atError().log(Constants.AN_UNEXPECTED_ERROR_HAS_OCCURRED, ex);
            throw new InternalException(getMessage(ex), ex, Constants.AN_UNEXPECTED_ERROR_HAS_OCCURRED);
        }
    }

    private String getMessage(Exception ex) {
        Throwable cause = ex;
        int level = 0;
        while (cause.getCause() != cause && nonNull(cause.getCause()) && ++level < 10) {
            cause = cause.getCause();
        }
        final String message = cause.getMessage();
        return ObjectUtils.isEmpty(message) ? cause.toString() : message;
    }
}
