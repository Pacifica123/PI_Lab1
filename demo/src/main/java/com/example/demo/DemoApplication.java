package com.example.demo;

import com.example.demo.MyDB.ProductDB;
import com.example.demo.MyDB.UserDB;
import com.example.demo.handlers.ProductNotifer;
import com.example.demo.handlers.UserObserver;
import com.example.demo.handlers.UserSuber;
import com.example.demo.models.Order;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.services.OrderService;
import com.stripe.model.ProductCollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		// ПРОВЕРКА НАБЛЮДАТЕЛЯ
		//==============================
		UserSuber u1 = new UserSuber(UserDB.getUserById(1L));
		UserSuber u2 = new UserSuber(UserDB.getUserById(2L));
		UserSuber u3 = new UserSuber(UserDB.getUserById(3L));
		ProductNotifer notiferByProduct1 = new ProductNotifer(ProductDB.getProductById("productId1"));
		notiferByProduct1.addObserver(u1);
		notiferByProduct1.addObserver(u2);
		notiferByProduct1.addObserver(u3);
		ProductDB.updateProduct("productId1",
				new Product("productId1",
				"Product 1",
				9),
				notiferByProduct1
		);
		//==============================

		//==============================
		// ПРОВЕРКА ЦЕПОЧКА ОБЯЗАННОСТЕЙ

		// допустим формируется новый заказ:
		Order o1 = new Order(
				1L,
				"d6ftgy",
				"123456789",
				"cwje",
				"stripe",
				1000L,
				900L,
				"productId1",
				""
		);
		Order o2 = new Order(
				1L,
				"yellow@gmail.com",
				"",
				"cwje",
				"youMoney",
				1000L,
				900L,
				"productId1",
				""
		);
		Order o3 = new Order(
				1L,
				"yellow@gmail.com",
				"123456789",
				"yyywicueh",
				"stripe",
				1000L,
				900L,
				"productId1",
				"адресс с кодом 1"
		);
		// Проверяем каэжый заказ по цепочке проверок
		OrderService orderChecker = new OrderService();
		orderChecker.processOrder(o1);
		orderChecker.processOrder(o2);
		orderChecker.processOrder(o3);
		//==============================

		SpringApplication.run(DemoApplication.class, args);
	}

}
