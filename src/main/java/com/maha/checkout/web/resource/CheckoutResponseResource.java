package com.maha.checkout.web.resource;

public class CheckoutResponseResource {

  private Integer price;

  public CheckoutResponseResource(){}

  public CheckoutResponseResource(Integer price) {
    this.price = price;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}
