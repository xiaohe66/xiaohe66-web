package com.xiaohe66.web.test.base.copyprop;

import com.google.gson.Gson;
import com.xiaohe66.web.test.base.copyprop.copy.AbstractCopyService;
import com.xiaohe66.web.test.base.copyprop.copy.CglibBeanCopyService;
import com.xiaohe66.web.test.base.copyprop.copy.SpringBeanUtilsCopyService;
import com.xiaohe66.web.test.base.copyprop.copy.XhClassUtilsCopyService;
import com.xiaohe66.web.test.base.copyprop.model.HotelCtripDto;
import com.xiaohe66.web.test.base.copyprop.model.HotelElong;
import com.xiaohe66.web.test.base.copyprop.model.Room;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xiaohe
 * @time 2020.01.02 10:09
 */
public class CopyPropTest {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        Room room = new Room(1, "Standard Room");

        HotelElong hotelElong = new HotelElong(10001, "BeijingHotel", room);
        hotelElong.setSelf2("abc");
        hotelElong.setId(1111111);
        hotelElong.setCurrentDate(new Date());

        HotelCtripDto hotelCtrip = new HotelCtripDto();

        int times = 1;
        List<AbstractCopyService> abstractCopyServiceList =
                Arrays.asList(
//                        new ApacheBeanUtilsCopyService(),
//                        new SelfReflectCopyService(),
                        new SpringBeanUtilsCopyService(),
                        new CglibBeanCopyService(),
//                        new SelfGetSetCopyService(),
                        new XhClassUtilsCopyService());
        for (AbstractCopyService copyService : abstractCopyServiceList) {
            copyService.calcExecTime(hotelCtrip, hotelElong, times);
//            System.out.println(gson.toJson(hotelCtrip));
//            System.out.println("=====================================================================================");
        }
    }

}