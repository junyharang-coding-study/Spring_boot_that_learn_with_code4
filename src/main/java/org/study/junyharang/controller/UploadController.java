package org.study.junyharang.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.study.junyharang.dto.UploadResponseDTO;
import net.coobird.thumbnailator.Thumbnailator;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Log4j2 @RestController public class UploadController {

    @Value("${org.study.junyharang.upload.path}")  // application.properties에 설정한 변수
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResponseDTO>> uploadFile(MultipartFile[] uploadFiles) {

        List<UploadResponseDTO> responseDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {

            // 이미지 파일만 업로드 하도록 처리
            if(uploadFile.getContentType().startsWith("image") == false) {  // 업로드 된 파일이 image가 아니라면?
                log.warn("이 파일은 Image 파일이 아닙니다!");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
                // 실제 Image 저장
                uploadFile.transferTo(savePath);

                // 섬네일 만들기
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;

                // 섬네일 파일 이름은 중간에 s_로 시작하도록 설정
                File thumbnailFile = new File(thumbnailSaveName);

                // 섬네일 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile,100,100);
                responseDTOList.add(new UploadResponseDTO(fileName, uuid, folderPath));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } // for문 끝

        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    } // uploadFile() 끝

    @GetMapping("/display") public ResponseEntity<byte[]> getFile(String fileName) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");

            log.info("출력되는 Image 파일 이름 : " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);

            log.info("파일 위치 :" + file);

            HttpHeaders headers = new HttpHeaders();

            // MIME Type 처리
            headers.add("Content-Type", Files.probeContentType(file.toPath()));

            // file Data 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } // try-catch 끝

        return result;
    } // getFile() 끝

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

    @PostMapping("/removeFile") public ResponseEntity<Boolean> removeFile(String fileName) {

        String srcFileName = null;

        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");

            File file = new File(uploadPath + File.separator + srcFileName);

            boolean response = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());

            response = thumbnail.delete();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        } // try-catch 끝

    } // removeFile() 끝

} // class 끝
