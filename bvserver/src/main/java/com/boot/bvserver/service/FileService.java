package com.boot.bvserver.service;

import com.boot.bvserver.bean.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {

    UploadedFile uploadFileToTemporaryDirectory(MultipartFile file) throws IOException;

    void readTemporaryFileToResponse(String date, String filename, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
