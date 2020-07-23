package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.ResourceMapper;
import com.aaa.model.FtpFile;
import com.aaa.model.Resource;
import com.aaa.utils.IDUtils;
import com.aaa.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/17 14:20
 * @Description
 *      附件表
 **/
@Service
public class ResourceService extends BaseService<Resource> {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UploadService uploadService;

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/17 14:23
     * @description:
     *      查询某个测绘单位的资源信息
     * @param id 测绘单位表的主键id，可以关联附件表的ref_biz_id
     * @return java.util.List<com.aaa.model.Resource>
     **/
    public List<Resource> queryResourceById(Long id){
        Resource resource =  new Resource();
        //1.判断前端的数据是否传过来了
        if(null != id){
            //传过来了
            resource.setRefBizId(id);
            //2.查询某个测绘单位的所有资源信息
            List<Resource> resources = super.selectList(resource);
            //3.判断查询是否成功
            if(null != resources){
                //成功
                return resources;
            }
        }
        return null;
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 22:37
     * @description:
     *      上传文件并将文件信息放入数据库中
     * @param file 上传的文件
	 * @param refBizType 关联业务的类型
	 * @param refBizId 关联业务的id
	 * @param memo 备注
     * @return com.aaa.vo.TokenVo
     **/
    public TokenVo insertResourceById(MultipartFile file, String refBizType, Long refBizId, String memo){
        TokenVo tokenVo = new TokenVo();
        //1.判断文件是否从前端传过来了
        if(null != file){
            //传过来了
            //2.将文件上传到ftp上
            FtpFile upload = uploadService.upload(file);
            //3.判断文件是否上传成功
            if(upload != null){
                //成功
                //4.将上传的文件的信息保存到数据库中
                Resource resource = new Resource();
                //4.1.获取到上传文件的各种信息
                Long id = Long.parseLong(IDUtils.getTimeId());
                String name = upload.getFileName();//资源名称
                Long size = file.getSize();//资源大小
                String filePath = upload.getFilePath();//资源路径
                String contentType = file.getContentType();//资源文件类型
                String extName = upload.getDir();//资源后缀名
                //4.2.判断数据库中是否已经存在将要设置的id(通过循环，查询出来的为空时跳出循环)
                Resource resource1 = null;
                do{
                    resource1 = resourceMapper.selectByPrimaryKey(id);
                    id = Long.parseLong(IDUtils.getTimeId());
                }while(resource1 != null);
                //4.3.对resource进行赋值
                resource.setId(id).setName(name).setSize(size).setPath(filePath).setType(contentType)
                        .setExtName(extName).setRefBizId(refBizId).setRefBizType(refBizType)
                        .setCreateTime(new Date()).setMemo(memo);
                //4.4.保存的操作
                Integer add = super.add(resource);
                //5.判断保存是否成功
                if(add > 0){
                    //成功
                    return tokenVo.setIsSuccess(true).setData(add);
                }
            }else{
                //文件上传失败
                return tokenVo.setIsSuccess(false).setOperation(3);
            }
        }
        //系统发生错误
        return tokenVo.setIsSuccess(false).setType(4);
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/21 15:58
     * @description:
     *      删除某一个资源
     * @param refBizId
	 * @param refBizType
     * @return java.lang.Boolean
     **/
    public Boolean deleteResourceByRefBizId(Long refBizId,String refBizType){
        //1.判断前端的数据是否传过来
        if(refBizId != null){
            //传过来了
            Resource resource = new Resource();
            resource.setRefBizId(refBizId).setRefBizType(refBizType);
            //2.查询要删除的资源是否存在
            Resource resource1 = super.selectOne(resource);
            //3.判断是否存在
            if(resource1 != null){
                //存在
                //4.进行删除操作
                Integer integer = resourceMapper.deleteResourceByRefBizId(refBizId, refBizType);
                //5.判断删除操作是否成功
                if(integer > 0){
                    return true;
                }else {
                    return false;
                }
            }else {
                return true;
            }
        }
        return false;
    }
}
