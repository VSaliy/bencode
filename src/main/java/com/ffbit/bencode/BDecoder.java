package com.ffbit.bencode;

import com.ffbit.bencode.dictionary.DictionaryDecoder;
import com.ffbit.bencode.integer.IntegerDecoder;
import com.ffbit.bencode.list.ListDecoder;
import com.ffbit.bencode.string.StringDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;

public class BDecoder implements Iterable<Object>, Iterator<Object> {
    private InputStream in;

    private Decoder stringDecoder;
    private Decoder integerDecoder;
    private Decoder listDecoder;
    private Decoder dictionaryDecoder;
    private int current = -1;

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
        current = in.read();

        Decoder decoder;

        if (listDecoder.isApplicable(current)) {
            decoder = listDecoder;
        } else if (dictionaryDecoder.isApplicable(current)) {
            decoder = dictionaryDecoder;
        } else if (integerDecoder.isApplicable(current)) {
            decoder = integerDecoder;
        } else if (stringDecoder.isApplicable(current)) {
            decoder = stringDecoder;
        } else {
            throw new BDecoderException("An unsupported symbol <" + (char) current + "> occurred");
        }

        in.reset();
        return decoder.decode();
    }

    @Override
    public Iterator<Object> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        try {
            in.mark(1);
            current = in.read();
            in.reset();

            return current != -1;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Object next() {
        try {
            return decode();
        } catch (IOException e) {
            throw new BDecoderException(e.getMessage(), e);
        }
    }

    @Override
    public void remove() {

    }

}
