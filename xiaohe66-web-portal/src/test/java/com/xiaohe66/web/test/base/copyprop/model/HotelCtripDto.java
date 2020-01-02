package com.xiaohe66.web.test.base.copyprop.model;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;

/**
 * @author xiaohe
 * @time 2020.01.02 10:10
 */
@Data
public class HotelCtripDto extends BaseDto {

    private int hotelId;
    private String hotelName;
    private Room room;
    private String currentDate;

    public HotelCtripDto(){
    }

    public HotelCtripDto(int hotelId, String hotelName, Room room){
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.room = room;
    }
}
