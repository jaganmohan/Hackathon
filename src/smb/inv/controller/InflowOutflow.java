package smb.inv.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import smb.inv.service.FetchInflowOutflowDetails;
import smb.inv.service.Product;

@Controller
public class InflowOutflow {
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private FetchInflowOutflowDetails fetchInflowOutflow;

	public FetchInflowOutflowDetails getFetchInflowOutflow() {
		return fetchInflowOutflow;
	}

	public void setFetchInflowOutflow(FetchInflowOutflowDetails fetchInflowOutflow) {
		this.fetchInflowOutflow = fetchInflowOutflow;
	}

	@RequestMapping(value="/inflow", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Product> inflow(@RequestParam Map<String,String> params, Model model){
		Date searchFrom = null, searchTo = null;
		
		try {
			searchFrom = df.parse(params.get("inflowFromDate"));
			searchTo = df.parse(params.get("inflowToDate"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			products = fetchInflowOutflow.inflowDetails(searchFrom, searchTo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	@RequestMapping(value="/outflow", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<Product> outflow(@RequestParam Map<String,String> params, Model model){
		Date searchFrom = null, searchTo = null;
		
		try {
			searchFrom = df.parse(params.get("outflowFromDate"));
			searchTo = df.parse(params.get("outflowToDate"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			products = fetchInflowOutflow.outflowDetails(searchFrom, searchTo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
}
