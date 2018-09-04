package com.xiaohe66.web.file.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.IoUtils;
import com.xiaohe66.web.file.dao.CommonFileTmpDao;
import com.xiaohe66.web.file.po.CommonFileTmp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * 临时文件service
 *
 * @author xh
 * @version 1.0
 * @time 2018-06-05 10:11
 */
@Service
public class CommonFileTmpService extends AbstractService<CommonFileTmp>{

    private static final Logger LOG = LoggerFactory.getLogger(CommonFileTmpService.class);

    private CommonFileTmpDao commonFileTmpDao;

    @Value("${file.home}")
    private String fileHomeUrl;

    public CommonFileTmpService(){}

    @Autowired
    public CommonFileTmpService(CommonFileTmpDao commonFileTmpDao){
        super(commonFileTmpDao);
        this.commonFileTmpDao = commonFileTmpDao;
    }

    /**
     * 上传一个临时文件，若文件已存在，则不上传，直接返回结果
     * @param md5       文件的md5值
     * @param chunk     当前上传的临时文件区块数
     */
    @Transactional(rollbackFor = Exception.class)
    public void uploadTmpFile(InputStream inputStream,Integer fileByte,String md5, Integer chunk){
        Check.notEmptyCheck(inputStream,fileByte,md5,chunk);
        if(!isExist(md5,chunk)){
            String path = createLogicPath(md5)+chunk;
            IoUtils.writeToFile(inputStream,new File(fileHomeUrl+path),false);

            CommonFileTmp commonFileTmp = new CommonFileTmp();
            commonFileTmp.setMd5(md5);
            commonFileTmp.setFileByte(fileByte);
            commonFileTmp.setChunk(chunk);
            commonFileTmp.setFileUrl(path);
            this.add(commonFileTmp,null);
        }else{
            LOG.info("临时文件已存在，不必再上传:md5="+md5+",chunk="+chunk);
        }
    }

    /**
     * 返回md5对应的已上传完的临时文件区块序号
     *
     * @param md5   文件的md5值
     * @return  已上传完的区块Set集合
     */
    public Set<Integer> findFinishChunk(String md5){
        Check.notEmptyCheck(md5);
        return commonFileTmpDao.findFinishChunk(md5);
    }

    /**
     * 返回所有md5的临时文件
     * @param md5   文件的md5值
     * @return  {@link List<CommonFileTmp>},md5对应的所有临时文件实例
     */
    public List<CommonFileTmp> findFileTmp(String md5){
        Check.notEmptyCheck(md5);
        return commonFileTmpDao.findFileTmp(md5);
    }

    /**
     * 统计md5对应文件上传完的临时文件区块数量
     * @param md5   文件的md5值
     * @return  md5值对应文件上传完成的数量
     */
    public int countFinish(String md5){
        Check.notEmptyCheck(md5);
        return commonFileTmpDao.countFinish(md5);
    }

    /**
     * 将md5对应的所有临时文件，整合到指定的file中
     * @param md5   文件的md5值
     * @param fileFull  组合该文件
     */
    public void joinToFile(String md5,File fileFull){
        List<CommonFileTmp> commonFileTmpList = this.findFileTmp(md5);
        File fileTmp;
        for (CommonFileTmp commonFileTmp : commonFileTmpList) {
            //整合临时文件到主文件
            fileTmp = new File(commonFileTmp.getFileUrl());
            IoUtils.writeToFile(fileTmp,fileFull,true);
        }
    }

    /**
     * 删除md5对应的临时文件
     * <p>该方法会同时删除数据库和磁盘中的记录
     *
     * @param md5   文件的md5值
     */
    @Transactional(rollbackFor = Exception.class)
    public void delByMd5(String md5){
        CommonFileTmp commonFileTmp = new CommonFileTmp();
        commonFileTmp.setMd5(md5);
        this.delByParamOfHard(commonFileTmp);

        IoUtils.delete(fileHomeUrl+createLogicPath(md5));
    }

    /**
     * 返回对应的区块是否上传完成
     * @param md5       文件的md5值
     * @param chunk     区块数
     * @return  该md5对应的区块上传完时返回true，反之返回false
     */
    public boolean isExist(String md5,int chunk){
        Check.notEmptyCheck(md5,chunk);
        return commonFileTmpDao.isExist(md5,chunk);
    }

    /**
     * 根据md5值生成一个文件逻辑路径
     * @param md5   文件的md5值
     * @return  格式：tmp/a/a5f3a36d02749cc5cf049fga80727f90/
     */
    private String createLogicPath(String md5){
        return "tmp"+File.separator+md5.substring(0,1)+File.separator+md5+File.separator;
    }

}