package org.study.junyharang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data @AllArgsConstructor
public class UploadResponseDTO implements Serializable {

    private String fileName;
    private String uuid;
    private String folderPath;

    public String getImageURL() {

        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } // try-catch 끝
        return "";

    } // getImageURL() 끝

    public String getThumbnailURL() {

        try {
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } // try-catch 끝

        return "";

    } // getThumbnailURL() 끝

} // class 끝
