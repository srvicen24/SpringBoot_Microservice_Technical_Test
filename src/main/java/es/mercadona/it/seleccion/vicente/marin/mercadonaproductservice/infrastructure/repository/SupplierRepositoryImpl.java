package es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.repository;

import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.Supplier;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierId;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.model.supplier.SupplierReference;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.domain.repository.SupplierRepository;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.entity.SupplierEntity;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.mapper.SupplierEntityMapper;
import es.mercadona.it.seleccion.vicente.marin.mercadonaproductservice.infrastructure.persistence.JpaSupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SupplierRepositoryImpl implements SupplierRepository {

    private final JpaSupplierRepository jpaSupplierRepository;
    private final SupplierEntityMapper supplierEntityMapper;

    @Override
    public Optional<Supplier> findByReference(SupplierReference reference) {
        Optional<SupplierEntity> supplier = jpaSupplierRepository.findByReference(reference.value());
        return supplier.map(supplierEntityMapper::toDomain);
    }

    @Override
    public Supplier save(Supplier supplier) {
        SupplierEntity supplierEntity = supplierEntityMapper.toEntity(supplier);
        SupplierEntity savedSupplier = jpaSupplierRepository.save(supplierEntity);
        return supplierEntityMapper.toDomain(savedSupplier);
    }

    @Override
    @Transactional
    public void deleteByReference(SupplierReference reference) {
        jpaSupplierRepository.deleteByReference(reference.value());
    }

    @Override
    public Optional<Supplier> updateById(SupplierId id, Supplier supplier) {
        Optional<SupplierEntity> supplierEntity = jpaSupplierRepository.findById(id.value());
        return updateSupplier(supplier, supplierEntity);
    }

    @Override
    public Optional<Supplier> updateByReference(SupplierReference reference, Supplier supplier) {
        Optional<SupplierEntity> supplierEntity = jpaSupplierRepository.findByReference(reference.value());
        return updateSupplier(supplier, supplierEntity);
    }

    private Optional<Supplier> updateSupplier(Supplier supplier, Optional<SupplierEntity> supplierEntity) {
        if (supplierEntity.isEmpty()) {
            return Optional.empty();
        }
        SupplierEntity updatedSupplier = supplierEntityMapper.toEntity(supplier);
        updatedSupplier.setId(supplierEntity.get().getId());
        SupplierEntity savedSupplier = jpaSupplierRepository.save(updatedSupplier);
        return Optional.of(supplierEntityMapper.toDomain(savedSupplier));
    }

    @Override
    public List<Supplier> findAll() {
        return jpaSupplierRepository.findAll().stream().map(supplierEntityMapper::toDomain).toList();
    }

    @Override
    public Optional<Supplier> findById(SupplierId supplierId) {
        Optional<SupplierEntity> supplier = jpaSupplierRepository.findById(supplierId.value());
        return supplier.map(supplierEntityMapper::toDomain);
    }

    @Override
    public void deleteById(SupplierId supplierId) {
        jpaSupplierRepository.deleteById(supplierId.value());
    }
}
