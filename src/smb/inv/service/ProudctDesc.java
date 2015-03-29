package smb.inv.service;

import java.util.stream.Stream;

public class ProductDesc{
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Stream<String> getDesc() {
		return desc;
	}
	public void setDesc(Stream<String> desc) {
		this.desc = desc;
	}
	private Product product;
	private Stream<String> desc;
	
}
