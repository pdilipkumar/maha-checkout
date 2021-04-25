package com.maha.checkout.service;

import java.util.List;

public interface CheckoutService {

  Integer calculateCost(List<String>products);

}
