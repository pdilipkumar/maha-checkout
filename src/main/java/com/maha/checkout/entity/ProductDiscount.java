package com.maha.checkout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedNativeQueries(
    @NamedNativeQuery(name = "findApplicableDiscount", resultClass = ProductDiscount.class,
    query = "SELECT discount.* FROM product_discount discount WHERE discount.product_id = :productId " +
        "ORDER BY ABS(discount.quantity - :totalQuantity) LIMIT 1")
)
public class ProductDiscount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Integer id;

  @Column(nullable = false)
  private Integer quantity;

  @Column(nullable = false)
  private Integer amount;

  @ManyToOne
  @JoinColumn(name = "productId", referencedColumnName = "id")
  private ProductCatalogue product;

  public ProductDiscount(){}

  public ProductDiscount(Integer quantity, Integer amount) {
    this.quantity = quantity;
    this.amount = amount;
  }

  public ProductDiscount(Integer quantity, Integer amount, ProductCatalogue product) {
    this.quantity = quantity;
    this.amount = amount;
    this.product = product;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public ProductCatalogue getProduct() {
    return product;
  }

  public void setProduct(ProductCatalogue product) {
    this.product = product;
  }
}
