public class View {
	private String sessionId;
	private String productId;
	private int price;
	public View(String sessionId, String productId, int price) {
		this.sessionId = sessionId;
		this.productId = productId;
		this.price = price;
	}
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		else if (getClass() != obj.getClass()) {
			return false;
		}
		return (sessionId == ((View)obj).sessionId &&
			productId == ((View)obj).productId &&
			price == ((View)obj).price);
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
}