package com.egenIOT.egenIOTChallenge.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.core.BasicRule;

import com.egenIOT.egenIOTChallenge.models.Alert;
import com.egenIOT.egenIOTChallenge.models.Alert.AlertType;
import com.egenIOT.egenIOTChallenge.services.AlertServices;
import com.egenIOT.egenIOTChallenge.models.Metric;

public class UnderWeightRule extends BasicRule{
	
	private static final int baseWeight = 150;
	AlertServices alertservices = new AlertServices();
	
	public UnderWeightRule() {
		 super("UnderWeightRule", "Check if person's weight is < 10% of his base weight then fire under weight alert", 1);
	}
	
	@Override
	public boolean evaluate(Facts facts)  {
		 Metric metric = (Metric) facts.get("metric");
	        return metric.getWeight() < (0.1*baseWeight);
	}
	
	@Override
	public void execute(Facts facts)  {
		Metric metric = (Metric) facts.get("metric");
		Alert alert = new Alert();
		alert.setType(AlertType.UNDER_WEIGHT);
		alert.setWeight(metric.getWeight());
		alert.setTimestamp(metric.getTimestamp());
		alertservices.createAlert(alert);
	}
	
}
