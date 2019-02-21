package com.pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

import com.pos.model.Category;
import com.pos.model.Category.Leavy;
import com.pos.model.Product;

/**
 * @author abhishek pandey
 *
 */
public class POS {
	private List<Product> products;

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		POS pos = new POS();
		System.out.println("Welcome to POS system!");

		Scanner commandLineInput = new Scanner(System.in);
		List<Product> cartList = pos.generateSalesReceipt(commandLineInput);
		pos.printBill(cartList);
	}

	POS() {
		products = new ArrayList<Product>();
		products.add(loadProduct(1, "Pen", "A", 100));
		products.add(loadProduct(2, "Pencil", "B", 200));
		products.add(loadProduct(3, "Eraser", "C", 300));
		products.add(loadProduct(4, "Notepad", "A", 400));
	}

	private Product loadProduct(int productId, String productName, String productCategory, double cost) {
		Product product = new Product();
		Category category = new Category();
		product.setId(productId);
		product.setCost(cost);
		product.setName(productName);
		category.setId(productId);
		category.setLeavy(Leavy.valueOf(productCategory).getLeavy());
		product.setCategory(category);

		return product;
	}

	protected List<Product> generateSalesReceipt(Scanner commandLineInput) {
		System.out.println("Please select product id or name");
		displayProducts();
		
		List<Product> productCart = new ArrayList<Product>();
		String productIdOrName = null;

		while (true) {
			productIdOrName = commandLineInput.next();
			productCart.add(findProduct(productIdOrName));
			System.out.println("Would you like at add another item : Y/y");

			if (commandLineInput.next().equalsIgnoreCase("y")) {
				displayProducts();
			} else {
				break;
			}
		}
		productCart.removeIf(Objects::isNull);
		return productCart;
	}

	private void printBill(List<Product> productCart) {
		System.out.println("No\tProduct\tCost\tLeavy\tTotalCost");
		int count = 0;
		double totalBill = 0, totalProductCost = 0;
		for (Product cartProduct : productCart) {
			totalProductCost = cartProduct.getCost()
					+ ((cartProduct.getCost() * cartProduct.getCategory().getLeavy()) / 100);
			totalBill += totalProductCost;
			System.out.println(String.format("%d\t%s\t%.2f\t%d\t%.2f", ++count, cartProduct.getName(),
					cartProduct.getCost(), cartProduct.getCategory().getLeavy(), totalProductCost));
		}
		System.out.println("------------------------------------------------");
		System.out.println(String.format("%d\t\t\t\t%.2f", count, totalBill));

	}

	private Product findProduct(String productIdOrName) {
		Product foundProduct = null;
		Optional<Product> productOptional = products.stream()
				.filter(((Predicate<Product>) product -> productIdOrName.equals(Integer.toString(product.getId())))
						.or(product -> productIdOrName.equals(product.getName())))
				.findFirst();
		if (!productOptional.isPresent()) {
			System.out.println("Invalid selection please select product from product list");
		} else {
			foundProduct = productOptional.get();
		}
		return foundProduct;
	}

	private void displayProducts() {
		System.out.println("1 Pen");
		System.out.println("2 Pencil");
		System.out.println("3 Eraser");
		System.out.println("4 Notepad");
	}
}
