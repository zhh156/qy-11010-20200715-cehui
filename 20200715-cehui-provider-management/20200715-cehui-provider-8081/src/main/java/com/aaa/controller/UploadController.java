package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.FtpFile;
import com.aaa.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/25 14:16
 * @Description
 *      专注于文件上传
 **/
@RestController
public class UploadController extends BaseController {
    @Autowired
    private UploadService uploadService;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/25 14:23
     * @description:
     *      测试通过feign进行文件上传的操作
     * @param file
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/uploadImage")
    public ResultData uploadImage(@RequestParam("file")MultipartFile[] file){
        List<FtpFile> list = new ArrayList<FtpFile>();
        for (MultipartFile multipartFile : file) {
            System.out.println(multipartFile);
            FtpFile upload = uploadService.upload(multipartFile);
            list.add(upload);
        }
        if(list.size() > 0){
            return addSuccess(list);
        }
        return addFailed("文件添加失败");
    }

}
