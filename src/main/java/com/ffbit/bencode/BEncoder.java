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

    public String encode(Object input) {
//        for (Object input : inputs) {
            if (input == null) {
                throw new BEncoderException("A null value occurred");
            }

            if (input instanceof String) {
                stringEncoder.encode(input);
            } else if (input instanceof Integer) {
                integerEncoder.encode(input);
            } else if (input instanceof List) {
                listEncoder.encode(input);
            } else if (input instanceof Map) {
                dictionaryEncoder.encode(input);
            } else {
                throw new BEncoderException("An unsupported data type occurred "
                        + input.getClass());
            }
//        }

        return "";
    }

}
