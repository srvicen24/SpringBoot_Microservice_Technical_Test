package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierToSupplierDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier.GetSupplierUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.repository.SupplierNotFoundException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetSupplierUseCaseImpl implements GetSupplierUseCase {

    private SupplierRepository supplierRepository;
    private SupplierToSupplierDtoMapper supplierToSupplierDtoMapper;

    @Cacheable(value = "suppliers", key = "#reference")
    @Override
    public SupplierDTO getSupplierByReference(String reference) {
        Optional<Supplier> supplier = supplierRepository.findByReference(new SupplierReference(reference));
        if (supplier.isEmpty()) {
            throw new SupplierNotFoundException(reference);
        }
        return supplierToSupplierDtoMapper.mapSupplierToSupplierDto(supplier.get());
    }

    @Cacheable(value = "suppliers", key = "#id")
    @Override
    public SupplierDTO getSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(new SupplierId(id));
        if (supplier.isEmpty()) {
            throw new SupplierNotFoundException(id);
        }
        return supplierToSupplierDtoMapper.mapSupplierToSupplierDto(supplier.get());
    }

    @Cacheable(value = "suppliers", key = "#root.methodName")
    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierToSupplierDtoMapper.mapSupplierListToSupplierDtoList(supplierRepository.findAll());
    }
}
