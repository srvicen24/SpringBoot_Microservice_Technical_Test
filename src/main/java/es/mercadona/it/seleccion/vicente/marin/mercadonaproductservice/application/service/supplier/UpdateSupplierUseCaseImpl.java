package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierDtoToSupplierMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierToSupplierDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier.UpdateSupplierUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingSupplierException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository.SupplierNotFoundException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateSupplierUseCaseImpl implements UpdateSupplierUseCase {

    private SupplierRepository supplierRepository;
    private SupplierDtoToSupplierMapper supplierDtoToSupplierMapper;
    private SupplierToSupplierDtoMapper supplierToSupplierDtoMapper;

    @CachePut(value = "suppliers", key = "#supplierDTO.reference")
    @Override
    public SupplierDTO updateSupplierById(Long id, SupplierDTO supplierDTO) {
        validateSupplier(supplierDTO);
        SupplierId supplierId = new SupplierId(id);
        Supplier supplier = supplierDtoToSupplierMapper.mapSupplierDtoToSupplier(supplierDTO);
        Optional<Supplier> updatedSupplier = supplierRepository.updateById(supplierId, supplier);
        if (updatedSupplier.isEmpty()) {
            throw new SupplierNotFoundException(id);
        }
        return supplierToSupplierDtoMapper.mapSupplierToSupplierDto(updatedSupplier.get());
    }

    @CachePut(value = "suppliers", key = "#supplierDTO.reference")
    @Override
    public SupplierDTO updateSupplierByReference(String reference, SupplierDTO supplierDTO) {
        validateSupplier(supplierDTO);
        Supplier supplier = supplierDtoToSupplierMapper.mapSupplierDtoToSupplier(supplierDTO);
        SupplierReference supplierReference = new SupplierReference(reference);
        Optional<Supplier> updatedSupplier = supplierRepository.updateByReference(supplierReference, supplier);
        if (updatedSupplier.isEmpty()) {
            throw new SupplierNotFoundException(reference);
        }
        return supplierToSupplierDtoMapper.mapSupplierToSupplierDto(updatedSupplier.get());
    }

    private void validateSupplier(SupplierDTO supplierDTO) {
        if (supplierDTO == null) {
            throw new ValidatingSupplierException("SupplierDTO is null");
        }
    }
}
