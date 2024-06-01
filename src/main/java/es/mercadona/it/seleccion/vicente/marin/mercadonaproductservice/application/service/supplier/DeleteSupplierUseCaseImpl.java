package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.usecase.supplier.DeleteSupplierUseCase;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteSupplierUseCaseImpl implements DeleteSupplierUseCase {

    private SupplierRepository supplierRepository;

    @CacheEvict(value = "suppliers", key = "#reference")
    @Override
    public void deleteSupplierByReference(String reference) {
        supplierRepository.deleteByReference(new SupplierReference(reference));
    }

    @CacheEvict(value = "suppliers", key = "#id")
    @Override
    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(new SupplierId(id));
    }
}
