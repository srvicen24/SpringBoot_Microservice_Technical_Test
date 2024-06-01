package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierDtoToSupplierMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierToSupplierDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier.CreateSupplierUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateSupplierUseCaseImpl implements CreateSupplierUseCase {

    private SupplierRepository supplierRepository;
    private SupplierDtoToSupplierMapper supplierDtoToSupplierMapper;
    private SupplierToSupplierDtoMapper supplierToSupplierDtoMapper;

    @CachePut(value = "suppliers", key = "#supplierDTO.reference")
    @Override
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = supplierDtoToSupplierMapper.mapSupplierDtoToSupplier(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierToSupplierDtoMapper.mapSupplierToSupplierDto(savedSupplier);
    }
}
