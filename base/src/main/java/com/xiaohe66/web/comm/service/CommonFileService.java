package com.xiaohe66.web.comm.service;

import com.xiaohe66.web.comm.dao.CommonFileDao;
import com.xiaohe66.web.comm.po.CommonFile;
import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.util.DateUtils;
import com.xiaohe66.web.common.util.IoUtils;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.home.po.HomeUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author xh
 * @date 18-03-22 022
 */
@Service
public class CommonFileService extends AbstractService<CommonFile>{

    private static final Logger LOG = LoggerFactory.getLogger(CommonFileService.class);

    private CommonFileDao commonFileDao;

    @Value("${file.home}")
    private String fileHomeUrl;

    public CommonFileService(){}

    @Autowired
    public CommonFileService(CommonFileDao commonFileDao) {
        super(commonFileDao);
        this.commonFileDao = commonFileDao;
    }

    /**
     * 上传一个文件,若文件已存在，则不上传直接返回CommonFile实例
     * @param bytes     待上传的文件的字节表现形式
     * @param md5   md5摘要
     * @return  返回该文件对应的CommonFile实例
     */
    public CommonFile uploadFile(byte[] bytes,String md5){
        if(bytes == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"upload file is empty");
        }

        if(StrUtils.isEmpty(md5)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"md5 is empty");
        }
        LOG.info("准备上传文件："+md5);

        CommonFile commonFile = findByMd5(md5);
        //不存在时，进行上传
        if(commonFile == null){
            //创建commonFile实例保存到数据库
            commonFile = new CommonFile();
            commonFile.setStartTime(new Date());
            commonFile.setMd5(md5);
            commonFile.setFileByte(bytes.length);
            this.add(commonFile,null);

            String folderName = String.valueOf(commonFile.getId()/50);
            String fileUrl = File.separator+folderName+File.separator + md5;
            //上传
            File serverFile = new File(fileHomeUrl+fileUrl);
            File parentFile = serverFile.getParentFile();

            //文件夹不存在时创建
            if(!serverFile.getParentFile().exists()){
                parentFile.mkdirs();
            }

            FileOutputStream fileOutputStream = null;
            BufferedOutputStream stream = null;
            try{
                fileOutputStream = new FileOutputStream(serverFile);
                stream = new BufferedOutputStream(fileOutputStream);
                stream.write(bytes);
            }catch (IOException e){
                throw new XhException(CodeEnum.IO_EXCEPTION);
            }finally {
                IoUtils.close(stream,fileOutputStream);
            }

            //结束上传，更新数据库结束时间
            commonFile.setEndTime(new Date());
            //数据库中保存的是相对地址
            commonFile.setFileUrl(fileUrl);
            updateById(commonFile,null);
        }else{
            LOG.info("文件已存在，不再上传，md5="+md5);
        }
        return commonFile;
    }

    public void outputFile(Long id,OutputStream outputStream){
        if(Check.isNull(outputStream)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"outputStream is null");
        }
        if(Check.isNull(id)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"file id is null");
        }
        CommonFile commonFile = findById(id);
        String url = fileHomeUrl + commonFile.getFileUrl();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(url));
            byte[] date = new byte[commonFile.getFileByte()];
            fileInputStream.read(date);
            outputStream.write(date);
            outputStream.flush();
        } catch (IOException e) {
            throw new XhException(CodeEnum.IO_EXCEPTION);
        } finally {
            IoUtils.close(fileInputStream,outputStream);
        }
    }

    /**
     * 判断一个文件是否存在于服务器中
     * @param md5   文件的md5摘要
     * @return  返回true表示存在，false表示不存在
     */
    public boolean isExist(String md5){
        return findByMd5(md5) != null;
    }

    public CommonFile findByMd5(String md5){
        return commonFileDao.findByMd5(md5);
    }
}
