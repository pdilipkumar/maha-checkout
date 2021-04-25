package com.maha.checkout.repository;

import com.maha.checkout.entity.ProductCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCatalogueRepository extends JpaRepository<ProductCatalogue, Integer> {

  List<ProductCatalogue> findAllByProductIdIn(List<String> productIds);

}
