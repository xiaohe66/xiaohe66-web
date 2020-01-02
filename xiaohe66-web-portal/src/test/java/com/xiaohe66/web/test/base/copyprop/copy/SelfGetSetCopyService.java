package com.xiaohe66.web.test.base.copyprop.copy;

import com.xiaohe66.web.test.base.copyprop.model.HotelCtripDto;
import com.xiaohe66.web.test.base.copyprop.model.HotelElong;
import com.xiaohe66.web.test.base.copyprop.model.Room;

/**
 * @author xiaohe
 * @time 2020.01.02 10:12
 */
public class SelfGetSetCopyService extends AbstractCopyService {

    @Override
    public void copy(Object dest1, Object orig1) {
        HotelCtripDto dest = (HotelCtripDto) dest1;
        HotelElong orig = (HotelElong) orig1;
        dest.setHotelId(orig.getHotelId());
        dest.setHotelName(orig.getHotelName());
        Room room = new Room();
        room.setRoomId(orig.getRoom().getRoomId());
        room.setRoomName(orig.getRoom().getRoomName());
        dest.setRoom(room);
    }

}
