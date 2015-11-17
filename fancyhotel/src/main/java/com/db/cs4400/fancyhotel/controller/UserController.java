package com.db.cs4400.fancyhotel.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.cs4400.fancyhotel.dao.CustomerDao;
import com.db.cs4400.fancyhotel.exception.FancyHotelException;
import com.db.cs4400.fancyhotel.exception.FancyHotelUserExistException;
import com.db.cs4400.fancyhotel.model.Customer;

@Component
public class UserController {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Customer getCustomer(final String userName, final String password) throws FancyHotelException {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			throw new FancyHotelException("Username or password is empty");
		}
		
		return customerDao.getCustomer(userName, password);
	}
	
	public void saveCustomer(final String userName, final String password, final String email) throws FancyHotelException {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)) {
			throw new FancyHotelException("Username, password or email is empty");
		}
		
		customerDao.saveCustomer(userName, password, email);
	}
	
	public boolean isUserExist(final String userName, final String email) throws FancyHotelUserExistException {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(email)) {
			return false;
		}
		
		boolean isUserExist = false;
		isUserExist = customerDao.isUserExist(userName, email);
		if (isUserExist) {
			throw new FancyHotelUserExistException();
		}
		
		return isUserExist;
	}
}
