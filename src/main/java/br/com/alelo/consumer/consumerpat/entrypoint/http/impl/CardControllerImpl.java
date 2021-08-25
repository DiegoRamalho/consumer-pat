package br.com.alelo.consumer.consumerpat.entrypoint.http.impl;

import br.com.alelo.consumer.consumerpat.core.usecase.UseCaseHandler;
import br.com.alelo.consumer.consumerpat.core.usecase.card.balance.dto.IncreaseCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto.CreateTransactionDTO;
import br.com.alelo.consumer.consumerpat.entrypoint.http.CardController;
import br.com.alelo.consumer.consumerpat.entrypoint.http.adapter.CardHttpAdapter;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateTransactionHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.IncreaseCardBalanceHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.BaseHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.CreateTransactionHttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardControllerImpl extends BaseController implements CardController {

    private final UseCaseHandler useCase;

    @Override
    public ResponseEntity<Void> setBalance(@RequestBody IncreaseCardBalanceHttpRequest request) {
        useCase.execute(IncreaseCardBalanceDTO.builder().request(request).build());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<BaseHttpResponse<CreateTransactionHttpResponse>> buy(CreateTransactionHttpRequest request) {
        final CreateTransactionDTO dto = CreateTransactionDTO.builder().request(request).build();
        useCase.execute(dto);
        return of(CardHttpAdapter.from(dto.getResponse()), HttpStatus.CREATED);
    }

}
