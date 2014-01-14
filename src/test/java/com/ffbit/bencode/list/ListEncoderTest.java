package com.ffbit.bencode.list;

import com.ffbit.bencode.BEncoder;
import com.ffbit.bencode.Encoder;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListEncoderTest {

    private Encoder encoder;
    private BEncoder parent;

    @Before
    public void setUp() throws Exception {
        parent = new BEncoder();
    }

    @Test
    public void itShouldEncodeEmptyListOld() throws Exception {
        encoder = new ListEncoder(parent);

        assertThat(encoder.encode(emptyList()), is("le"));
    }

    @Test
    public void itShouldEncodeListOfStrings() throws Exception {
        encoder = new ListEncoder(parent);

        assertThat(encoder.encode(Arrays.asList("a", "foo")), is("l1:a3:fooe"));
    }

    @Test
    public void itShouldEncodeEmptyList() throws Exception {
        OutputStream out = new ByteArrayOutputStream();
        Encoder encoder = new ListEncoder(parent, out);

        encoder.encode(emptyList());
        assertThat(out.toString(), is("le"));
    }

}
