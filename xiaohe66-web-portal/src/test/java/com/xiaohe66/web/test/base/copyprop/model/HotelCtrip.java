package com.xiaohe66.web.test.base.copyprop.model;

import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;

/**
 * @author xiaohe
 * @time 2020.01.02 10:10
 */
@Data
public class HotelCtrip extends BasePo {

    private int hotelId;
    private String hotelName;
    private Room room;

    public HotelCtrip() {
    }

    public HotelCtrip(int hotelId, String hotelName, Room room) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.room = room;
    }
}
