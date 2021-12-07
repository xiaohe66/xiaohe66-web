package com.xiaohe66.web.application.love.result;

import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.12.07 11:54
 */
@Data
public class MessageListResult {

    private Long id;
    private Boolean isMe;
    private String avatarUrl;
    private String nickname;
    private String createTime;
    private String text;
}
