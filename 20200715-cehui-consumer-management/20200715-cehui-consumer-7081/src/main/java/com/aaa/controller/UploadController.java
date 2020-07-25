package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/25 15:27
 * @Description
 **/
@RestController
public class UploadController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/25 14:23
     * @description:
     *      测试通过feign进行文件上传的操作
     * @param file
     * @return com.aaa.base.ResultData
     **/
    @PostMapping("/uploadImage")
    public ResultData uploadImage(@RequestParam("file") MultipartFile[] file){
        for (MultipartFile multipartFile : file) {
            System.out.println(multipartFile);
        }
        return iProjectService.uploadImage(file);
    }
}
