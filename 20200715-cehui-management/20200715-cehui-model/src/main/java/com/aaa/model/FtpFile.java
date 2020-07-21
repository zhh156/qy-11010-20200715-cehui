package com.aaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 15:08
 * @Description
 *      上传后的文件
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FtpFile {
    //文件上传的资源路径
    private String filePath;
    //上传文件的后缀名
    private String dir;
    //上传的文件名
    private String fileName;
}
