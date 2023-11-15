package com.example.demo;

import com.example.demo.MyDB.ProductDB;
import com.example.demo.handlers.ProductNotifer;
import com.example.demo.handlers.UserObserver;
import com.example.demo.handlers.UserSuber;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.stripe.model.ProductCollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		UserSuber u1 = new UserSuber(new User(
				1L,
				"Джон",
				"red@gmail.com",
				"f7dg829or3eivyuf",
				User.Role.USER
		));
		UserSuber u2 = new UserSuber(new User(
				2L,
				"Алексей",
				"yellow@gmail.com",
				"f29or3eivyu7dg8f",
				User.Role.USER
		));
		UserSuber u3 = new UserSuber(new User(
				3L,
				"Александр",
				"green@gmail.com",
				"3eivyuf7dg829orf",
				User.Role.USER
		));
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

		SpringApplication.run(DemoApplication.class, args);
	}

}
