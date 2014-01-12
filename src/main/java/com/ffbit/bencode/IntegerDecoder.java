package com.ffbit.bencode;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ffbit.bencode.IntegerEncoder.PREFIX;
import static com.ffbit.bencode.IntegerEncoder.SUFFIX;

public class IntegerDecoder implements Decoder<Integer> {
    private final char PREF = 'i';
    private final char SUFF = 'e';
    private final char MINUS = '-';
    private final char PLUS = '+';

    private InputStream in;
    private StringBuilder sb;
    private char current;

    @Deprecated
    public IntegerDecoder() {
    }

    public IntegerDecoder(InputStream in) {
        this.in = in;
        sb = new StringBuilder();
    }

    @Deprecated
    @Override
    public Integer decode(String input) {
        Pattern pattern = Pattern.compile("(?<=" + PREFIX + ")-?\\d+(?=" + SUFFIX + ")");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new IntegerDecoderException("Could not decode an integer from value <" + input + ">");
        }

        String intString = matcher.group();

        return Integer.valueOf(intString);
    }

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

        System.out.println("read an integer " + numString);

        return Integer.valueOf(numString);
    }

    private void clear() {
        sb.setLength(0);
    }

    public boolean isApplicable(byte b) {
        return b == PREF;
    }

}
