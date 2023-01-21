package com.example.planningproject;

import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Service {


    public Map<Integer, ArrayList<String>> createTimeplan(WebRequest req){

        Map<Integer, ArrayList<String>> map = new HashMap<>();

        //Get data from webReguest
        int teamAmount = Integer.parseInt(req.getParameter("teamsAmount"));
        int activitiesAmount = Integer.parseInt(req.getParameter("activitiesAmount"));

        //Teams
        /*
        String[] teams = new String[teamAmount];
        for (int i = 0; i < teams.length; i++) {
            int j = i+1;
            teams[i] = req.getParameter("t" + j);
        }

         */
        /*
        //Activities
        String[] activities = new String[activitiesAmount];
        for (int i = 0; i < activities.length; i++) {
            int j = i +1;
            activities[i] = req.getParameter("a" + j);
        }

         */

        String timespan = req.getParameter("time");
        String startTime = timespan.substring(0,8);
        String endTime = timespan.substring(9);

        System.out.println(startTime + "\n" + endTime);

        //map = createGrid(teamAmount);

        float time = calcHours(startTime,endTime);
        System.out.println("time: " + time);
        //Time pr activity In hours
        float timePrActivity = time / activitiesAmount;

        System.out.println("time pr activity: " + timePrActivity);

        List<String> activityTimes = createActivityTimes(timePrActivity,activitiesAmount,startTime);

        System.out.println(activityTimes);


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

        for (int i = 0; i < map.size(); i++) {
            System.out.println(map.get(i));
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
    private long calcHours(String startTime, String endTime){

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

        System.out.println(difference);
        return difference / 60000;
    }
    private Map<Integer, ArrayList<String>> createGrid(int teams){

        Map<Integer, ArrayList<String>> map = new HashMap<>();

        for (int i = 0; i < teams; i++) {
            ArrayList<String> list = new ArrayList<>();
            map.put(i,list);
        }
        return map;
    }

    private void addTimespanToList(ArrayList<String> list){


    }
}
