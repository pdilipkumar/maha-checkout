package com.maha.checkout.service.impl;

import com.maha.checkout.entity.ProductCatalogue;
import com.maha.checkout.entity.ProductDiscount;
import com.maha.checkout.repository.ProductCatalogueRepository;
import com.maha.checkout.repository.ProductDiscountRepository;
import com.maha.checkout.service.CheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImpl implements CheckoutService {

  private ProductCatalogueRepository productCatalogueRepository;

  private ProductDiscountRepository productDiscountRepository;

  @Override
  public Integer calculateCost(List<String> products) {
    Map<String, Long> productCount = getProductCount(products);
    List<ProductCatalogue> allProducts = productCatalogueRepository.findAllByProductIdIn(products);
    Integer totalCost = 0;
    for(ProductCatalogue product : allProducts) {
      totalCost = totalCost + calculateCostPerProduct(product, productCount.get(product.getProductId()));
    }
    return totalCost;
  }

  private Integer calculateCostPerProduct(ProductCatalogue product, Long totalQuantity) {
    int totalQuantityInt = totalQuantity.intValue();
    Optional<ProductDiscount> applicableDiscount =
        productDiscountRepository.findApplicableDiscount(product.getId(), totalQuantityInt);
    if(applicableDiscount.isPresent()) {
      return (totalQuantityInt / applicableDiscount.get().getQuantity()) * applicableDiscount.get().getAmount()
          + (totalQuantityInt % applicableDiscount.get().getQuantity()) * product.getUnitPrice();
    } else {
      return Math.multiplyExact(product.getUnitPrice(), totalQuantityInt);
    }
  }

  private Map<String, Long> getProductCount(List<String> products) {
    return products.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  @Autowired
  public void setProductCatalogueRepository(ProductCatalogueRepository productCatalogueRepository) {
    this.productCatalogueRepository = productCatalogueRepository;
  }

  @Autowired
  public void setProductDiscountRepository(ProductDiscountRepository productDiscountRepository) {
    this.productDiscountRepository = productDiscountRepository;
  }
}
