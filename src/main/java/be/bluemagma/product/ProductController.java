package be.bluemagma.product;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

import be.bluemagma.product.projection.ProductCodeOnly;
import be.bluemagma.web.projection.ProjectedBy;
import be.bluemagma.product.projection.ProductNameOnly;

/**
 * Web API end points for the {@link Product} domain entity
 */
@Controller
public class ProductController {

  private final ProductRepository productRepository;

  public ProductController(final ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping(value = "/product", produces = {
      ProductNameOnly.ACCEPT_HEADER,
      ProductCodeOnly.ACCEPT_HEADER
  })
  public ResponseEntity findAll(@ProjectedBy(def = ProductNameOnly.class) final Class<?> projectionClass) {
    final Collection<?> products = this.productRepository.findAllProjectedBy(projectionClass);
    return ResponseEntity.ok(products);
  }
}
