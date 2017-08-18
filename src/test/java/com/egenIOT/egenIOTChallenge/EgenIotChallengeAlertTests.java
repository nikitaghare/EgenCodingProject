package com.egenIOT.egenIOTChallenge;
import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.egenIOT.egenIOTChallenge.models.Alert;
import com.egenIOT.egenIOTChallenge.models.Metric;
import com.egenIOT.egenIOTChallenge.services.AlertServices;
import com.egenIOT.egenIOTChallenge.services.MetricServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EgenIotChallengeAlertTests {

		
		@Autowired
		private AlertServices alertService;
		
		@Autowired
		private MetricServices metricService;
		
		private Metric metric;
		private static int timeStamp = 1000;
		

		private void createMetric(double weight, long timeStamp) {
			metric = new Metric();
			metric.setWeight(weight);
			metric.setTimestamp(timeStamp);
			metricService.createMetric(metric);
			EgenIotChallengeAlertTests.timeStamp++;
		}
		
		@Test
		public void testCreateOverWeightAlert() {
			// Creating over-weight metric (base weight is 150)
			createMetric(170, timeStamp);
			List<Alert> dbAlerts = alertService.readByTimeRange(metric.getTimestamp(), metric.getTimestamp());
			System.out.println("dbalert size is" + dbAlerts.size());
			Alert dbAlert = dbAlerts.get(0);
			assertEquals(metric.getTimestamp(), dbAlert.getTimestamp(), 0);
			assertEquals(Alert.AlertType.OVER_WEIGHT, dbAlert.getType());
		}
		
		@Test
		public void testCreateUnderWeightAlert() {
			// Creating under-weight metric (base weight is 150)
			createMetric(14, timeStamp);
			List<Alert> dbAlerts = alertService.readByTimeRange(metric.getTimestamp(), metric.getTimestamp());
			Alert dbAlert = dbAlerts.get(0);
			assertEquals(metric.getTimestamp(), dbAlert.getTimestamp(), 0);
			assertEquals(Alert.AlertType.UNDER_WEIGHT, dbAlert.getType());
			
		}
		
		@Test
		public void testCreateNoAlert() {
			// Creating normal-weight metric (base weight is 150)
			createMetric(52, timeStamp);
			List<Alert> dbAlerts = alertService.readByTimeRange(metric.getTimestamp(), metric.getTimestamp());
			assertTrue(dbAlerts.isEmpty());
		}

		@Test
		public void testReadAllAlerts() {
			// Creating over-weight metric (base weight is 150)
			createMetric(170, timeStamp);
			// Creating under-weight metric (base weight is 150)
			createMetric(12, timeStamp);
			// Creating under-weight metric (base weight is 150)
			createMetric(10, timeStamp);
			List<Alert> dbAlerts = alertService.readAll();
			assertTrue(dbAlerts.size() == 3);
		}
		
		@Test
		public void testReadAlertsByTimeRange() {
			long startTimeStamp = timeStamp;
			createMetric(190, timeStamp);
			createMetric(4, timeStamp);
			createMetric(9, timeStamp);
			List<Alert> dbAlerts = alertService.readByTimeRange(startTimeStamp, timeStamp);
			assertTrue(dbAlerts.size() == 3);
		}
	}