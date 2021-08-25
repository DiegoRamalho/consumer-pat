package br.com.alelo.consumer.consumerpat.core.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public abstract class Command<T extends Request, R> implements Serializable {
    protected T request;
    protected R response;
}
