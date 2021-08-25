package br.com.alelo.consumer.consumerpat.core.usecase;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;

public interface UseCaseHandler {
    void execute(Command<?, ?> dto);
}
