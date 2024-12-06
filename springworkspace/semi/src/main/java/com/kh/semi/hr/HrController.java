package com.kh.semi.hr;

import com.kh.semi.hr.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HrController {
    private  final HrService service;

    @GetMapping("hr")
    public String phr() {
        return "hr/hr";
    }


    @PostMapping("hr")
    @ResponseBody
    public List<EmployeeVo> hr(){

        List<EmployeeVo> volist = service.getList();
        return volist;
    }
}
