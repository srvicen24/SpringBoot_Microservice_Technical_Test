package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.web.error;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ErrorMessage(Instant timestamp, String message, String status) {
}
