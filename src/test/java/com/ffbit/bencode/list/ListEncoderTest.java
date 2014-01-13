package com.ffbit.bencode.list;

import com.ffbit.bencode.BEncoder;
import com.ffbit.bencode.Encoder;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListEncoderTest {

    private Encoder encoder;

    @Test
    public void itShouldEncodeEmptyList() throws Exception {
        encoder = new ListEncoder(new BEncoder());

        assertThat(encoder.encode(Collections.emptyList()), is("le"));
    }

    @Test
    public void itShouldEncodeListOfStrings() throws Exception {
        encoder = new ListEncoder(new BEncoder());

        assertThat(encoder.encode(Arrays.asList("a", "foo")), is("l1:a3:fooe"));
    }

}
