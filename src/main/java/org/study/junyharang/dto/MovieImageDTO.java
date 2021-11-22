package org.study.junyharang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder public class MovieImageDTO {

    private String uuid;
    private String path;
    private String imageName;

    public String getImageName() {

        try {
            return URLEncoder.encode(path+"/"+uuid+"_"+imageName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } // try-catch 끝

        return "";
    } // getImageName() 끝

    public String getThumbnailURL() {
        try{
            return URLEncoder.encode(path+"/s_"+uuid+"_"+imageName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    } // getThumbnailURL() 끝
} // class 끝
