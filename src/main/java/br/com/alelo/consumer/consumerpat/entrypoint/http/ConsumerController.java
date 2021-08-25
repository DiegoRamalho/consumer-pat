package br.com.alelo.consumer.consumerpat.entrypoint.http;

import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateConsumerHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.UpdateConsumerHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.BaseHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.ConsumerHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.CreateConsumerHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.ErrorHttpResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ConsumerController {

    @GetMapping
    @ApiOperation(value = "Find all consumers.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All consumers."),
            @ApiResponse(code = 500, message = "Server errors", response = ErrorHttpResponse.class)
    })
    ResponseEntity<BaseHttpResponse<List<ConsumerHttpResponse>>> listAllConsumers();

    @PostMapping
    @ApiOperation(value = "Creates a new consumer.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Consumer created with success."),
            @ApiResponse(code = 400, message = "Validation errors", response = ErrorHttpResponse.class),
            @ApiResponse(code = 500, message = "Server errors", response = ErrorHttpResponse.class)
    })
    ResponseEntity<BaseHttpResponse<CreateConsumerHttpResponse>> createConsumer(@RequestBody CreateConsumerHttpRequest request);

    @PatchMapping
    @ApiOperation(value = "Update a consumer.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Consumer updated with success."),
            @ApiResponse(code = 400, message = "Validation errors", response = ErrorHttpResponse.class),
            @ApiResponse(code = 500, message = "Server errors", response = ErrorHttpResponse.class)
    })
    ResponseEntity<Void> updateConsumer(@RequestBody UpdateConsumerHttpRequest request);
}
