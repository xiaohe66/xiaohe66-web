package com.xiaohe66.web.test.base.copyprop.model;

import com.xiaohe66.web.base.base.BasePo;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaohe
 * @time 2020.01.02 10:11
 */
@Data
public class HotelElong extends BasePo {

    private int hotelId;
    private String hotelName;
    private Room room;
    private String self2;
    private Date currentDate;

    public HotelElong(){}
    public HotelElong(int hotelId, String hotelName, Room roomElong){
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.room = roomElong;
    }
}
