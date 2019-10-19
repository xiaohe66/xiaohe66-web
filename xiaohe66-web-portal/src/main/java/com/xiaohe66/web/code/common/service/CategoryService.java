package com.xiaohe66.web.code.common.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.code.common.dao.CategoryDao;
import com.xiaohe66.web.code.common.po.Category;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
@Service
public class CategoryService extends AbstractService<CategoryDao,Category> {

    public List<Category> findByPid(Integer pid){
        if(Check.isOneNull(pid)){
            throw new XhWebException(CodeEnum.NULL_EXCEPTION,"pid is null");
        }
        return baseMapper.findByPid(pid);
    }

    public List<Category> findTextSysCategory(){
        String val = SysCfgHelper.getString(Final.Str.DEFAULT_ARTICLE_PID);
        return findByPid(StrUtils.toIntNotException(val));
    }
}
