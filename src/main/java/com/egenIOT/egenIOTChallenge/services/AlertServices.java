package com.egenIOT.egenIOTChallenge.services;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.egenIOT.egenIOTChallenge.models.Alert;

@Service
public class AlertServices {
	
	Datastore datastore;
	public AlertServices() {
		datastore = MongoServices.getInstance().getDataStore();
	}
	
	public void createAlert(Alert alert) {
		 datastore.save(alert);
	}
	public List<Alert> readAll() {
		Query<Alert> retrievedAlerts = datastore.createQuery(Alert.class);
		return retrievedAlerts.asList();
	}

	public List<Alert> readByTimeRange(long start, long end)  {
		
		Query<Alert> retrievedAlerts = datastore.createQuery(Alert.class).
				filter("timestamp >=", start).filter("timestamp <=", end);
		return retrievedAlerts.asList();
	}	
}
