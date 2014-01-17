# Bencode

Java implementation of the [Bencode](http://en.wikipedia.org/wiki/Bencode) format.

[![Build Status](https://travis-ci.org/ffbit/bencode.png)](https://travis-ci.org/ffbit/bencode)
[![Coverage Status](https://coveralls.io/repos/ffbit/bencode/badge.png)](https://coveralls.io/r/ffbit/bencode)


## Usage examples

### US-ASCII

As the original algorithm specifies, default charset encoding is US-ASCII.
So, this charset is used by default.

See [EncoderDecoderExample.java](https://github.com/ffbit/bencode/blob/master/src/test/java/com/ffbit/bencode/EncoderDecoderExample.java).
````
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
````
Output:
````
1
foo
[1, 42]
{key=[foo, {a=42}]}
````

### Other charset encodings

If you need, for example Cyrillic alphabet's symbols support, you can use the UTF-8 charset.

See [EncoderDecoderUtf8Example.java](https://github.com/ffbit/bencode/blob/master/src/test/java/com/ffbit/bencode/EncoderDecoderUtf8Example.java).
````
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

for (Object e : decoder) {
    System.out.println(e);
}
````
Output:
````
1
слово
[один, 42]
{ключ=[значение 1, {ключ 2=42}]}
````

## Links

- [Bencode](http://en.wikipedia.org/wiki/Bencode)
- [Bencode Stream Parsing in Java](http://www.codecommit.com/blog/java/bencode-stream-parsing-in-java)
