package com.maha.checkout.service.impl;

import com.maha.checkout.entity.ProductCatalogue;
import com.maha.checkout.entity.ProductDiscount;
import com.maha.checkout.repository.ProductCatalogueRepository;
import com.maha.checkout.repository.ProductDiscountRepository;
import com.maha.checkout.service.CheckoutService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImpl implements CheckoutService {

  private static final Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);

  private ProductCatalogueRepository productCatalogueRepository;

  private ProductDiscountRepository productDiscountRepository;

  @Override
  public Integer calculateCost(List<String> products) {
    Map<String, Long> productCount = getProductCount(products);
    List<ProductCatalogue> allProducts = productCatalogueRepository.findAllByProductIdIn(products);
    Integer allProductsCost = 0;
    for(ProductCatalogue product : allProducts) {
      allProductsCost = allProductsCost + calculateCostPerProduct(product, productCount.get(product.getProductId()));
    }
    logger.info("Total cost of all products {}", allProductsCost);
    return allProductsCost;
  }

  private Integer calculateCostPerProduct(ProductCatalogue product, Long totalQuantity) {
    int totalQuantityInt = totalQuantity.intValue();
    Integer totalAmountPerProduct = 0;
    Optional<ProductDiscount> applicableDiscount =
        productDiscountRepository.findApplicableDiscount(product.getId(), totalQuantityInt);
    if(applicableDiscount.isPresent()) {
      logger.info("Applicable discount for productId: {} with quantity {} is {}", product.getProductId(),
          applicableDiscount.get().getQuantity(), applicableDiscount.get().getAmount());
      totalAmountPerProduct = (totalQuantityInt / applicableDiscount.get().getQuantity()) * applicableDiscount.get().getAmount()
          + (totalQuantityInt % applicableDiscount.get().getQuantity()) * product.getUnitPrice();
    } else {
      totalAmountPerProduct = Math.multiplyExact(product.getUnitPrice(), totalQuantityInt);
    }
    logger.info("Total cost for productId: {} with quantity {} is {}", product.getProductId(), totalQuantityInt, totalAmountPerProduct);
    return totalAmountPerProduct;
  }

  private Map<String, Long> getProductCount(List<String> products) {
    logger.info("Calculating each product count in the list");
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
