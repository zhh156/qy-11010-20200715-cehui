package com.aaa.service;

import com.aaa.properties.FtpProperties;
import com.aaa.utils.DateUtils;
import com.aaa.utils.FileNameUtils;
import com.aaa.utils.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.aaa.staticproperties.FtpStatic.POINT;
import static com.aaa.staticproperties.TimeFormatProperties.DATE_FORMAT;


/**
* @author zhh
* @date 2020/7/10 16:53
* 使用ftp上传文件
*/
@Service
public class UploadService {
    @Autowired
    private FtpProperties ftpProperties;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 10:51
     * @description:
     *      ftp文件上传,自动生成新的文件名自动生成路径
     * @param file
     * @return java.lang.Boolean
     **/
    public Boolean upload(MultipartFile file){
        //1.获取文件的远程名称（为了获取后缀名）
        String oldFileName = file.getOriginalFilename();
        //2.生成新的文件名
        String newFileName = FileNameUtils.getFileName();
        //3.截取后缀名，拼接到新的文件名上
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        //4.获取文件的上传路径（2020/07/10）
        String filePath = DateUtils.formatDate(new Date(),DATE_FORMAT);
        //5.调用文件上传工具类
        try {
            return FtpUtils.upload(ftpProperties.getHost(),ftpProperties.getPort(),ftpProperties.getUsername(),ftpProperties.getPassword(),ftpProperties.getBasePath(),
                    filePath,newFileName,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 11:01
     * @description:
     *      上传文件，指定上传之后的文件名，自动生成文件夹
     * @param file
	 * @param newFileName 指定的文件名
     * @return java.lang.Boolean
     **/
    public Boolean upload(MultipartFile file,String newFileName){
        //1.获取文件的远程名称（为了获取后缀名）
        String oldFileName = file.getOriginalFilename();
        //2.截取后缀名，拼接到新的文件名上
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        //3.获取文件的上传路径（2020/07/10）
        String filePath = DateUtils.formatDate(new Date(),DATE_FORMAT);
        //4.调用文件上传工具类
        try {
            return FtpUtils.upload(ftpProperties.getHost(),ftpProperties.getPort(),ftpProperties.getUsername(),ftpProperties.getPassword(),ftpProperties.getBasePath(),
                    filePath,newFileName,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 11:04
     * @description:
     *      上传文件，指定文件名和路径
     * @param file
	 * @param newFileName
	 * @param filePath 指定存储文件的路径
     * @return java.lang.Boolean
     **/
    public Boolean upload(MultipartFile file,String newFileName,String filePath){
        //1.获取文件的远程名称（为了获取后缀名）
        String oldFileName = file.getOriginalFilename();
        //2.截取后缀名，拼接到新的文件名上
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        //3.调用文件上传工具类
        try {
            return FtpUtils.upload(ftpProperties.getHost(),ftpProperties.getPort(),ftpProperties.getUsername(),ftpProperties.getPassword(),ftpProperties.getBasePath(),
                    filePath,newFileName,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
