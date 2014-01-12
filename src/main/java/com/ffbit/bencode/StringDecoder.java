package com.ffbit.bencode;

import java.io.IOException;
import java.io.InputStream;

public class StringDecoder implements Decoder<String> {
    private final char SEPARATOR = ':';

    private InputStream in;
    private char current;
    private int length;

    @Deprecated
    public StringDecoder() {
    }

    public StringDecoder(InputStream in) {
        this.in = in;
    }

    @Override
    public String decode(String input) {
        int separatorIndex = input.indexOf(SEPARATOR);
        int length = Integer.valueOf(input.substring(0, separatorIndex));
        String value = input.substring(separatorIndex + 1, separatorIndex + 1 + length);

        return value;
    }

    public String decode() throws IOException {
        readLength();
        checkSeparator();
        byte[] bytes = new byte[length];
        in.read(bytes);

        return new String(bytes);
    }

    private void readLength() throws IOException {
        StringBuilder sb = new StringBuilder();

        while (Character.isDigit(read())) {
            sb.append(current);
        }

        length = Integer.valueOf(sb.toString());
    }

    private char read() throws IOException {
        current = (char) in.read();
        return current;
    }

    private void checkSeparator() {
    }

}
