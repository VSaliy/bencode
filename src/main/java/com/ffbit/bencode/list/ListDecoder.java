package com.ffbit.bencode.list;

import com.ffbit.bencode.BDecoder;
import com.ffbit.bencode.Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListDecoder implements Decoder<List> {
    private final char PREFIX = 'l';
    private final char SUFFIX = 'e';
    private BDecoder parent;
    private InputStream in;
    private char current;

    public ListDecoder(BDecoder parent) {
        this.parent = parent;
    }

    public ListDecoder(InputStream in, BDecoder parent) {
        this(parent);
        this.in = in;
    }

    @Override
    public boolean isApplicable(int b) {
        return b == PREFIX;
    }

    @Override
    public List<?> decode() throws IOException {
        checkPrefix();
        List<Object> content = readContent();
        checkSuffix();
        return content;
    }

    private void checkPrefix() throws IOException {
        if (read() != PREFIX) {
            throw new ListDecoderException(
                    "Unexpected beginning of list <" + current + ">, expected <" + PREFIX + ">");
        }

    }

    private char read() throws IOException {
        current = (char) in.read();
        return current;
    }

    private List<Object> readContent() throws IOException {
        List<Object> content = new ArrayList<Object>();

        in.mark(1);
        while (read() != SUFFIX) {
            in.reset();
            Object element = parent.decode();
            content.add(element);
            in.mark(1);
        }
        in.reset();

        return content;
    }

    private void checkSuffix() throws IOException {
        if (read() != SUFFIX) {
            throw new ListDecoderException(
                    "Unexpected end of list <" + current + ">, expected <" + SUFFIX + ">");
        }
    }

}
