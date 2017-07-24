package com.examples.ezoo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FeedingSchedule {
	
	@Id	
	private long schedule_ID = 0L;
	private String time = ""; 
	private int recurrence = 0;
	private String food = "";
	private String notes = "";
	
	public FeedingSchedule()
	{
		
	}

	public FeedingSchedule(long schedule_ID, String time, int recurrence, String food, String notes) {
		
		super();		
		this.setSchedule_ID(schedule_ID);
		this.time = time;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;	
		
	}
	
	public FeedingSchedule(String time, int recurrence, String food, String notes) {
		
		super();	
		
		this.time = time;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;	
		
	}
	
	
	
	public int getRecurrence()
	{
		return recurrence;
	}
	public void setRecurrence(int recurrence)
	{
		this.recurrence = recurrence;
	}
	public String getFood()
	{
		return food;
	}
	public void setFood(String food)
	{
		this.food = food;
	}
	public String getNotes()
	{
		return notes;
	}
	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public long getSchedule_ID() {
		return schedule_ID;
	}

	public void setSchedule_ID(long schedule_ID) {
		this.schedule_ID = schedule_ID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
