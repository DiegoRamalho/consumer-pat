package br.com.alelo.consumer.consumerpat.core.usecase.card.balance.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.VoidParameter;
import lombok.Builder;

public class IncreaseCardBalanceDTO extends Command<IncreaseCardBalanceRequest, VoidParameter> {
    @Builder
    public IncreaseCardBalanceDTO(IncreaseCardBalanceRequest request, VoidParameter response) {
        super(request, response);
    }
}