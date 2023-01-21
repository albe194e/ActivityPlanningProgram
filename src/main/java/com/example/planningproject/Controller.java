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

        session.setAttribute("map", map);

        return "redirect:/";
    }






    @GetMapping("/")
    public String index(Model model, HttpSession session){

        if (session.getAttribute("map") != null ) {
            model.addAttribute(session.getAttribute("map"));
        }


       return "page";
    }
}
