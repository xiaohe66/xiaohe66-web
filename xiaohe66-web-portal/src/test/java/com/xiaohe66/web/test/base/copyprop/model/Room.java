package com.xiaohe66.web.test.base.copyprop.model;

import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;

/**
 * @author xiaohe
 * @time 2020.01.02 10:11
 */
@Data
public class Room extends BasePo {
    private int roomId;
    private String roomName;

    public Room(){}
    public Room(int roomId, String roomName){
        this.roomId = roomId;
        this.roomName = roomName;
    }
}
