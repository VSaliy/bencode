package com.ffbit.bencode;

import java.util.ArrayList;
import java.util.List;

public class ListDecoder implements Decoder<List> {

    @Override
    public List<?> decode(String input) {
        return new ArrayList<Object>();
    }

}
