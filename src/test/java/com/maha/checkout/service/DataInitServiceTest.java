package com.maha.checkout.service;

import com.maha.checkout.entity.ProductCatalogue;
import com.maha.checkout.entity.ProductDiscount;
import com.maha.checkout.repository.ProductCatalogueRepository;
import com.maha.checkout.repository.ProductDiscountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class DataInitServiceTest {

  @Autowired
  @InjectMocks
  private DataInitService dataInitService;

  @Mock
  private ProductCatalogueRepository productCatalogueRepository;

  @Mock
  private ProductDiscountRepository productDiscountRepository;

  @Test
  public void initTest() {
    dataInitService.init();
    Mockito.verify(productCatalogueRepository, Mockito.times(4)).save(Mockito.any(ProductCatalogue.class));
    Mockito.verify(productDiscountRepository, Mockito.times(2)).save(Mockito.any(ProductDiscount.class));
  }

}