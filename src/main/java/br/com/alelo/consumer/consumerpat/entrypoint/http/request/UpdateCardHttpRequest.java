package br.com.alelo.consumer.consumerpat.entrypoint.http.request;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto.UpdateCardRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardHttpRequest implements HttpRequest, UpdateCardRequest {
    private Long id;
    private String number;
    private Set<CardType> types;
}
