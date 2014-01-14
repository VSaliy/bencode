package com.ffbit.bencode.list;

import com.ffbit.bencode.BEncoder;
import com.ffbit.bencode.Encoder;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListEncoderTest {
    private Encoder encoder;
    private BEncoder parent;
    private OutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        parent = new BEncoder(out);
        encoder = new ListEncoder(parent, out);
    }

    @Test
    public void itShouldEncodeEmptyList() throws Exception {
        encoder.encode(emptyList());

        assertThat(out.toString(), is("le"));
    }

    @Test
    public void itShouldEncodeListOfStringAndInteger() throws Exception {
        encoder.encode(asList("a", 1));

        assertThat(out.toString(), is("l1:ai1ee"));
    }

}
