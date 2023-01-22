package com.example.planningproject;

import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Service {


    public Map<Integer, ArrayList<String>> createTimePlan(WebRequest req){

        Map<Integer, ArrayList<String>> map = new HashMap<>();

        //Get data from webReguest
        int activitiesAmount = Integer.parseInt(req.getParameter("activitiesAmount"));

        String timespan = req.getParameter("time");
        String startTime = timespan.substring(0,8);
        String endTime = timespan.substring(9);


        float time = calcTime(startTime,endTime);
        //Time pr activity In hours
        float timePrActivity = time / activitiesAmount;


        List<String> activityTimes = createActivityTimes(timePrActivity,activitiesAmount,startTime);


        for (int i = 0; i < activitiesAmount; i++) {

            ArrayList<String> list = new ArrayList<>();

            int count = i;

            for (int j = 0; j < activitiesAmount; j++) {

                if (count>=activitiesAmount) count = 0;

                list.add(activityTimes.get(count));

                count++;

            }


            map.put(i,list);
        }

        return map;
    }

    private List<String> createActivityTimes(float timePrActivity, int activityAmount, String startTime) {

        List<String> list = new ArrayList<>();

        LocalDateTime start = LocalDateTime.parse("2000-10-04T"+startTime);


        for (int i = 0; i < activityAmount; i++) {

            LocalDateTime end = start.plusMinutes((long) timePrActivity);

            String startingTime = start.toString().substring(11);
            String endingTime = end.toString().substring(11);

            list.add(startingTime + "-" + endingTime);

            start = end;

        }

        return list;
    }
    private long calcTime(String startTime, String endTime){

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1;
        Date date2;
        long difference = 0;
        try {
            date1 = format.parse(startTime);
            date2 = format.parse(endTime);
            difference = date2.getTime() - date1.getTime();
        } catch (Exception e) {

        }

        //Difference is in milliseconds, is converted to minutes
        return TimeUnit.MILLISECONDS.toMinutes(difference);
    }
}
