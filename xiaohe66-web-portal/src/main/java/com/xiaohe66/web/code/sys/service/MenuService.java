package com.xiaohe66.web.code.sys.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.sys.dto.MenuTreeDto;
import com.xiaohe66.web.code.sys.mapper.MenuMapper;
import com.xiaohe66.web.code.sys.po.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 2021.02.22 17:58
 */
@Service
public class MenuService extends AbstractService<MenuMapper, Menu> {

    public List<MenuTreeDto> all() {
        return getAllDto(0);
    }

    private List<MenuTreeDto> getAllDto(Integer pid) {
        List<Menu> menuList = baseMapper.selectByPid(pid);

        return ClassUtils.convert(MenuTreeDto.class, menuList, (dto, po) -> {
            List<MenuTreeDto> menuDtoList = getAllDto(po.getId());
            dto.setChildren(menuDtoList);
        });
    }

}
