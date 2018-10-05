package be.bluemagma.product.projection;

import be.bluemagma.product.Product;

/**
 * Projection for {@link Product} domain entity
 */
public interface ProductCodeOnly {

  String ACCEPT_HEADER = "application/vnd.product-code-only+json";

  String getCode();
}
