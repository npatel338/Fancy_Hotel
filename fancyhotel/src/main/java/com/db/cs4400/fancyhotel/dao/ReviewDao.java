package com.db.cs4400.fancyhotel.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReviewDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Value("${review.location.query}")
	private String reviewGetQuery;
	@Value("${review.provide.query}")
	private String reviewPostQuery;
	
	public List<String> getLocations() {
		List<String> locations = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
				String.format(reviewGetQuery));
		if (CollectionUtils.isNotEmpty(rows)) {
			for (Map<String, Object> row : rows) {
				locations.add((String)row.get("Loc"));
			}
		}

		return locations;
	}
	
	public void insertReview(final String userName, final String comment, final String rating, final String location) {
		jdbcTemplate.execute(String.format(reviewPostQuery, userName, comment, rating, location));
	}
}
