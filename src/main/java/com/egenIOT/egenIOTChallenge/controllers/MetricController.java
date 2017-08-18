package com.egenIOT.egenIOTChallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egenIOT.egenIOTChallenge.models.Metric;
import com.egenIOT.egenIOTChallenge.services.MetricServices;

@RestController
@RequestMapping("/metrics")
public class MetricController {
	
	@Autowired
	private MetricServices metricServices;
	
	@PostMapping("/create") 
	public void create(@RequestBody Metric metric) {
		metricServices.createMetric(metric);
	}
	@GetMapping("/read")
	public List<Metric>read() {
		return metricServices.readAll();
	}
	@GetMapping("/readByTimeRange/{start}/{end}")
	public List<Metric> readByTimeRange(@PathVariable Long start, @PathVariable Long end) {
		if(start == null || end == null) {
		}
		return metricServices.readByTimeRange(start, end);
	}
}
