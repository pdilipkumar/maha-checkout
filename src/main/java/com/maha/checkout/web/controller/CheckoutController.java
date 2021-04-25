package com.maha.checkout.web.controller;

import com.maha.checkout.service.CheckoutService;
import com.maha.checkout.web.resource.CheckoutResponseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/checkout")
@Validated
public class CheckoutController {

  private CheckoutService checkoutService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CheckoutResponseResource> productCheckout(@RequestBody
      @NotEmpty(message = "Products must not be empty") List<String> requestBody) {
    return ResponseEntity.ok(new CheckoutResponseResource(
        checkoutService.calculateCost(requestBody)
    ));
  }

  @Autowired
  public void setCheckoutService(CheckoutService checkoutService) {
    this.checkoutService = checkoutService;
  }
}
