package com.ffbit.bencode.string;

import com.ffbit.bencode.Decoder;

import java.io.IOException;
import java.io.InputStream;

public class StringDecoder implements Decoder<String> {
    private final char SEPARATOR = ':';

    private InputStream in;
    private char current;
    private int length;

    public StringDecoder(InputStream in) {
        this.in = in;
    }

    @Override
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
        if (current != SEPARATOR) {
            throw new StringDecoderException(
                    "Unexpected separator of string <" + current + ">, expected <" + SEPARATOR + ">");
        }
    }

    public boolean isApplicable(int b) {
        return Character.isDigit(b);
    }

}
