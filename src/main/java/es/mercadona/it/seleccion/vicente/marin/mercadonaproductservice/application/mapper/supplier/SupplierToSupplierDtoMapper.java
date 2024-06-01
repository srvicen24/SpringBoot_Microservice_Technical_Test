package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;

import java.util.List;

public interface SupplierToSupplierDtoMapper {

    SupplierDTO mapSupplierToSupplierDto(Supplier supplier);

    List<SupplierDTO> mapSupplierListToSupplierDtoList(List<Supplier> list);
}
