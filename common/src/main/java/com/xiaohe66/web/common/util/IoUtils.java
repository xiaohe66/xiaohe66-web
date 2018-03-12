package com.xiaohe66.web.common.util;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author xiaohe
 * @time 18-02-04 004
 */
public class IoUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(IoUtils.class);

    public static String readStringWithInput(InputStream inputStream){
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try{
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bufferedReader,reader);
        }
        return null;
    }

    public static void close(Closeable... closeables){
        if(closeables != null){
            for (Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    throw new XhException(CodeEnum.EXCEPTION, e);
                }
            }
        }
    }

}
