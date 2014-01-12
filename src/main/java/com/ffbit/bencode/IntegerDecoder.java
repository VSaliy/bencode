package com.ffbit.bencode;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ffbit.bencode.IntegerEncoder.PREFIX;
import static com.ffbit.bencode.IntegerEncoder.SUFFIX;

public class IntegerDecoder implements Decoder<Integer> {
    private final char PREF = 'i';
    private final char SUFF = 'e';
    private final char MINUS = '-';

    @Deprecated
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

    public Integer decode(InputStream in) throws IOException {
        char current = (char) in.read();

        if (current != PREF) {
            throw new IntegerDecoderException(
                    "Unexpected beginning of integer <" + current + ">, expected <" + PREF + ">");
        }

        StringBuilder sb = new StringBuilder();

        current = (char) in.read();

        if (current == MINUS) {
            sb.append(current);
            current = (char) in.read();
        }

        do {
            sb.append(current);
            current = (char) in.read();
        } while (Character.isDigit(current));

        if (current != SUFF) {
            throw new IntegerDecoderException(
                    "Unexpected end of integer <" + current + ">, expected <" + SUFF + ">");
        }

        String numString = sb.toString();

        return Integer.valueOf(numString);
    }

}
