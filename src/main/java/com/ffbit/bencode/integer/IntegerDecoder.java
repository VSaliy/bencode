package com.ffbit.bencode.integer;

import com.ffbit.bencode.Decoder;

import java.io.IOException;
import java.io.InputStream;

public class IntegerDecoder implements Decoder<Integer> {
    private final char PREF = 'i';
    private final char SUFF = 'e';
    private final char MINUS = '-';
    private final char PLUS = '+';

    private InputStream in;
    private StringBuilder sb;
    private char current;

    public IntegerDecoder(InputStream in) {
        this.in = in;
        sb = new StringBuilder();
    }

    @Override
    public Integer decode() throws IOException {
        checkPrefix();
        readSign();
        readDigits();
        checkSuffix();
        return toInteger();
    }

    private void checkPrefix() throws IOException {
        if (read() != PREF) {
            throw new IntegerDecoderException(
                    "Unexpected beginning of integer <" + current + ">, expected <" + PREF + ">");
        }
    }

    private char read() throws IOException {
        current = (char) in.read();
        return current;
    }

    private void readSign() throws IOException {
        if (read() == MINUS) {
            sb.append(current);
            read();
        } else {
            sb.append(PLUS);
        }
    }

    private void readDigits() throws IOException {
        while (Character.isDigit(current)) {
            sb.append(current);
            read();
        }
    }

    private void checkSuffix() throws IOException {
        if (current != SUFF) {
            throw new IntegerDecoderException(
                    "Unexpected end of integer <" + current + ">, expected <" + SUFF + ">");
        }
    }

    private Integer toInteger() {
        if (sb.length() == 1) {
            throw new IntegerDecoderException("An empty integer occurred");
        }

        String numString = sb.toString();
        clear();

        return Integer.valueOf(numString);
    }

    private void clear() {
        sb.setLength(0);
    }

    public boolean isApplicable(int b) {
        return b == PREF;
    }

}
