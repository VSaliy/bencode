package com.ffbit.bencode.list;

import com.ffbit.bencode.BDecoder;
import com.ffbit.bencode.Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListDecoder implements Decoder<List> {
    private final char PREF = 'l';
    private final char SUFF = 'e';
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
    public List<?> decode() throws IOException {
        checkPrefix();
        List<Object> content = readContent();
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

    private List<Object> readContent() throws IOException {
        List<Object> content = new ArrayList<Object>();

        in.mark(1);
        while (read() != SUFF) {
            in.reset();
            Object element = parent.decode();
            content.add(element);
            in.mark(1);
        }
        in.reset();

        return content;
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
