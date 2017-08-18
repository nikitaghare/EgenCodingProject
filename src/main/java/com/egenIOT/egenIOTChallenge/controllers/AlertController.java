package com.egenIOT.egenIOTChallenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egenIOT.egenIOTChallenge.models.Alert;
import com.egenIOT.egenIOTChallenge.services.AlertServices;

@RestController
@RequestMapping("/alerts")
public class AlertController {
	@Autowired
	private AlertServices alertServices;
	
	@PostMapping("/create") 
	public void create(@RequestBody Alert alert) {
		alertServices.createAlert(alert);
	}
	@GetMapping("/read")
	public List<Alert>read() {
		return alertServices.readAll();
	}
	@GetMapping("/readByTimeRange/{start}/{end}")
	public List<Alert> readByTimeRange(@PathVariable Long start, @PathVariable Long end) {
		return alertServices.readByTimeRange(start, end);
	}
}
