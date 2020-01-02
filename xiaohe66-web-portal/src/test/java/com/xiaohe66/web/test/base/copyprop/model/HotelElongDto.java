package com.xiaohe66.web.test.base.copyprop.model;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaohe
 * @time 2020.01.02 10:11
 */
@Data
public class HotelElongDto extends BaseDto {

    private int hotelId;
    private String hotelName;
    private Room room;
    private String self2;

    public HotelElongDto(){}
    public HotelElongDto(int hotelId, String hotelName, Room roomElong){
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.room = roomElong;
    }
}
