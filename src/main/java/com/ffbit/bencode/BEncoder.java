package com.ffbit.bencode;

import com.ffbit.bencode.dictionary.DictionaryEncoder;
import com.ffbit.bencode.integer.IntegerEncoder;
import com.ffbit.bencode.list.ListEncoder;
import com.ffbit.bencode.string.StringEncoder;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class BEncoder {
    private Encoder stringEncoder;
    private Encoder integerEncoder;
    private Encoder dictionaryEncoder;
    private Encoder listEncoder;


    public BEncoder(OutputStream out) {
        this(out, Decoder.DEFAULT_CHARSET);
    }

    public BEncoder(OutputStream out, Charset charset) {
        stringEncoder = new StringEncoder(out, charset);
        integerEncoder = new IntegerEncoder(out);
        dictionaryEncoder = new DictionaryEncoder(this, out);
        listEncoder = new ListEncoder(this, out);
    }

    public String encode(Object... inputs) {
        for (Object e : inputs) {
            if (e == null) {
                throw new BEncoderException("A null value occurred");
            }

            if (e instanceof String) {
                stringEncoder.encode(e);
            } else if (e instanceof Integer) {
                integerEncoder.encode(e);
            } else if (e instanceof List) {
                listEncoder.encode(e);
            } else if (e instanceof Map) {
                dictionaryEncoder.encode(e);
            } else {
                throw new BEncoderException("An unsupported data type occurred "
                        + e.getClass());
            }
        }

        return "";
    }

}
