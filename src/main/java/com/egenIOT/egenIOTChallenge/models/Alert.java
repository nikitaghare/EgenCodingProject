package com.egenIOT.egenIOTChallenge.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("Alert")
public class Alert {
	@Id
	private ObjectId id;
	private double weight;
	private long timestamp;
	public enum AlertType {UNDER_WEIGHT, OVER_WEIGHT};
	private AlertType type;
	
	public ObjectId getId() {
		return id;
	}
	public AlertType getType() {
		return this.type;
	}
	public void setType(AlertType type) {
		this.type = type;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
