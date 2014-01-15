package com.ffbit.bencode.integer;

import com.ffbit.bencode.Encoder;

import java.io.IOException;
import java.io.OutputStream;

public class IntegerEncoder implements Encoder<Integer> {
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
    public void encode(Integer input) throws IOException {
        String result = String.valueOf(INTEGER_PREFIX) + input + END_SUFFIX;

        out.write(result.getBytes());
    }

}
