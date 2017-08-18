package com.egenIOT.egenIOTChallenge.services;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MongoServices {
	private static MongoServices mongoservices = new MongoServices();
	Datastore datastore = null;
	private MongoServices() {
		Morphia morphia = new Morphia();
		datastore = morphia.createDatastore(new MongoClient("127.0.0.1",27017), "egen-sensors"); 
		datastore.ensureIndexes();
		morphia.mapPackage("com.egenIOT.egenIOTChallenge");
		
	}
	public static MongoServices getInstance() {
		
	    return mongoservices;
	}
	public Datastore getDataStore() {
		
		return datastore;
	}
}
