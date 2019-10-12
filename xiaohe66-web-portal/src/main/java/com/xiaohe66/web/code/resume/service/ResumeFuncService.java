package com.xiaohe66.web.code.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.resume.dao.ResumeFuncDao;
import com.xiaohe66.web.code.resume.dto.ResumeFuncDto;
import com.xiaohe66.web.code.resume.po.ResumeFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xh
 * @date 18-10-12 012
 */
@Service
public class ResumeFuncService extends AbstractService<ResumeFuncDao,ResumeFunc>{

    private ResumeFuncDao baseMapper;

    public List<ResumeFuncDto> findDtoByProjectId(Integer projectId){
        Check.notNullCheck(projectId);
        List<ResumeFunc> resumeFuncList = baseMapper.findByProjectId(projectId);
        return ClassUtils.convertList(ResumeFuncDto.class, resumeFuncList);
    }
}
