package com.example.planningproject;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    Service s = new Service();

    @PostMapping("/createTimeplan")
    public String createTimePlan(WebRequest req, HttpSession session){

        Map<Integer, ArrayList<String>> map = s.createTimeplan(req);

        int activityAmount = Integer.parseInt(req.getParameter("activitiesAmount"));


        ArrayList<String> activities = new ArrayList<>();

        for (int i = 1; i < activityAmount + 1; i++) {

            String reqName = "a" + i;

            activities.add(req.getParameter(reqName));
        }
        System.out.println(activities);

        session.setAttribute("activities", activities);
        session.setAttribute("map", map);
        session.setAttribute("startTime", req.getParameter("time").substring(0,5));

        return "redirect:/";
    }






    @GetMapping("/")
    public String index(Model model, HttpSession session){

        if (session.getAttribute("map") != null ) {
            //model.addAttribute(session.getAttribute("map"));
            //model.addAttribute(session.getAttribute("activities"));
        }


       return "page";
    }
}
