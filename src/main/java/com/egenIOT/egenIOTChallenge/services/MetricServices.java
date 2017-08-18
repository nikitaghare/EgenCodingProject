package com.egenIOT.egenIOTChallenge.services;

import java.util.List;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.RulesEngineBuilder;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.egenIOT.egenIOTChallenge.models.Metric;
import com.egenIOT.egenIOTChallenge.rules.OverWeightRule;
import com.egenIOT.egenIOTChallenge.rules.UnderWeightRule;
@Service
public class MetricServices {
	
	private RulesEngine rulesEngine;
   
	Datastore datastore;
	
	public MetricServices() {
		rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
		datastore = MongoServices.getInstance().getDataStore();
	}
	
	public void createMetric(Metric metric) {
		Facts facts = new Facts();
		Rules rules = new Rules();
		facts.put("metric", metric);
		rules.register(new UnderWeightRule());
		rules.register(new OverWeightRule());
		rulesEngine.fire(rules, facts);
		datastore.save(metric);
	}
	public List<Metric> readAll() {
		Query<Metric> retrievedMetrics = datastore.createQuery(Metric.class);
		return retrievedMetrics.asList();
	}

	public List<Metric> readByTimeRange(long start, long end)  {
		Query<Metric> retrievedMetrics = datastore.createQuery(Metric.class).
				filter("timestamp >=", start).filter("timestamp <=", end);
		return retrievedMetrics.asList();
	}	
}
