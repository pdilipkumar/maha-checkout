package com.maha.checkout.service;

import com.maha.checkout.entity.ProductCatalogue;
import com.maha.checkout.entity.ProductDiscount;
import com.maha.checkout.repository.ProductCatalogueRepository;
import com.maha.checkout.repository.ProductDiscountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInitService {

  private static final Logger logger = LoggerFactory.getLogger(DataInitService.class);

  private ProductCatalogueRepository productCatalogueRepository;

  private ProductDiscountRepository productDiscountRepository;

  public void init() {
    logger.info("Loading records to database");
    // Rolex watch
    ProductCatalogue watch1 = new ProductCatalogue("001", "watch", "Rolex", 100);
    productCatalogueRepository.save(watch1);
    ProductDiscount discount1 = new ProductDiscount(3, 200, watch1);
    productDiscountRepository.save(discount1);

    // Michael Kors watch
    ProductCatalogue watch2 = new ProductCatalogue("002", "watch", "Michael Kors", 80);
    productCatalogueRepository.save(watch2);
    ProductDiscount discount2 = new ProductDiscount(2, 120, watch2);
    productDiscountRepository.save(discount2);

    // Swatch watch
    ProductCatalogue watch3 = new ProductCatalogue("003", "watch", "Swatch", 50);
    productCatalogueRepository.save(watch3);

    // Casio watch
    ProductCatalogue watch4 = new ProductCatalogue("004", "watch", "Casio", 30);
    productCatalogueRepository.save(watch4);
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
