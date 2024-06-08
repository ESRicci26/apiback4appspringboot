package br.com.javaricci.Back4App.Model;

public class Client {

	private String id;
	private String name;
	private double price;
	private int stock;
	private boolean selling;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isSelling() {
		return selling;
	}

	public void setSelling(boolean selling) {
		this.selling = selling;
	}

	public Client() {
	}

	public Client(String id, String name, double price, int stock, boolean selling) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.selling = selling;
	}


}
