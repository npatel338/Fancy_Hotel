package com.db.cs4400.fancyhotel.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.cs4400.fancyhotel.dao.ReviewDao;

@Component
public class ReviewController {
	
	@Autowired
	private ReviewDao reviewDao;
	
	public List<String> getLocations() {
		return reviewDao.getLocations();
	}
	
	public void postReview(final String userName, final String comment, 
			final String rating, final String location) {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(comment) 
				|| StringUtils.isEmpty(rating) || StringUtils.isEmpty(rating)) {
			return;
		}
		
		reviewDao.insertReview(userName, comment, rating, location);
	}
}
