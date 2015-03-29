package smb.inv.service;

import java.util.ArrayList;

public class ProductDesc{
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ArrayList<String> getDesc() {
		return desc;
	}
	public void setDesc(ArrayList<String> desc) {
		this.desc = desc;
	}
	private Product product;
	private ArrayList<String> desc;
	
}
