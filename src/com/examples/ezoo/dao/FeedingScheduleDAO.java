package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {
	
	int AddFeedingSchedule(FeedingSchedule schedule) throws Exception;
	int DeleteFeedingSchedule(Integer id);
	List<FeedingSchedule> GetFeedingSchedules();
	FeedingSchedule GetFeedingSchedule(long scheduleID);
	FeedingSchedule GetFeedingScheduleForAnAnimal(int animalID);
	int AssignFeedingSchedule(long feedingScheduleID, String animalName);
	void UpdateFeedingSchedule(FeedingSchedule schedule);
	

}
