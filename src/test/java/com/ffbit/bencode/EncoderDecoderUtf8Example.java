package com.ffbit.bencode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;

public class EncoderDecoderUtf8Example {

    public static void main(String... args) throws Exception {
        Charset charset = Charset.forName("UTF-8");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BEncoder encoder = new BEncoder(out, charset);

        List<Object> data = asList(1, "слово", asList("один", 42),
                singletonMap("ключ", asList("значение 1", singletonMap("ключ 2", 42))));

        for (Object datum : data) {
            encoder.encode(datum);
        }

        InputStream in = new ByteArrayInputStream(out.toByteArray());
        BDecoder decoder = new BDecoder(in, charset);

        while (decoder.hasNext()) {
            System.out.println(decoder.next());
        }
    }

}
