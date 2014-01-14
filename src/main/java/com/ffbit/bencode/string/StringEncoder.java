package com.ffbit.bencode.string;

import com.ffbit.bencode.Encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class StringEncoder implements Encoder<String> {
    public static final char SEPARATOR = ':';

    private OutputStream out;
    private Charset charset;

    public StringEncoder() {
    }

    public StringEncoder(OutputStream out, Charset charset) {
        this.out = out;
        this.charset = charset;
    }

    @Override
    public boolean isApplicable(Object value) {
        return value instanceof String;
    }

    @Override
    public String encode(String input) {
        if (charset == null) {
            return String.valueOf(input.length()) + SEPARATOR + input;
        }

        byte[] content = input.getBytes(charset);
        String prefix = String.valueOf(content.length) + SEPARATOR;

        try {
            out.write(prefix.getBytes(charset));
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
