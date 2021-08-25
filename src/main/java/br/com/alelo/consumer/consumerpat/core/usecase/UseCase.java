package br.com.alelo.consumer.consumerpat.core.usecase;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;

public interface UseCase<T extends Command<?, ?>> {

    void execute(T dto);
}
