package com.ffbit.bencode;

import java.util.ArrayList;
import java.util.List;


public class BDecoder {
    private StringDecoder stringDecoder;
    private IntegerDecoder integerDecoder;

    private StringEncoder stringEncoder;
    private IntegerEncoder integerEncoder;

    public BDecoder() {
        stringDecoder = new StringDecoder();
        integerDecoder = new IntegerDecoder();

        stringEncoder = new StringEncoder();
        integerEncoder = new IntegerEncoder();
    }

    public List<Object> decode(String input) {
        List<Object> output = new ArrayList<Object>();

        String remainder = input;

        while (!remainder.isEmpty()) {
            String readTerm;
            Object readObject;

            if (remainder.startsWith(IntegerEncoder.PREFIX)) {
                Integer readInt = integerDecoder.decode(remainder);
                readObject = readInt;
                readTerm = integerEncoder.encode(readInt);
            } else if (Character.isDigit(remainder.charAt(0))) {
                String readString = stringDecoder.decode(remainder);
                readObject = readString;
                readTerm = stringEncoder.encode(readString);
            } else {
                throw new RuntimeException(remainder);
            }

            output.add(readObject);
            remainder = remainder.substring(readTerm.length());
        }


        return output;
    }

}
