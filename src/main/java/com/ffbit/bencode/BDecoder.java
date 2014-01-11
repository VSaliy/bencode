package com.ffbit.bencode;

import java.util.ArrayList;
import java.util.List;

public class BDecoder {

    private StringDecoder stringDecoder;
    private StringEncoder stringEncoder;

    private IntegerDecoder integerDecoder;
    private IntegerEncoder integerEncoder;

    private ListDecoder listDecoder;
    private ListEncoder listEncoder;

    public BDecoder() {
        stringDecoder = new StringDecoder();
        stringEncoder = new StringEncoder();

        integerDecoder = new IntegerDecoder();
        integerEncoder = new IntegerEncoder();

        listDecoder = new ListDecoder(this);
        listEncoder = new ListEncoder(new BEncoder());
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
            } else if (remainder.startsWith(ListEncoder.PREFIX)) {
                List<?> readList = listDecoder.decode(remainder);
                readObject = readList;
                readTerm = listEncoder.encode(readList);
            } else {
                throw new RuntimeException(remainder);
            }

            output.add(readObject);
            remainder = remainder.substring(readTerm.length());
        }

        return output;
    }

}
