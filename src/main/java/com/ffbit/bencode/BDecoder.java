package com.ffbit.bencode;

import com.ffbit.bencode.dictionary.DictionaryDecoder;
import com.ffbit.bencode.integer.IntegerDecoder;
import com.ffbit.bencode.list.ListDecoder;
import com.ffbit.bencode.string.StringDecoder;

import java.io.IOException;
import java.io.InputStream;

public class BDecoder {

    private InputStream in;
    private StringDecoder stringDecoder;

    private IntegerDecoder integerDecoder;

    private ListDecoder listDecoder;
    private DictionaryDecoder dictionaryDecoder;

    public BDecoder(InputStream in) {
        this.in = in;
        listDecoder = new ListDecoder(in, this);
        dictionaryDecoder = new DictionaryDecoder(in, this);
        integerDecoder = new IntegerDecoder(in);
        stringDecoder = new StringDecoder(in);
    }

    public Object decode() throws IOException {
        in.mark(1);
        int i = in.read();

        if (listDecoder.isApplicable(i)) {
            in.reset();
            return listDecoder.decode();
        } else if (dictionaryDecoder.isApplicable(i)) {
            in.reset();
            return dictionaryDecoder.decode();
        } else if (integerDecoder.isApplicable(i)) {
            in.reset();
            return integerDecoder.decode();
        } else if (stringDecoder.isApplicable(i)) {
            in.reset();
            return stringDecoder.decode();
        }

        throw new IllegalArgumentException("bad byte " + (char) i);
    }

}
