package com.db.cs4400.fancyhotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.cs4400.fancyhotel.dao.PaymentInfoDao;
import com.db.cs4400.fancyhotel.dao.ReservationDao;
import com.db.cs4400.fancyhotel.model.PaymentInfo;

@Component
public class PaymentInfoController {
	
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private PaymentInfoDao paymentInfoDao;
	
	public List<String> getLocations() {
		return reservationDao.getLocations();
	}
	
	public List<Integer> getCardNumbers(final String userName) {
		if (StringUtils.isEmpty(userName)) {
			return null;
		}

		List<Integer> cardNumbers = new ArrayList<>();
		List<PaymentInfo> paymentInfos = paymentInfoDao
				.getCardNumbers(userName);
		if (CollectionUtils.isNotEmpty(paymentInfos)) {
			for (PaymentInfo paymentInfo : paymentInfos) {
				cardNumbers.add(paymentInfo.getCardNumber());
			}
		}

		return cardNumbers;
	}
}
