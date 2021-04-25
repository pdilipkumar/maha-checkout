package com.maha.checkout.service.impl;

import com.maha.checkout.entity.ProductCatalogue;
import com.maha.checkout.entity.ProductDiscount;
import com.maha.checkout.repository.ProductCatalogueRepository;
import com.maha.checkout.repository.ProductDiscountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

  @Autowired
  @InjectMocks
  private CheckoutServiceImpl checkoutService;

  @Mock
  private ProductCatalogueRepository productCatalogueRepository;

  @Mock
  private ProductDiscountRepository productDiscountRepository;

  ProductCatalogue watch1;
  ProductDiscount discount1;
  ProductCatalogue watch2;
  ProductDiscount discount2;
  ProductCatalogue watch3;
  ProductCatalogue watch4;

  @BeforeEach
  public void setUp() {
    watch1 = new ProductCatalogue(1, "001", "watch", "Rolex", 100);
    discount1 = new ProductDiscount(3, 200, watch1);
    watch2 = new ProductCatalogue(2, "002", "watch", "Michael Kors", 80);
    discount2 = new ProductDiscount(2, 120, watch2);
    watch3 = new ProductCatalogue(3, "003", "watch", "Swatch", 50);
    watch4 = new ProductCatalogue(4, "004", "watch", "Casio", 30);
  }

  @AfterEach
  public void tearDown() {
    watch1 = watch2 = watch3 = watch4 = null;
    discount1 = discount2 = null;
  }

  @Test
  void calculateCost_productId_001_quantity_1() {
    List<String> products = Arrays.asList("001");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch1));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(100), totalCost);
  }

  @Test
  public void calculateCost_productId_001_quantity_2() {
    List<String> products = Arrays.asList("001", "001");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch1));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(200), totalCost);
  }

  @Test
  public void calculateCost_productId_001_quantity_3() {
    List<String> products = Arrays.asList("001", "001", "001");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch1));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount1));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(200), totalCost);
  }

  @Test
  public void calculateCost_productId_001_quantity_4() {
    List<String> products = Arrays.asList("001", "001", "001", "001");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch1));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount1));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(300), totalCost);
  }

  @Test
  public void calculateCost_productId_001_quantity_6() {
    List<String> products = Arrays.asList("001", "001", "001", "001", "001", "001");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch1));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount1));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(400), totalCost);
  }

  @Test
  public void calculateCost_productId_001_quantity_8() {
    List<String> products = Arrays.asList("001", "001", "001", "001", "001", "001", "001", "001");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch1));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount1));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(600), totalCost);
  }

  @Test
  public void calculateCost_productId_002_quantity_1() {
    List<String> products = Arrays.asList("002");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch2));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(80), totalCost);
  }

  @Test
  public void calculateCost_productId_002_quantity_2() {
    List<String> products = Arrays.asList("002", "002");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch2));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount2));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(120), totalCost);
  }

  @Test
  public void calculateCost_productId_002_quantity_4() {
    List<String> products = Arrays.asList("002", "002", "002", "002");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch2));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount2));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(240), totalCost);
  }

  @Test
  public void calculateCost_productId_002_quantity5() {
    List<String> products = Arrays.asList("002", "002", "002", "002", "002");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products)).thenReturn(Arrays.asList(watch2));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.anyInt(),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount2));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(320), totalCost);
  }

  @Test
  public void calculateCost_multiProduct() {
    List<String> products = Arrays.asList("001", "002", "001", "004", "003");
    Mockito.when(productCatalogueRepository.findAllByProductIdIn(products))
        .thenReturn(Arrays.asList(watch1, watch2, watch3, watch4));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.eq(1),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount1));
    Mockito.when(productDiscountRepository.findApplicableDiscount(ArgumentMatchers.eq(2),
        ArgumentMatchers.anyInt())).thenReturn(Optional.of(discount2));
    Integer totalCost = checkoutService.calculateCost(products);
    Assertions.assertEquals(Integer.valueOf(360), totalCost);
  }
}