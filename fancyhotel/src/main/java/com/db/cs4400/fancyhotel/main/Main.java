package com.db.cs4400.fancyhotel.main;

import java.awt.EventQueue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.db.cs4400.fancyhotel.view.FancyHotelFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ApplicationContext context = null;
				try {
					context = new ClassPathXmlApplicationContext("applicationContext.xml");
					FancyHotelFrame fancyHotelFrame = (FancyHotelFrame) context.getBean("fancyHotelFrame");
					fancyHotelFrame.loadUI();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					((ConfigurableApplicationContext)context).close();
				}
			}
		});

	}
}
