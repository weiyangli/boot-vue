package com.boot.bvserver.controller;

import com.boot.bvserver.bean.Result;
import com.boot.bvserver.bean.UploadedFile;
import com.boot.bvserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传单个文件到临时文件夹
     * 网址: http://localhost/api/file/upload
     *
     * @param file 上传的文件
     * @return 上传成功时 payload 为 UploadedFile，里面有上传的文件名和 URL，success 为 true，上传出错时抛异常
     * @throws IOException 保存文件出错时抛出异常
     */
    @PostMapping(Urls.API_FILE_UPLOAD)
    public Result<UploadedFile> uploadTemporaryFile(@RequestParam("file") MultipartFile file) throws IOException {
        return Result.ok(fileService.uploadFileToTemporaryDirectory(file));
    }

    /**
     * 访问临时文件中的文件
     * 网址: http://localhost:8080/api/file/read/2019-12-27/123.doc
     *
     * @param request  HttpServletRequest 对象
     * @param response HttpServletResponse 对象
     * @throws IOException 读取文件出错时抛出异常
     */
    @GetMapping(Urls.API_FILE_READ)
    public void accessTemporaryFile(@PathVariable("date") String date,
                                    @PathVariable("filename") String filename,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        fileService.readTemporaryFileToResponse(date, filename, request, response);
    }
}
