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

    private static final int BUFF_BYTE_SIZE = 2048;

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

    public static void createFile(File file){
        if (!file.exists()) {
            LOGGER.debug("文件不存在，创建文件:"+file.getPath());
            createDirectory(file.getParentFile());
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new XhException(CodeEnum.IO_EXCEPTION,e);
            }
        }
    }

    public static void createDirectory(File directory){
        if(!directory.exists()){
            directory.mkdirs();
        }
    }

    /**
     * 写入数据到文件
     * @param inputStream   输入流
     * @param file          目标文件
     * @param append        是否在文件末尾定稿，传入true时，在文件的末尾写入。传入false时会覆盖旧文件。
     */
    public static void writeToFile(InputStream inputStream,File file,boolean append){
        Check.notEmptyCheck(inputStream,file);
        LOGGER.debug("向路径<"+file.getPath()+">写入数据");
        createFile(file);

        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file,append);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] bytes = new byte[BUFF_BYTE_SIZE];
            int len;
            while ((len = (inputStream.read(bytes)))>0){
                bufferedOutputStream.write(bytes,0,len);
            }
        } catch (IOException e){
            throw new XhException(CodeEnum.IO_EXCEPTION,e);
        }finally {
            close(bufferedOutputStream,fileOutputStream);
        }

    }

    public static void writeToFile(File file1,File file2,boolean append) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file1);
            writeToFile(fileInputStream,file2,append);
        } catch (FileNotFoundException e) {
            throw new XhException(CodeEnum.RUNTIME_EXCEPTION,e);
        } finally {
            close(fileInputStream);
        }
    }

    public static void writeToOutput(File file,OutputStream outputStream){
        Check.notEmptyCheck(file,outputStream);

        FileInputStream fileInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            byte[] bytes = new byte[BUFF_BYTE_SIZE];
            int len;
            while ((len = (fileInputStream.read(bytes)))>0){
                bufferedOutputStream.write(bytes,0,len);
            }
        } catch (IOException e){
            throw new XhException(CodeEnum.IO_EXCEPTION,e);
        }finally {
            close(bufferedOutputStream,fileInputStream);
        }
    }

    /**
     * 删除一个文件或文件夹
     * <p>传入的如果是一个文件，则直接删除。
     * 传入的如果是一个文件夹，则先删除文件夹里面所有的文件和文件夹，再删除该文件夹。
     *
     * @param path  待删除的文件或文件夹路径
     * @throws NullPointerException if path is null.
     */
    public static void delete(String path){
        delete(new File(path));
    }

    /**
     * 删除一个文件或文件夹
     * <p>传入的如果是一个文件，则直接删除。
     * 传入的如果是一个文件夹，则先删除文件夹里面所有的文件和文件夹，再删除该文件夹。
     *
     * <p>该方法是由递归调用实现的
     *
     * @param file  待删除的文件或文件夹file
     * @throws NullPointerException if file is null.
     */
    public static void delete(File file){
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if(files!=null){
                for (File file1 : files) {
                    delete(file1);
                }
            }
        }
        file.delete();
    }

    public static void close(Closeable... closeables){
        if(closeables != null){
            for (Closeable closeable : closeables) {
                if(closeable == null){
                    continue;
                }
                try {
                    closeable.close();
                } catch (IOException e) {
                    throw new XhException(CodeEnum.EXCEPTION, e);
                }
            }
        }
    }

}
