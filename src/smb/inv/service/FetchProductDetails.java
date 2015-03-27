package smb.inv.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class FetchProductDetails {
	
	private DataSource dataSource;
	Connection conn = null;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<Product> fetchProductDetails(String product, ProductBy productBY) throws SQLException{
		
		ArrayList<Product> productDet = new ArrayList<Product>();;
		Statement stmt;
		conn = dataSource.getConnection();
		String query = "Select * from ";
		if(productBY == ProductBy.ID)
			query = query+"product NATURAL JOIN productInfo where productID="+Long.parseLong(product);
		else if(productBY == ProductBy.MODEL)
			query = query+"productInfo NATURAL JOIN product where modelNo=\""+product+"\"";
		else
			query = query+"productInfo NATURAL JOIN product where productName=\""+product+".*\"";
		stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		Product p;
		while(result.next()){
			p = new Product();
			p.setModelNo(result.getString("modelNo"));
			p.setProductId(result.getLong("productID"));
			p.setInflowDate(result.getDate("inflowDate"));
			p.setOutflowDate(result.getDate("outflowDate"));
			p.setDescFileLoc(result.getString("descFileLoc"));
			p.setProductName(result.getString("productName"));
			productDet.add(p);
		}
		conn.close();
		return productDet;
		
	}

}
