package com.seomoon.base.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FilterTransfer {

    public String webToLocal(MultipartFile uploadfile) throws IOException {
        String check;
        double doubleCheck;
        String baseUrl = "C://work/input_image";

        if (!uploadfile.isEmpty()) {

            File newFileName = new File(baseUrl + uploadfile.getOriginalFilename());

            // 전달된 내용을 실제 물리적인 파일로 저장
            uploadfile.transferTo(newFileName);

            check = RequestUtil.restRequest("http://localhost:8000/catdog/predict/",
                    baseUrl + uploadfile.getOriginalFilename());

            System.out.println("result from Django:" + check);
            doubleCheck = Double.parseDouble(check);

            if (doubleCheck < 0.5) {
                return "cat";
            } else {
                return "dog";
            }
        }
        return "error";
    }

}
