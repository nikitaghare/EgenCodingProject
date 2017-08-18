package com.egenIOT.egenIOTChallenge.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.core.BasicRule;

import com.egenIOT.egenIOTChallenge.models.Alert;
import com.egenIOT.egenIOTChallenge.models.Alert.AlertType;
import com.egenIOT.egenIOTChallenge.services.AlertServices;
import com.egenIOT.egenIOTChallenge.models.Metric;

public class OverWeightRule extends BasicRule{

	AlertServices alertservices = new AlertServices();
	private static final int baseWeight = 150;
	public OverWeightRule() {
		 super("overWeightRule", "Check if person's weight is > 10% over his base weight and fires over weight alert", 1);
	}
	
	@Override
	public boolean evaluate(Facts facts)  {
		 Metric metric = (Metric) facts.get("metric");
		 double threshold = baseWeight + (0.1*baseWeight);
	        return metric.getWeight() > threshold;
	}
	
	@Override
	public void execute(Facts facts)  {
		Metric metric = (Metric) facts.get("metric");
		Alert alert = new Alert();
		alert.setType(AlertType.OVER_WEIGHT);
		alert.setWeight(metric.getWeight());
		alert.setTimestamp(metric.getTimestamp());
		alertservices.createAlert(alert);
	}
}
