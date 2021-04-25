package com.maha.checkout.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class ProductCatalogue {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Integer id;

  @Column(nullable = false, unique = true)
  private String productId;

  @Column(nullable = false)
  private String productType;

  @Column(length = 50, nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer unitPrice;

  @OneToMany(mappedBy = "product")
  private List<ProductDiscount> discount;

  public ProductCatalogue(){}

  public ProductCatalogue(String productId, String productType, String name, Integer unitPrice) {
    this.productId = productId;
    this.productType = productType;
    this.name = name;
    this.unitPrice = unitPrice;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Integer unitPrice) {
    this.unitPrice = unitPrice;
  }

  public List<ProductDiscount> getDiscount() {
    return discount;
  }

  public void setDiscount(List<ProductDiscount> discount) {
    this.discount = discount;
  }
}
