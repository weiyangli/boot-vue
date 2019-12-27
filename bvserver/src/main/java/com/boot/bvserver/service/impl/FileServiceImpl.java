package com.boot.bvserver.service.impl;

import com.boot.bvserver.bean.UploadedFile;
import com.boot.bvserver.dao.CommonDao;
import com.boot.bvserver.service.FileService;
import com.boot.bvserver.util.IdWorker;
import com.boot.bvserver.util.SecurityUtils;
import com.boot.bvserver.util.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    private static Logger logger = LoggerFactory.getLogger(Utils.class.getName());

    @Value("${file.path}")
    private String filePath;   // 上传文件的路径

    private String API_PATH = "/api/file/read";

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CommonDao commonDao;

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public UploadedFile uploadFileToTemporaryDirectory(MultipartFile file) throws IOException {
        // 1. 处理文件相关信息
        // 2. 上传文件
        // 3. 将文件信息入库

        // [1] 处理文件相关信息
        InputStream inputStream = file.getInputStream();                           // 文件流
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 获取文件后缀
        Long fileId = idWorker.nextId();                                           // 文件 id 作为临时文件名
        String targetFilePath = String.format("%s/%s", generateTargetFilePath(), fileId + "." + extension); // 文件上传地址
        String accessingPath = String.format("%s/%s/%s", API_PATH, Utils.DateToString(new Date(), "yyyy-MM-dd"), fileId + "." + extension);        // 文件访问地址
        // [2] 上传文件
        File tempFile = new File(targetFilePath);
        FileUtils.copyInputStreamToFile(inputStream, tempFile);

        UploadedFile uploadedFile = new UploadedFile(fileId, file.getOriginalFilename(),accessingPath, 0, SecurityUtils.getLoginUserId());
        // [6] 如果上传的是图片，则还要读取图片的宽和高
        if (Utils.isImage(file.getOriginalFilename())) {
            Dimension size = Utils.getImageSize(tempFile.getAbsolutePath());

            if (size != null) {
                uploadedFile.setImageWidth((int) size.getWidth());
                uploadedFile.setImageHeight((int) size.getHeight());
            }
        }
        // [3] 将文件信息入库
        commonDao.insertOrUpdateUploadedFile(uploadedFile);
        return uploadedFile;
    }

    /**
     * 从临时文件夹读取文件到 HttpServletResponse
     *
     * @param filename 临时文件名
     * @param request  HttpServletRequest 对象
     * @param response HttpServletResponse 对象
     */
    @Override
    public void readTemporaryFileToResponse(String date, String filename, HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 1. 获取临时文件
        // 2. 获取临时文件上传时的名字
        // 3. 把临时文件写入 HttpServletResponse
        File   file = getTemporaryFileForFilename(filename, date);
        String path = file.getAbsolutePath();
        String originalFilename = getUploadedFilename(filename);

        originalFilename = (originalFilename != null) ? originalFilename : filename.substring(filename.lastIndexOf(46));

        Utils.readFileToResponse(path, originalFilename, request, response);
    }

    /**
     * 获取临时文件
     *
     * @param filename  文件名
     * @return
     */
    private File getTemporaryFileForFilename(String filename, String date) {
        return new File(String.format("%s%s/%s", filePath, date, filename));
    }

    /**
     * 使用文件名查找上传的文件的原始名字
     *
     * @param filename 上传得到的文件名，格式为 217933208270929920.png 这样的格式
     * @return 返回上传的文件的原始名字，不存在则返回 null
     */
    public String getUploadedFilename(String filename) {

        Long fileId = getUploadedFileId(filename);
        UploadedFile uploadedFile = findUploadedFileById(fileId);

        if (uploadedFile != null) {
            return uploadedFile.getFilename();
        } else {
            logger.warn("文件没有上传记录: {}", filename);
            return null;
        }
    }

    /**
     * 根据 id 查文件
     * @param fileId
     * @return
     */
    private UploadedFile findUploadedFileById(Long fileId) {
        return commonDao.findUploadedFileById(fileId);
    }

    /**
     * 根据上传的文件的文件名获取文件的 ID，文件名格式为 {long number}[.ext]
     *
     * @param filename 上传的文件名
     * @return 文件的 ID，如果文件名不是上传的文件名格式，则返回 0
     */
    public long getUploadedFileId(String filename) {
        try {
            return Long.parseLong(FilenameUtils.getBaseName(filename));
        } catch (Exception ex) {
            logger.warn("文件名不是上传的格式 {long number}[.ext]: {}", filename);
            return 0;
        }
    }

    /**
     * 生成文件上传地址
     *
     * @return
     */
    public String generateTargetFilePath() {
        // 上传的文件地址组成结构: filePath + 上传日期
        return String.format("%s%s", filePath, Utils.DateToString(new Date(), "yyyy-MM-dd"));
    }
}
