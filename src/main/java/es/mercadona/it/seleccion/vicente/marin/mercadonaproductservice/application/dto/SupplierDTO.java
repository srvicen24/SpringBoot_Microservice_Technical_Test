package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto;

import lombok.Builder;

@Builder
public record SupplierDTO(Long id, String reference, String name) {
}
