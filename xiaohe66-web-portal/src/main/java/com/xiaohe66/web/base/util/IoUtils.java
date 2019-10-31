package com.xiaohe66.web.base.util;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhIoException;
import com.xiaohe66.web.base.exception.XhWebException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.DigestUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

/**
 * @author xiaohe
 * @time 18-02-04 004
 */
@Slf4j
public class IoUtils {

    private IoUtils() {
    }

    private static final int BUFF_BYTE_SIZE = 2048;

    public static String readStringWithInput(InputStream inputStream) throws XhIoException {
        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new XhIoException(e);
        }
    }

    /**
     * 读取classPath下的文件内容，返回字符串
     *
     * @param filePath 文件路径
     * @return 读取到的内容
     */
    public static String readStringInClassPath(String filePath) throws XhIoException {
        URL url = IoUtils.class.getClassLoader().getResource(filePath);
        if (url == null) {
            throw new XhIoException("资源不存在 : " + filePath);
        }
        try (InputStream inputStream = new FileInputStream(new File(url.toURI()))) {
            return readStringWithInput(inputStream);
        } catch (URISyntaxException | IOException e) {
            throw new XhIoException(e);
        }
    }

    /**
     * 读取jar包中的文件内容，返回字符串
     *
     * @param filePath 文件路径
     * @return 读取到的内容
     */
    public static String readStringInJar(String filePath) throws XhIoException {
        return readStringWithInput(IoUtils.class.getClassLoader().getResourceAsStream(filePath));
    }

    // todo : 线程安全
    public static void createFileIfNotExsit(File file) throws XhIoException {
        if (!file.exists()) {
            log.debug("文件不存在，创建文件:" + file.getPath());
            createDirectoryIfNotExist(file.getParentFile());
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                throw new XhIoException(e);
            }
        }
    }

    public static void createDirectoryIfNotExist(File directory) throws XhIoException {
        if (!directory.exists()) {
            try {
                FileUtils.forceMkdir(directory);
            } catch (IOException e) {
                throw new XhIoException("创建文件夹失败, 路径 : " + directory.getPath(), e);
            }
        }
    }

    /**
     * 写入数据到文件
     *
     * @param inputStream 输入流
     * @param file        目标文件
     * @param append      是否在文件末尾定稿，传入true时，在文件的末尾写入。传入false时会覆盖旧文件。
     */
    public static void writeToFile(InputStream inputStream, File file, boolean append) throws XhIoException {
        Objects.requireNonNull(inputStream);
        Objects.requireNonNull(file);

        log.debug("向路径<{}>写入数据", file.getPath());
        createFileIfNotExsit(file);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file, append);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);) {
            byte[] bytes = new byte[BUFF_BYTE_SIZE];
            int len;
            while ((len = (inputStream.read(bytes))) > 0) {
                bufferedOutputStream.write(bytes, 0, len);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            throw new XhIoException(e);
        }
    }

    public static void writeToFile(File readFile, File writeFile, boolean append) throws XhIoException {
        try (FileInputStream fileInputStream = new FileInputStream(readFile)) {
            writeToFile(fileInputStream, writeFile, append);
        } catch (IOException e) {
            throw new XhIoException(e);
        }
    }

    public static void writeToOutput(File file, OutputStream outputStream) throws XhIoException {
        Objects.requireNonNull(file);
        Objects.requireNonNull(outputStream);

        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);) {
            byte[] bytes = new byte[BUFF_BYTE_SIZE];
            int len;
            while ((len = (fileInputStream.read(bytes))) > 0) {
                bufferedOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            throw new XhIoException(e);
        }
    }

    /**
     * 删除一个文件或文件夹
     * <p>传入的如果是一个文件，则直接删除。
     * 传入的如果是一个文件夹，则先删除文件夹里面所有的文件和文件夹，再删除该文件夹。
     *
     * @param path 待删除的文件或文件夹路径
     * @throws NullPointerException if path is null.
     */
    public static void delete(String path) throws IOException {
        delete(new File(path));
    }

    /**
     * 删除一个文件或文件夹
     * <p>传入的如果是一个文件，则直接删除。
     * 传入的如果是一个文件夹，则先删除文件夹里面所有的文件和文件夹，再删除该文件夹。
     *
     * <p>该方法是由递归调用实现的
     *
     * @param file 待删除的文件或文件夹file
     * @throws NullPointerException if file is null.
     */
    public static void delete(File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    delete(file1);
                }
            }
        }
        boolean isSuccess = Files.deleteIfExists(file.toPath());
        if (!isSuccess) {
            // todo : to sth.
            log.debug("删除失败");
        }
    }

    public static String md5Sex(File file) throws XhIoException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return DigestUtils.md5DigestAsHex(inputStream);
        } catch (IOException e) {
            throw new XhIoException(e);
        }
    }

    public static void close(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable == null) {
                    continue;
                }
                try {
                    closeable.close();
                } catch (IOException e) {
                    throw new XhWebException(CodeEnum.EXCEPTION, e);
                }
            }
        }
    }

    public static String convertFriendlySize(long bytes) {
        final int unit = 1024;

        if (bytes < unit) {
            return "<1KB";
        }
        double kb = bytes * 1.0 / unit;
        if (kb < unit) {
            return String.format("%.2fKB", kb);
        }
        double mb = kb / unit;
        return String.format("%.2fMB", mb);
    }
}
