package smb.inv.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

public class FetchInflowOutflowDetails {
	
	private DataSource dataSource;
	Connection conn = null;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) throws SQLException {
		this.dataSource = dataSource;
		conn = getDataSource().getConnection();
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConnection(){
		return conn;
	}
	
	public ArrayList<Product> inflowDetails(Date searchFrom,Date searchTo) throws SQLException{
			
			String query = "SELECT * FROM product NATURAL JOIN productInfo WHERE inflowDate>=\""+df.format(searchFrom)+"\" AND inflowDate<=\""+df.format(searchTo)+"\"";
				
			Statement s = getConnection().createStatement();
			ResultSet result = s.executeQuery(query);
			ArrayList<Product> productDet = new ArrayList<Product>();
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
			return productDet;
	}
	
	public ArrayList<Product> outflowDetails(Date searchFrom,Date searchTo) throws SQLException{
		
		String query = "SELECT * FROM product NATURAL JOIN productInfo WHERE outflowDate>=\""+df.format(searchFrom)+"\" AND outflowDate<=\""+df.format(searchTo)+"\"";
			
		Statement s = getConnection().createStatement();
		ResultSet result = s.executeQuery(query);
		ArrayList<Product> productDet = new ArrayList<Product>();
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
		return productDet;
	
	}
}
