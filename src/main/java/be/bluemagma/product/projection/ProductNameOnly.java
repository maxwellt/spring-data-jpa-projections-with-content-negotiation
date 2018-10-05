package be.bluemagma.product.projection;

import be.bluemagma.product.Product;

/**
 * Projection for {@link Product} domain entity
 */
public interface ProductNameOnly {

  String ACCEPT_HEADER = "application/vnd.product-name-only+json";

  String getName();
}
