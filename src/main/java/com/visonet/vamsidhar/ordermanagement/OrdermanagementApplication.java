package com.visonet.vamsidhar.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * You are asked to build a Restful API in Java and Spring Boot that will receive a
 * list of Orders and their corresponding Order-Lines from a client.
 * The API should be implemented as a POST endpoint that accepts a JSON payload.
 *
 * The API should perform the following steps upon receiving a request:
 * 1) JSON Payload Request and Response structure not predefined. Your code should handle like in future
 * any property can be added or removed.
 *
 * 2) Valid the request, order and order-line should not be empty
 *
 * 3) Sort the orders based on the first two characters of the order code before inserting in the database.
 *
 * 4) Implement a functional interface that logs the order details using streams, list should be iterated for
 * this purpose using streams and using the functional interface details should be logged for each order.
 *
 * 5) Persist the orders in the order table and their corresponding order lines in the order_line table.
 *
 * 6) If any error occurs during the persistence process, throw a custom exception named InvalidOrderException.
 * If an InvalidOrderException is thrown, roll back the transaction to ensure data consistency.
 *
 */
@SpringBootApplication
public class OrdermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdermanagementApplication.class, args);
	}

}
