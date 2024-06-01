package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto;

import lombok.Builder;

@Builder
public record ProductDTO(Long id, String ean, String name, String description, double price, String productCode,
                         String Destination, SupplierDTO supplierDTO) {
}
