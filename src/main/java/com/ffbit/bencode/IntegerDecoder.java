package com.ffbit.bencode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ffbit.bencode.IntegerEncoder.PREFIX;
import static com.ffbit.bencode.IntegerEncoder.SUFFIX;

public class IntegerDecoder implements Decoder<Integer> {

    @Override
    public Integer decode(String input) {
        Pattern pattern = Pattern.compile("(?<=" + PREFIX + ")-?\\d+(?=" + SUFFIX + ")");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new IntegerDecoderException("Could not decode an integer from value <" + input + ">");
        }

        String intString = matcher.group();

        return Integer.valueOf(intString);
    }

}
