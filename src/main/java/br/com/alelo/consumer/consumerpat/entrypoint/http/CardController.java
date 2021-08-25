package br.com.alelo.consumer.consumerpat.entrypoint.http;

import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateTransactionHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.IncreaseCardBalanceHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.BaseHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.CreateTransactionHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.ErrorHttpResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CardController {

    @PostMapping
    @ApiOperation(value = "Credits a value to a card.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Operation performed successfully"),
            @ApiResponse(code = 400, message = "Validation errors", response = ErrorHttpResponse.class),
            @ApiResponse(code = 500, message = "Server errors", response = ErrorHttpResponse.class)
    })
    ResponseEntity<Void> setBalance(@RequestBody IncreaseCardBalanceHttpRequest request);

    @PostMapping("/buy")
    @ApiOperation(value = "...")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Operation performed successfully"),
            @ApiResponse(code = 400, message = "Validation errors", response = ErrorHttpResponse.class),
            @ApiResponse(code = 500, message = "Server errors", response = ErrorHttpResponse.class)
    })
    ResponseEntity<BaseHttpResponse<CreateTransactionHttpResponse>> buy(@RequestBody CreateTransactionHttpRequest request);
}
