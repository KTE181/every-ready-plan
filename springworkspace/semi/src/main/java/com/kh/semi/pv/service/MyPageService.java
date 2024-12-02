package com.kh.semi.pv.service;

import com.kh.semi.pv.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MyPageService {

    private final MyPageMapper myPageMapper;

    public String updateProfileImage(MultipartFile profileImage, String employeeId, String uploadDir) throws Exception {
        // 1. 기존 파일 삭제
        String oldProfilePath = myPageMapper.getProfileImage(employeeId);
        if (oldProfilePath != null) {
            String oldFileExtension = oldProfilePath.substring(oldProfilePath.lastIndexOf(".") + 1);
            File oldFile = new File(uploadDir + employeeId + "." + oldFileExtension);
            if (oldFile.exists() && !oldFile.delete()) {
                log.warn("기존 파일 삭제 실패: {}", oldFile.getAbsolutePath());
            }
        }

        // 2. 새 파일 저장
        String fileExtension = Objects.requireNonNull(profileImage.getOriginalFilename()).split("\\.")[1];
        String fileName = employeeId + "." + fileExtension;
        File newFile = new File(uploadDir + fileName);
        profileImage.transferTo(newFile);

        // 3. DB 경로 업데이트
        String newProfilePath = "/img/employee/" + fileName;
        myPageMapper.updateProfileImage(employeeId, newProfilePath);

        return newProfilePath;
    }
}







