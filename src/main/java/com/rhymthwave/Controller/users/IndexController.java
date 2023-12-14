package com.rhymthwave.Controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin("*")
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "/user/index";
    }

    @GetMapping("/index")
    public String home(){
        return "/admin/index";
    }
}
