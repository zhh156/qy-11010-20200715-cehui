package com.aaa.service;

import com.aaa.model.FtpFile;
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
    public FtpFile upload(MultipartFile file){
        FtpFile  ftpFile = new FtpFile();
        //1.获取文件的远程名称（为了获取后缀名）
        String oldFileName = file.getOriginalFilename();
        //2.生成新的文件名
        String newFileName = FileNameUtils.getFileName();
        //3.获取文件的后缀名
        String extName = null;
        if (oldFileName != null) {
            extName = oldFileName.substring(oldFileName.lastIndexOf(POINT));
        }
        //4.将文件的后缀名添加到新的文件名上
        newFileName = newFileName + extName;
        //5.获取文件的上传路径（2020/07/10）
        String filePath = DateUtils.formatDate(new Date(),DATE_FORMAT);
        //6.调用文件上传工具类
        try {
            Boolean upload = FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(),
                    filePath, newFileName, file.getInputStream());
            //7.判断上传是否成功
            if(upload){
                //成功
                return ftpFile.setFileName(newFileName).setDir(extName).setFilePath(ftpProperties.getBasePath()+filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 15:21
     * @description:
     *      上传文件，自定义文件名
     * @param file
	 * @param newFileName
     * @return FtpFile
     **/
    public FtpFile upload(MultipartFile file, String newFileName){
        FtpFile  ftpFile = new FtpFile();
        //1.获取文件的远程名称（为了获取后缀名）
        String oldFileName = file.getOriginalFilename();
        //2.判断前端的文件名是否传过来
        if(newFileName == null){
            newFileName = FileNameUtils.getFileName();
        }
        //3.截取后缀名，拼接到新的文件名上
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        //4.获取文件的上传路径（2020/07/10）
        String filePath = DateUtils.formatDate(new Date(),DATE_FORMAT);
        //5.调用文件上传工具类
        try {
            //6.上传文件
            Boolean upload = FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(),
                    filePath, newFileName, file.getInputStream());
            //7.判断上传是否成功
            if(upload){
                //成功
                return ftpFile.setFilePath(filePath).setDir(oldFileName.substring(oldFileName.lastIndexOf(POINT))).setFileName(newFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
    public FtpFile upload(MultipartFile file,String newFileName,String filePath){
        FtpFile ftpFile = new FtpFile();
        //1.获取文件的远程名称（为了获取后缀名）
        String oldFileName = file.getOriginalFilename();
        //2.获取到文件的后缀名
        String extName = oldFileName.substring(oldFileName.lastIndexOf(POINT));
        //3.将后缀名拼接到新的文件名上
        newFileName = newFileName + extName;
        //4.调用文件上传工具类
        try {
            Boolean upload = FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(),
                    filePath, newFileName, file.getInputStream());
            //5.判断上传是否成功
            if(upload){
                //成功
                return ftpFile.setFilePath(filePath).setDir(extName).setFileName(newFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
