package br.com.alelo.consumer.consumerpat.entrypoint.http.response;

import lombok.Data;

@Data
public class CreateConsumerHttpResponse implements HttpResponse {
    private Long id;
}
