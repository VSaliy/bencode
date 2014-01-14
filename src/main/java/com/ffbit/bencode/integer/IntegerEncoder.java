package com.ffbit.bencode.integer;

import com.ffbit.bencode.Encoder;

import java.io.IOException;
import java.io.OutputStream;

public class IntegerEncoder implements Encoder<Integer> {
    public static final String PREFIX = "i";
    public static final String SUFFIX = "e";

    private OutputStream out;

    public IntegerEncoder() {
    }

    public IntegerEncoder(OutputStream out) {
        this.out = out;
    }

    @Override
    public boolean isApplicable(Object value) {
        return value instanceof Integer;
    }

    @Override
    public String encode(Integer input) {
        String result = PREFIX + input + SUFFIX;

        if (out != null) {
            try {
                out.write(result.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
