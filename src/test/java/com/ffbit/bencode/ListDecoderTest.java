package com.ffbit.bencode;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListDecoderTest {

    @Test
    public void itShouldDecodeEmptyList() throws Exception {
        Decoder decoder = new ListDecoder();

        assertThat(decoder.decode("le"), is((Object) new ArrayList<Object>()));
    }

}
