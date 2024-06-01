package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeleteSupplierUseCaseImplTest {

    @InjectMocks
    private DeleteSupplierUseCaseImpl deleteSupplierUseCaseImpl;

    @Mock
    private SupplierRepository supplierRepository;

    private SupplierReference supplierReference;
    private SupplierId supplierId;

    @BeforeEach
    void setUp() {
        deleteSupplierUseCaseImpl = spy(new DeleteSupplierUseCaseImpl(supplierRepository));
        MockitoAnnotations.openMocks(this);
        doNothing().when(supplierRepository).deleteByReference(any());
        doNothing().when(supplierRepository).deleteById(any());
        supplierReference = new SupplierReference("1234567");
        supplierId = new SupplierId(1L);
    }

    @Test
    void givenValidSupplierReference_whenDeleteSupplier_thenSupplierIsDeleted() {
        deleteSupplierUseCaseImpl.deleteSupplierByReference(supplierReference.value());
        verify(supplierRepository, times(1)).deleteByReference(supplierReference);
    }

    @Test
    void givenValidSupplierId_whenDeleteSupplier_thenSupplierIsDeleted() {
        deleteSupplierUseCaseImpl.deleteSupplierById(supplierId.value());
        verify(supplierRepository, times(1)).deleteById(supplierId);
    }

}
