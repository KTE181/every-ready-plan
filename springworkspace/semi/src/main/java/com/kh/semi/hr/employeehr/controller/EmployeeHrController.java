package com.kh.semi.hr.employeehr.controller;

import com.kh.semi.hr.employeehr.service.EmployeeHrService;
import com.kh.semi.hr.employeehr.vo.EmployeeVo;
import com.kh.semi.login.vo.AdminLoginVo;
import com.kh.semi.login.vo.LoginVo;
import com.kh.semi.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmployeeHrController {

    private final EmployeeHrService service;

    @GetMapping("employeehr")
    public String getEmployeeList(
            @RequestParam(value = "no", required = false) String no,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dname", required = false) String dname,
            @RequestParam(value = "pname", required = false) String pname,
            @RequestParam(value = "esname", required = false) String esname,
            @RequestParam(value = "page", defaultValue = "1") int currentPage,
            Model model, HttpSession session) {
        LoginVo loginEmployeeVo = (LoginVo) session.getAttribute("loginEmployeeVo");
        AdminLoginVo adminVo = (AdminLoginVo) session.getAttribute("loginAdminVo");
        if(loginEmployeeVo==null&&adminVo==null){
            session.setAttribute("loginalertMsg","로그인후 이용하세요");
            return "redirect:/login";
        }


        // 전체 직원 수 조회 (검색 조건 포함)
        int listCount = service.getEmployeeCount(no, name, dname, pname, esname);

        // 페이징 정보 계산
        int pageLimit = 5; // 하단 페이지 번호 최대 개수
        int boardLimit = 12; // 한 페이지에 보여줄 데이터 수
        PageVo pageVo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        // 직원 리스트 조회
        List<EmployeeVo> employeeList = service.searchEmployee(no, name, dname, pname, esname, pageVo);
        System.out.println(currentPage);
        System.out.println(pageVo.getOffset());
        System.out.println(pageVo.getBoardLimit());
        // 모델에 데이터 전달
        model.addAttribute("employeeVoList", employeeList);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("no", no);
        model.addAttribute("name", name);
        model.addAttribute("dname", dname);
        model.addAttribute("pname", pname);
        model.addAttribute("esname", esname);

        return "hr/employeehr/employeehr"; // JSP 페이지로 이동
    }

    @PostMapping("/employeehr/delete")
    public ResponseEntity<String> deleteEmployees(@RequestBody List<String> ids) {
        try {
            // 리스트를 "1,2,3" 형식의 문자열로 변환
            String idString = String.join(",", ids);

            // 서비스에 전달
            service.deleteEmployees(idString);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            log.error("삭제 중 오류 발생", e);
            return ResponseEntity.status(500).body("삭제 실패");
        }
    }

    //사원등록
    @PostMapping("/employeehr/register")
    public String registerEmployee(
            @ModelAttribute EmployeeVo employeeVo,
            @RequestParam("profilePicture") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        try {
            // 날짜 변환: yyyy-MM-dd -> yyyyMMdd
            employeeVo.setEnterDate((employeeVo.getEnterDate()).replace("-", ""));
            employeeVo.setOutDate((employeeVo.getOutDate()).replace("-", ""));
            employeeVo.setBirth((employeeVo.getBirth()).replace("-", ""));

            // 사원 등록 및 사번 생성
            String generatedId = service.registerEmployee(employeeVo);
            log.info("Generated Employee No: {}", generatedId);

            // 프로필 사진 처리
            String profileImagePath = "/img/employee/default.png"; // 기본값
            if (!file.isEmpty()) {
                // 파일 저장 경로
                String relativePath = "/img/employee";
                String uploadDir = new File("src/main/resources/static" + relativePath).getAbsolutePath();
                File dir = new File(uploadDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // 파일명 생성 및 저장
                String originalFilename = file.getOriginalFilename();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                String newFileName = generatedId + "." + fileExtension;
                File destination = new File(uploadDir, newFileName);
                file.transferTo(destination);

                // 파일 경로 설정
                profileImagePath = relativePath + "/" + newFileName;
            }

            // 프로필 이미지 경로 업데이트
            service.updateEmployeeProfileImage(generatedId, profileImagePath);

            // 성공 메시지 전달
            redirectAttributes.addFlashAttribute("successMessage", "등록이 성공적으로 완료되었습니다.");
            return "redirect:/employeehr"; // 성공 후 리스트 페이지로 리다이렉트
        } catch (Exception e) {
            log.error("사원 등록 중 오류 발생", e);
            redirectAttributes.addFlashAttribute("errorMessage", "등록에 실패하였습니다.");
            return "redirect:/employeehr"; // 실패 후에도 동일 페이지로 리다이렉트
        }
    }
    //상세조회
    @GetMapping("/employeehr/detail/{no}")
    @ResponseBody
    public ResponseEntity<EmployeeVo> getEmployeeDetail(@PathVariable("no") String no) {
        try {
            EmployeeVo employee = service.getEmployeeDetail(no);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            log.error("사원 상세 정보 로드 중 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //수정
    @PostMapping("/employeehr/update")
    public ResponseEntity<String> updateEmployee(
            @ModelAttribute EmployeeVo employeeVo,
            @RequestParam(value = "profilePicture", required = false) MultipartFile file) {
        try {
            // 파일 처리
            if (file != null && !file.isEmpty()) {
                String profileImagePath = "/img/employee/" + employeeVo.getNo() + "_" + file.getOriginalFilename();
                String uploadDir = new File("src/main/resources/static/img/employee").getAbsolutePath();
                File destination = new File(uploadDir, employeeVo.getNo() + "_" + file.getOriginalFilename());
                file.transferTo(destination);
                employeeVo.setProfileImage(profileImagePath); // VO에 이미지 경로 설정
            }

            // 직원 정보 업데이트
            service.updateEmployee(employeeVo);

            return ResponseEntity.ok("수정 완료");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("수정 실패");
        }
    }


}












