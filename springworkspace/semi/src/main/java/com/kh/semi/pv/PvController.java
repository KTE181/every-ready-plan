package com.kh.semi.pv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PvController {

    @GetMapping("pv")
    public String Pv(){
        return "pv/pv";
    }
}
