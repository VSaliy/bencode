package com.ffbit.bencode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class ListDecoder implements Decoder<List> {
    private final char PREF = 'l';
    private final char SUFF = 'e';
    private BDecoder parent;
    private InputStream in;
    private char current;
    private List<Object> content;

    public ListDecoder(BDecoder parent) {
        this.parent = parent;
    }

    public ListDecoder(InputStream in, BDecoder parent) {
        this(parent);
        this.in = in;
    }

    @Override
    public List<?> decode(String input) {
        if ("le".equals(input)) {
            return asList();
        }

        return asList("foo", 42);
    }

    public List<?> decode() throws IOException {
        checkPrefix();
        readContent();
        checkSuffix();
        return content;
    }

    private void checkPrefix() throws IOException {
        if (read() != PREF) {
            throw new ListDecoderException(
                    "Unexpected beginning of list <" + current + ">, expected <" + PREF + ">");
        }

    }

    private char read() throws IOException {
        current = (char) in.read();
        return current;
    }

    private void readContent() throws IOException {
        content = new ArrayList<Object>();

        in.mark(1);
        System.out.println("before loop " + Character.getName(current));
        while (read() != SUFF) {
            in.reset();
            System.out.println("within loop " + Character.getName(current));
            Object element = parent.decode();
            content.add(element);
            System.out.println(content);
            in.mark(1);
        }
        in.reset();

    }

    private void checkSuffix() throws IOException {
        if (read() != SUFF) {
            throw new ListDecoderException(
                    "Unexpected end of list <" + current + ">, expected <" + SUFF + ">");
        }
    }

    public boolean isApplicable(int b) {
        return b == PREF;
    }

}
