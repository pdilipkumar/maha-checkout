package com.maha.checkout.repository;

import com.maha.checkout.entity.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Integer> {

  @Query(nativeQuery = true, name = "findApplicableDiscount")
  Optional<ProductDiscount> findApplicableDiscount(@Param("productId") Integer productId,
      @Param("totalQuantity") Integer totalQuantity);
}
