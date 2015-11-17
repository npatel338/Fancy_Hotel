package com.db.cs4400.fancyhotel.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.db.cs4400.fancyhotel.model.PaymentInfo;

@Component
public class PaymentInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Value("${payment.cardnumber.query}")
	private String paymentGetCardNumberQuery;
	@Value("${payment.card.add.query}")
	private String paymentAddCardQuery;
	@Value("${payment.card.exist.query}")
	private String paymentCardExistQuery;

	public List<PaymentInfo> getCardNumbers(final String userName) {
		List<PaymentInfo> paymentInfoList = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(String
				.format(paymentGetCardNumberQuery, userName));
		if (CollectionUtils.isNotEmpty(rows)) {
			for (Map<String, Object> row : rows) {
				if (MapUtils.isNotEmpty(row)) {
					PaymentInfo paymentInfo = new PaymentInfo();
					paymentInfo.setCardNumber((Integer) row.get("Card_num"));
					paymentInfoList.add(paymentInfo);
				}
			}
		}

		return paymentInfoList;
	}

	public void saveCard(final PaymentInfo paymentInfo, final String userName) {
		if (StringUtils.isNotEmpty(paymentInfo.getName())
				&& paymentInfo.getExpDate() != null
				&& StringUtils.isNotEmpty(userName)
				&& paymentInfo.getCardNumber() != null
				&& paymentInfo.getCvv() != null) {
			jdbcTemplate.execute(String.format(paymentAddCardQuery,
					paymentInfo.getCardNumber(), paymentInfo.getName(),
					paymentInfo.getCvv(), paymentInfo.getExpDate(), userName));
		}
	}

	public PaymentInfo getPaymentInfo(final Integer cardNumber) {
		PaymentInfo paymentInfo = new PaymentInfo();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(String
				.format(paymentCardExistQuery, cardNumber));
		if (CollectionUtils.isNotEmpty(rows)) {
			Map<String, Object> row = rows.iterator().next();
			if (MapUtils.isNotEmpty(row)) {
				paymentInfo.setCardNumber((int) row.get("Card_num"));
			}
		}
		
		return paymentInfo;
	}
}
