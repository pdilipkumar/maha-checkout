package com.maha.checkout;

import com.maha.checkout.service.DataInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private DataInitService dataInitService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataInitService.init();
	}

	@Autowired
	public void setDataInitService(DataInitService dataInitService) {
		this.dataInitService = dataInitService;
	}
}
