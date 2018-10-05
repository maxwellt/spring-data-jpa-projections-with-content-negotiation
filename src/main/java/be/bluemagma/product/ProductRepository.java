package be.bluemagma.product;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Repository methods for the {@link Product} domain entity
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
  <T> Collection<T> findAllProjectedBy(Class<T> projectionClass);
}
