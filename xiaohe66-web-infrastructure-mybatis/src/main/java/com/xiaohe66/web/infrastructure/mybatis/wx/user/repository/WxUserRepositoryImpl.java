package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.value.WxLoveUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxTaskUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserAvatar;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.convert.WxUserDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxLoveUserDo;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxTaskUserDo;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxUserDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import com.xiaohe66.web.integration.config.FileConfig;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.10.29 17:28
 */
@Repository
@AllArgsConstructor
@Slf4j
public class WxUserRepositoryImpl
        extends AbstractMybatisService<WxUserDoConverter, WxUserMapper, WxUserDo, WxUser, WxUserId>
        implements WxUserRepository {

    private final WxTaskUserRepositoryImpl taskUserRepository;
    private final WxLoveUserRepositoryImpl loveUserRepository;

    private final FileConfig fileConfig;

    @Override
    protected void insertImpl(WxUser agg) {

        super.insertImpl(agg);

        saveAvatar(agg);

        if (agg.getWxTaskUserOpenId() != null) {

            taskUserRepository.insert(agg.getCreateId(), agg.getWxTaskUserOpenId());
        }
        if (agg.getWxLoveUserOpenId() != null) {

            loveUserRepository.insert(agg.getCreateId(), agg.getWxLoveUserOpenId());
        }
    }

    @Override
    protected void updateImpl(WxUser agg, WxUser snapshot) {
        super.updateImpl(agg, snapshot);

        boolean isNeedSaveAvatar = snapshot == null || !Objects.equals(agg.getAvatar(), snapshot.getAvatar());
        if (isNeedSaveAvatar) {
            saveAvatar(agg);
        }

    }

    @Override
    protected void removeByIdImpl(WxUserId id) {

        WxUserDo wxUserDo = getById(id.getValue());
        Long createId = wxUserDo.getCreateId();

        taskUserRepository.removeByCreateId(createId);
        loveUserRepository.removeByCreateId(createId);

        super.removeByIdImpl(id);
    }

    @Override
    protected WxUser getByIdImpl(WxUserId id) {

        WxUser wxUser = getById(id);

        fillOtherAttr(wxUser);

        return wxUser;
    }

    @Override
    public WxUser getByUnionId(@NonNull WxUnionId unionId) {

        LambdaQueryWrapper<WxUserDo> queryWrapper = new LambdaQueryWrapper<WxUserDo>()
                .eq(WxUserDo::getUnionId, unionId.getValue());

        WxUserDo wxUserDo = getOne(queryWrapper);

        WxUser wxUser = dataConverter.toAgg(wxUserDo);

        fillOtherAttr(wxUser);

        saveSnapshot(dataConverter.copyAgg(wxUser));

        return wxUser;
    }

    @Override
    public WxUser getByAccountId(AccountId accountId) {

        LambdaQueryWrapper<WxUserDo> queryWrapper = new LambdaQueryWrapper<WxUserDo>()
                .eq(WxUserDo::getCreateId, accountId.getValue());

        WxUserDo wxUserDo = getOne(queryWrapper);

        WxUser wxUser = dataConverter.toAgg(wxUserDo);

        fillOtherAttr(wxUser);

        saveSnapshot(dataConverter.copyAgg(wxUser));

        return wxUser;
    }

    protected void fillOtherAttr(WxUser wxUser) {

        if (wxUser == null) {
            return;
        }

        WxTaskUserDo taskUserDo = taskUserRepository.getByCreateId(wxUser.getId().getValue());
        if (taskUserDo != null) {
            wxUser.setWxTaskUserOpenId(new WxTaskUserOpenId(taskUserDo.getOpenId()));
        }

        WxLoveUserDo loveUserDo = loveUserRepository.getByCreateId(wxUser.getId().getValue());
        if (loveUserDo != null) {
            wxUser.setWxLoveUserOpenId(new WxLoveUserOpenId(loveUserDo.getOpenId()));
        }

        String fullPath = getFullPath(wxUser.getId());
        File file = new File(fullPath);
        if (file.exists()) {
            try (FileInputStream input = new FileInputStream(file)) {
                wxUser.setAvatar(new WxUserAvatar(input));

            } catch (IOException e) {
                log.error("read wxAvatar error, wxUserId : {}, fullPath : {}", wxUser.getId(), fullPath, e);
            }
        }
    }

    protected void saveAvatar(WxUser wxUser) {
        if (wxUser.getAvatar() != null) {
            String fullPath = getFullPath(wxUser.getId());

            File file = new File(fullPath);
            try {
                FileUtils.touch(file);

                try (FileOutputStream outputStream = new FileOutputStream(fullPath)) {
                    wxUser.getAvatar().write(outputStream);
                }
            } catch (IOException e) {
                log.error("save wxAvatar error, wxUserId : {}, path : {}", wxUser.getId(), fullPath, e);
            }
        }
    }

    protected String getFullPath(WxUserId id) {
        return fileConfig.getImageDirectory() + id.takeAbsolutePath();
    }
}
