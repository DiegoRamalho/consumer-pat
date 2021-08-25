package br.com.alelo.consumer.consumerpat.entrypoint.http.impl;

import br.com.alelo.consumer.consumerpat.core.usecase.UseCaseHandler;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.find.dto.ListConsumerDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.entrypoint.http.ConsumerController;
import br.com.alelo.consumer.consumerpat.entrypoint.http.adapter.ConsumerHttpAdapter;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateConsumerHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.UpdateConsumerHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.BaseHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.ConsumerHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.CreateConsumerHttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerControllerImpl extends BaseController implements ConsumerController {

    private final UseCaseHandler useCase;

    @Override
    public ResponseEntity<BaseHttpResponse<List<ConsumerHttpResponse>>> listAllConsumers() {
        ListConsumerDTO dto = ListConsumerDTO.builder().build();
        useCase.execute(dto);
        return of(dto.getResponse().stream().map(ConsumerHttpAdapter::from)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseHttpResponse<CreateConsumerHttpResponse>> createConsumer(@RequestBody CreateConsumerHttpRequest request) {
        CreateConsumerDTO dto = CreateConsumerDTO.builder()
                .request(request)
                .build();
        useCase.execute(dto);
        return of(ConsumerHttpAdapter.from(dto.getResponse()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateConsumer(@RequestBody UpdateConsumerHttpRequest request) {
        useCase.execute(UpdateConsumerDTO.builder().request(request).build());
        return ResponseEntity.noContent().build();
    }
}
