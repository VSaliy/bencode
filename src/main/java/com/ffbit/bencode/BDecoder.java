package com.ffbit.bencode;

import com.ffbit.bencode.dictionary.DictionaryDecoder;
import com.ffbit.bencode.integer.IntegerDecoder;
import com.ffbit.bencode.list.ListDecoder;
import com.ffbit.bencode.string.StringDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class BDecoder {
    private InputStream in;

    private Decoder stringDecoder;
    private Decoder integerDecoder;
    private Decoder listDecoder;
    private Decoder dictionaryDecoder;

    public BDecoder(InputStream in) {
        this(in, Encoder.DEFAULT_CHARSET);
    }

    public BDecoder(InputStream in, Charset charset) {
        this.in = in;
        listDecoder = new ListDecoder(in, this);
        dictionaryDecoder = new DictionaryDecoder(in, this);
        integerDecoder = new IntegerDecoder(in);
        stringDecoder = new StringDecoder(in, charset);
    }

    public Object decode() throws IOException {
        in.mark(1);
        int i = in.read();

        Decoder decoder;

        if (listDecoder.isApplicable(i)) {
            decoder = listDecoder;
        } else if (dictionaryDecoder.isApplicable(i)) {
            decoder = dictionaryDecoder;
        } else if (integerDecoder.isApplicable(i)) {
            decoder = integerDecoder;
        } else if (stringDecoder.isApplicable(i)) {
            decoder = stringDecoder;
        } else {
            throw new BDecoderException("An unsupported symbol <" + (char) i + "> occurred");
        }

        in.reset();
        return decoder.decode();
    }

}
