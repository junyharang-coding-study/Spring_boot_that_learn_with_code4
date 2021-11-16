package org.study.junyharang.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Log4j2 @RestController public class UploadController {

    @Value("${org.study.junyharang.upload.path}")  // application.properties에 설정한 변수
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles) {

        for (MultipartFile uploadFile : uploadFiles) {

            // 이미지 파일만 업로드 하도록 처리
            if(uploadFile.getContentType().startsWith("image") == false) {  // 업로드 된 파일이 image가 아니라면?
                log.warn("이 파일은 Image 파일이 아닙니다!");
                return;
            } // if문 끝

            // 실제 파일 이름이 IE나, Edge는 전체 경로가 들어오기 때문에 아래와 같이 처리
            String originalFilename = uploadFile.getOriginalFilename();
            String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            log.info("업로드 된 파일 이름은 " + fileName);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            // UUID
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 Under Bar를 이용해서 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try{
                uploadFile.transferTo(savePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // for문 끝
    } // uploadFile() 끝

    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // 폴더 생성
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false) {   // uploadFolder가 존재하지 않는다면?
            // uploadPathFolder 만들기
            uploadPathFolder.mkdirs();
        } // if문 끝

        return folderPath;

    } // makeFolder() 끝

} // class 끝
