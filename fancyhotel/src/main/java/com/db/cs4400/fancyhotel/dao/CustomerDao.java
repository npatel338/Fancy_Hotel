package com.db.cs4400.fancyhotel.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.db.cs4400.fancyhotel.model.Customer;

@Component
public class CustomerDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Value("${customer.get.query}")
	private String customerGetQuery;
	@Value("${customer.save.query}")
	private String customerSaveQuery;
	@Value("${customer.exist.query}")
	private String customerExistQuery;
	
	public Customer getCustomer (final String userName, final String password) {
		Customer customer = null;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
				String.format(customerGetQuery, userName, password));
		if (CollectionUtils.isNotEmpty(rows)) {
			Map<String, Object> row = rows.iterator().next();
			if (MapUtils.isNotEmpty(row)) {
				customer = new Customer();
				customer.setUserName((String)row.get("Username"));
				customer.setPassword((String)row.get("Password"));
				customer.setEmailId((String)row.get("Email"));
			}
		}

		return customer;
	}
	
	public void saveCustomer(final String userName, final String password, final String email) {
		jdbcTemplate.execute(String.format(customerSaveQuery, userName, password, email));
	}
	
	public boolean isUserExist(final String userName, final String email) {
		boolean isUserExist = false;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
				String.format(customerExistQuery, userName, email));
		if (CollectionUtils.isNotEmpty(rows)) {
			isUserExist = true;
		}

		return isUserExist;
	}
}
