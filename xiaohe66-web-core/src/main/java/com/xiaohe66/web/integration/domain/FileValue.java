package com.xiaohe66.web.integration.domain;

import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author xiaohe
 * @since 2021.12.10 22:17
 */
public class FileValue implements ValueObject {

    @Getter
    private final Md5 md5;

    private final byte[] bytes;

    public FileValue(InputStream inputStream) throws IOException {
        // TODO : 处理大文件问题
        this.bytes = inputStream.readAllBytes();
        md5 = new Md5(DigestUtils.md5Hex(bytes));
    }

    public void write(OutputStream outputStream) throws IOException {
        outputStream.write(bytes);
    }

    public int size(){
        return bytes.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileValue fileValue = (FileValue) o;

        return bytes.length == fileValue.bytes.length && md5.equals(fileValue.md5);
    }

    @Override
    public int hashCode() {
        return md5.hashCode();
    }

    @Override
    public String toString() {
        return "FileValue{" +
                "md5='" + md5 + '\'' +
                ",length='" + bytes.length + '\'' +
                '}';
    }
}
