package com.kh.semi.pv.controller;

import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.pv.service.MyPageService;
import com.kh.semi.pv.vo.MyPageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {

    private final MyPageService myPageService;


    //마이페이지 화면
    @GetMapping
    public String mypage(HttpSession session)
    {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        if(loginEmployeeVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }
        return "pv/mypage/mypage";
    }

    @PostMapping("/uploadProfileImage")
    public ResponseEntity<Map<String, Object>> uploadProfileImage(
            @RequestParam("profileImage") MultipartFile profileImage,
            @RequestParam("employeeId") String employeeId,
            HttpSession session) {


        Map<String, Object> response = new HashMap<>();
        try {
            // 파일 업로드 및 기존 파일 삭제 로직
            String projectRoot = new File("").getAbsolutePath();
            String uploadDir = projectRoot + "/src/main/resources/static/img/employee/";
            String newProfilePath = myPageService.updateProfileImage(profileImage, employeeId, uploadDir);

            // 세션 데이터 업데이트
            LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
            if (loginEmployeeVo != null) {
                loginEmployeeVo.setProfileImage(newProfilePath);
                session.setAttribute("loginEmployeeVo", loginEmployeeVo);
            }


            response.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "파일 업로드 실패: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Map<String, Object>> changePassword(
            @RequestBody Map<String, String> passwordData,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            String currentPassword = passwordData.get("currentPassword");
            String newPassword = passwordData.get("newPassword");

            // 로그인된 사용자 정보 가져오기
            LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
            if (loginEmployeeVo == null) {
                throw new IllegalStateException("사용자 세션이 만료되었습니다.");
            }

            // 비밀번호 변경 로직 처리
            boolean isUpdated = myPageService.changePassword(
                    loginEmployeeVo.getNo(),
                    currentPassword,
                    newPassword);

            if (isUpdated) {
                // 세션 초기화 및 성공 응답
                session.invalidate();
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "현재 비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "비밀번호 변경 실패: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }




}
