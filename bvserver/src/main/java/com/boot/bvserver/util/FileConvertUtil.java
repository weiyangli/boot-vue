package com.boot.bvserver.util;

import org.jodconverter.DocumentConverter;
import org.jodconverter.office.OfficeException;
import org.jodconverter.spring.JodConverterBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;

@Component
public class FileConvertUtil {
    @Value("${file.path}")
    private String tempDirectory; // 临时文件目录

    @Value("${libreOffice.home}")
    private String libreOfficeHome;

    @Value("${libreOffice.processTimeout}")
    private long processTimeout;

    @Value("${libreOffice.taskExecutionTimeout}")
    private long taskExecutionTimeout;

    private JodConverterBean jodConverterBean;

    /**
     * 将文件转 pdf
     * @param sourceFile       源文件
     * @param targetFile       目标文件
     * @throws OfficeException
     */
    public void ConverterWordToPdf(File sourceFile, File targetFile) throws OfficeException {
        DocumentConverter converter = getJodConverterBean().getConverter();
        converter.convert(sourceFile).to(targetFile).execute();
    }

    public JodConverterBean getJodConverterBean() throws OfficeException {
        if (jodConverterBean == null) {
            synchronized(this) {
                if (jodConverterBean == null) {
                    jodConverterBean = new JodConverterBean();
                    jodConverterBean.setOfficeHome(libreOfficeHome);
                    jodConverterBean.setProcessTimeout(processTimeout);
                    jodConverterBean.setTaskExecutionTimeout(taskExecutionTimeout);
                    jodConverterBean.afterPropertiesSet();
                }
            }
        }

        return jodConverterBean;
    }

    @PreDestroy
    public void destroy() {
        synchronized(this) {
            if (jodConverterBean != null) {
                jodConverterBean.destroy();
                jodConverterBean = null;
            }
        }
    }
}
