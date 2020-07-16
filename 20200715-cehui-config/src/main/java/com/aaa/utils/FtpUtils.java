package com.aaa.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

import static com.aaa.staticproperties.FtpStatic.DIR;

/**
* @author zhh
* @date 2020/7/10 17:12
* ftp的工具类
*/
public class FtpUtils {
    private FtpUtils(){

    }
    public static Boolean upload(String host, Integer port, String username, String password, String basePath,
                                 String filePath, String fileName, InputStream inputStream){
        /**
         * 最终按照每天日期的文件夹来机械能给上传
         *      2020/7/20--->20文件夹
         *      cd /home/ftp
         */
        //1.创建临时路径
        String tempPath = "";
        //2.创建FTPClient对象（这个对象就是FTP给java所提供的API）
        FTPClient ftpClient = new FTPClient();
        try {
            //3.定义返回状态码
            int replayCode;
            //4.连接ftp
            ftpClient.connect(host,port);
            //5.登录ftp服务器
            ftpClient.login(username,password);
            //6.接收返回的状态码
            //如果成功返回230，如果失败返回503
            replayCode = ftpClient.getReplyCode();
            //7.判断
            if(!FTPReply.isPositiveCompletion(replayCode)){
                //连接失败
                ftpClient.disconnect();
                return false;
            }
            //8.先检测要上传的目录是否存在（2020/07/10）
            //basePath:/home/ftp
            //filePath:/2020/07/10
            //--->/home/ftp/2020/07/10
            if(!ftpClient.changeWorkingDirectory(basePath+fileName)){
                //该文件夹不存在
                //9.创建文件夹
                String[] dirs = filePath.split(DIR);
                //10.把basePath赋值给临时路径
                tempPath = basePath;
                //11.进行循环创建文件夹
                for (String dir : dirs){
                    if(dir == null || StringUtils.isEmpty(dir)){
                        //没有数据
                        continue;
                    }
                    //12.进行拼接临时路径（如果没有进if，则取到的值应该是2020）
                    tempPath = tempPath + "/" + dir;
                    //13.再次检测tempPath是否存在
                    if(!ftpClient.changeWorkingDirectory(tempPath)){
                        //文件夹不存在
                        //14.创建文件夹
                        if(!ftpClient.makeDirectory(tempPath)){
                            //说明创建文件夹失败
                            return false;
                        }else{
                            //15.严谨判断，判断创建出来的目录确实存在
                            ftpClient.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //16.把文件转换为二进制的形式来进行上传
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //17.这里才是真正的文件上传，之前的步骤都是为了这个铺垫
            if(!ftpClient.storeFile(fileName,inputStream)){
                //说明上传失败
                return false;
            }
            //18.关闭输入流
            inputStream.close();
            //19.退出ftp
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(ftpClient.isConnected()){
                try {
                    //说明还在连接中
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
