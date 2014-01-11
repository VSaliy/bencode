package com.ffbit.bencode;

public class BEncoder {
    private StringEncoder stringEncoder;
    private IntegerEncoder integerEncoder;

    public BEncoder() {
        stringEncoder = new StringEncoder();
        integerEncoder = new IntegerEncoder();
    }

    public String encode(Object... inputs) {
        StringBuilder output = new StringBuilder();

        for (Object e : inputs) {
            if (e instanceof String) {
                output.append(stringEncoder.encode((String) e));
            } else {
                output.append(integerEncoder.encode((Integer) e));
            }
        }

        return output.toString();
    }

}
