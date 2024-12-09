package com.kh.semi.pv.controller;

import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class NotionController {

    @GetMapping("home")
    public String home(HttpSession session){
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        return "pv/notion";
    }

    @PostMapping("home")
    @ResponseBody
    public String m01() throws Exception {
        //HTTP 요청을 보내고 응답받기
        String databaseId ="154fef1413d78073be10d53428a768bb";
        String secretKey ="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";
        String notionVersion ="2022-06-28";
        //URL 설정
        URL url = new URL("https://api.notion.com/v1/databases/" + databaseId + "/query");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //method 설정
        connection.setRequestMethod("POST");

        //data(Header)설정
        connection.setRequestProperty("Authorization", secretKey);
        connection.setRequestProperty("Notion-Version", notionVersion);
        connection.setRequestProperty("Content-Type", "application/json");


        //응답코드 얻기
        int responseCode = connection.getResponseCode();
        System.out.println("responseCode = " + responseCode);

        //데이터 읽기
        StringBuilder response = new StringBuilder();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String str;
        while ((str = reader.readLine()) != null) {
            response.append(str.trim());
        }
        System.out.println("response = " + response);
        return response.toString();

    }
}
