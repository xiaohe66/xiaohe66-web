package com.xiaohe66.web.code.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.resume.mapper.ResumeFuncMapper;
import com.xiaohe66.web.code.resume.dto.ResumeFuncDto;
import com.xiaohe66.web.code.resume.po.ResumeFunc;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xh
 * @date 18-10-12 012
 */
@Service
public class ResumeFuncService extends AbstractService<ResumeFuncMapper,ResumeFunc>{

    private ResumeFuncMapper baseMapper;

    public List<ResumeFuncDto> findDtoByProjectId(Integer projectId){
        Check.notEmpty(projectId);
        List<ResumeFunc> resumeFuncList = baseMapper.findByProjectId(projectId);
        return ClassUtils.convert(ResumeFuncDto.class, resumeFuncList);
    }
}
