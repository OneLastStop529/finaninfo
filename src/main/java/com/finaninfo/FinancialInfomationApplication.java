package com.finaninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class FinancialInfomationApplication extends SpringBootServletInitializer{
	@RequestMapping("/stock")
	public String getStockPage(){

		return "stock";
	}
	public static void main(String[] args) {
		SpringApplication.run(FinancialInfomationApplication.class, args);
	}
}
