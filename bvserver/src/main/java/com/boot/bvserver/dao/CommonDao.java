package com.boot.bvserver.dao;

import com.boot.bvserver.bean.UploadedFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonDao {

    void insertOrUpdateUploadedFile(UploadedFile uploadedFile);

    UploadedFile findUploadedFileById(Long fileId);
}
