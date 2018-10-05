package be.bluemagma.product;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Product domain entity
 */
@Entity
@Table(name = "t_product")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@ToString
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false)
  private BigDecimal price;

  public Product(final String name, final String code, final BigDecimal price) {
    this.name = name;
    this.code = code;
    this.price = price;
  }
}
