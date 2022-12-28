package com.example.L07springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping("/menu")
    // @ResponseBody // this is needed to send the response as text not the html page
    public String getMenu(){
        return "menu.html";
    } // search the html page in static resource and return that html page
}
