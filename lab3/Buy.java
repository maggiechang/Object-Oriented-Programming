public class Buy {
	private String sessionId;
	private String productId;
	private int price;
	private int quantity;
	public Buy(String sessionId, String productId, int price, int quantity) {
		this.sessionId = sessionId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		else if (getClass() != obj.getClass()) {
			return false;
		}
		return (sessionId == ((Buy)obj).sessionId &&
			productId == ((Buy)obj).productId &&
			price == ((Buy)obj).price &&
			quantity == ((Buy)obj).quantity);
	}
	public String getSessionId() {
		return sessionId;
	}
	public String getProduct() {
		return productId;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}

}