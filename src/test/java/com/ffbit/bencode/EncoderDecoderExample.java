package com.ffbit.bencode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;

public class EncoderDecoderExample {

    public static void main(String... args) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BEncoder encoder = new BEncoder(out);

        List<Object> data = asList(1, "foo", asList("1", 42),
                singletonMap("key", asList("foo", singletonMap("a", 42))));

        for (Object datum : data) {
            encoder.encode(datum);
        }

        InputStream in = new ByteArrayInputStream(out.toByteArray());
        BDecoder decoder = new BDecoder(in);

        for (Object e : decoder) {
            System.out.println(e);
        }
    }

}
