package com.ffbit.bencode.list;

import com.ffbit.bencode.BEncoder;
import com.ffbit.bencode.Encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ListEncoder implements Encoder<List> {
    public static final char PREFIX = 'l';
    public static final char SUFFIX = 'e';

    private BEncoder parent;
    private OutputStream out;

    public ListEncoder(BEncoder parent) {
        this.parent = parent;
    }

    public ListEncoder(BEncoder parent, OutputStream out) {
        this(parent);
        this.out = out;
    }

    @Override
    public boolean isApplicable(Object value) {
        return value instanceof List;
    }

    @Override
    public void encode(List input) {
        try {
            out.write(PREFIX);
            for (Object e : input) {
                parent.encode(e);
            }
            out.write(SUFFIX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
