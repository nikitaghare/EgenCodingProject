package com.egenIOT.egenIOTChallenge;
import static org.junit.Assert.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.egenIOT.egenIOTChallenge.models.Metric;
import com.egenIOT.egenIOTChallenge.services.AlertServices;
import com.egenIOT.egenIOTChallenge.services.MetricServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EgenIotChallengeMetricTests {

	private MetricServices metricService;
	private Metric metric;
	private static int timeStamp = 2000;
	
	@Before
	public void setUp() {
		metricService = new MetricServices();
		new AlertServices();
	}
	
	@Test
	public void testCreateMetric() {
		createMetric(80, timeStamp);
		List<Metric> dbMetrics = metricService.readByTimeRange(metric.getTimestamp(), metric.getTimestamp());
		assertTrue(dbMetrics.size() == 1);
		Metric dbMetric = dbMetrics.get(0);
		assertEquals(metric.getWeight(), dbMetric.getWeight(), 0);
		assertEquals(metric.getTimestamp(), dbMetric.getTimestamp(), 0);
	}
	private void createMetric(double weight, long timeStamp) {
		metric = new Metric();
		metric.setWeight(weight);
		metric.setTimestamp(timeStamp);
		metricService.createMetric(metric);
		EgenIotChallengeMetricTests.timeStamp++;
	}

	@Test
	public void testReadAllMetrics() {
		createMetric(75, timeStamp);
		createMetric(71, timeStamp);
		createMetric(60, timeStamp);
		List<Metric> dbMetrics = metricService.readAll();
		assertTrue(dbMetrics.size() > 0);
	}
	@Test
	public void testReadMetricsByTimeRange() {
		long currentTime = timeStamp;
		createMetric(45, timeStamp);
		createMetric(29, timeStamp);
		createMetric(30, timeStamp);
		List<Metric> dbMetrics = metricService.readByTimeRange(currentTime, timeStamp);
		System.out.println("size is: " +dbMetrics.size());
		assertTrue(dbMetrics.size() == 3);
	}
}
