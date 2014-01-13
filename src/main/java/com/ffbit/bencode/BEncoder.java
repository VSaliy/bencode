package com.ffbit.bencode;

import com.ffbit.bencode.dictionary.DictionaryEncoder;
import com.ffbit.bencode.integer.IntegerEncoder;
import com.ffbit.bencode.list.ListEncoder;
import com.ffbit.bencode.string.StringEncoder;

import java.util.List;
import java.util.Map;

public class BEncoder {
    private StringEncoder stringEncoder;
    private IntegerEncoder integerEncoder;
    private DictionaryEncoder dictionaryEncoder;
    private ListEncoder listEncoder;

    public BEncoder() {
        stringEncoder = new StringEncoder();
        integerEncoder = new IntegerEncoder();
        dictionaryEncoder = new DictionaryEncoder(this);
        listEncoder = new ListEncoder(this);
    }

    public String encode(Object... inputs) {
        StringBuilder output = new StringBuilder();

        for (Object e : inputs) {
            if (e == null) {
                throw new BEncoderException("A null value occurred");
            }

            if (e instanceof String) {
                output.append(stringEncoder.encode((String) e));
            } else if (e instanceof Integer) {
                output.append(integerEncoder.encode((Integer) e));
            } else if (e instanceof List) {
                output.append(listEncoder.encode((List) e));
            } else if (e instanceof Map) {
                output.append(dictionaryEncoder.encode((Map) e));
            } else {
                throw new BEncoderException("An unsupported data type occurred "
                        + e.getClass());
            }
        }

        return output.toString();
    }

}
