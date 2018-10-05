package be.bluemagma;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import be.bluemagma.product.Product;
import be.bluemagma.product.ProductRepository;

@SpringBootApplication
public class ProjectionsDemoApplication implements CommandLineRunner {

  private final ProductRepository productRepository;

  public ProjectionsDemoApplication(final ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(ProjectionsDemoApplication.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {
    final List<Product> products = Stream.of(
        new Product("Hammer", "HAM", BigDecimal.valueOf(15.99)),
        new Product("Nail", "NAI", BigDecimal.valueOf(0.09)),
        new Product("Screwdriver", "SCR", BigDecimal.valueOf(10.99))
    ).collect(Collectors.toList());

    this.productRepository.saveAll(products);
  }
}
