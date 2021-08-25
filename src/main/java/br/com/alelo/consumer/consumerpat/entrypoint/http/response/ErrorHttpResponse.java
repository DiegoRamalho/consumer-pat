package br.com.alelo.consumer.consumerpat.entrypoint.http.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorHttpResponse implements HttpResponse {
    private String userMessage;
    private String developerMessage;
}
