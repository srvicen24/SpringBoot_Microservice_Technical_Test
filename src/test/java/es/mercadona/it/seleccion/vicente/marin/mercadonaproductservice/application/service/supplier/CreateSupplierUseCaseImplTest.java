package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.service.supplier;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.dto.SupplierDTO;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierDtoToSupplierMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.application.mapper.supplier.SupplierToSupplierDtoMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.exception.model.ValidatingSupplierException;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateSupplierUseCaseImplTest {

    @InjectMocks
    private CreateSupplierUseCaseImpl createSupplierUseCaseImpl;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierDtoToSupplierMapper supplierDtoToSupplierMapper;

    @Mock
    private SupplierToSupplierDtoMapper supplierToSupplierDtoMapper;

    private SupplierDTO supplierDTO;

    @BeforeEach
    void setUp() {
        createSupplierUseCaseImpl = spy(new CreateSupplierUseCaseImpl(supplierRepository, supplierDtoToSupplierMapper
                , supplierToSupplierDtoMapper));
        MockitoAnnotations.openMocks(this);
        supplierDTO = SupplierDTO.builder().name("Supplier").reference("Reference").build();
        Supplier supplier = mock(Supplier.class);
        when(supplierDtoToSupplierMapper.mapSupplierDtoToSupplier(any())).thenReturn(supplier);
        when(supplierToSupplierDtoMapper.mapSupplierToSupplierDto(any())).thenReturn(supplierDTO);
    }

    @Test
    void givenValidSupplierDto_whenCreateSupplier_thenSupplierIsCreated() {
        createSupplierUseCaseImpl.createSupplier(supplierDTO);
        verify(supplierRepository, times(1)).save(any());
    }

    @Test
    void givenNullSupplierDto_whenCreateSupplier_thenThrowValidatingSupplierException() {
        when(supplierDtoToSupplierMapper.mapSupplierDtoToSupplier(null)).thenThrow(ValidatingSupplierException.class);
        assertThrows(ValidatingSupplierException.class, () -> createSupplierUseCaseImpl.createSupplier(null));
    }

}
