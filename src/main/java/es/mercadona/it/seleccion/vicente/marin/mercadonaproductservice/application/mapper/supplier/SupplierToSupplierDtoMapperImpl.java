package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingSupplierException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierToSupplierDtoMapperImpl implements SupplierToSupplierDtoMapper {

    @Override
    public SupplierDTO mapSupplierToSupplierDto(Supplier supplier) {
        validateSupplier(supplier);
        return buildSupplierDTO(supplier);
    }

    @Override
    public List<SupplierDTO> mapSupplierListToSupplierDtoList(List<Supplier> list) {
        return list.stream().map(this::buildSupplierDTO).toList();
    }

    private void validateSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new ValidatingSupplierException("Supplier is null");
        }
    }

    private SupplierDTO buildSupplierDTO(Supplier supplier) {
        return SupplierDTO.builder()
                          .id(supplier.supplierId().value())
                          .reference(supplier.reference().value())
                          .name(supplier.name())
                          .build();
    }
}
