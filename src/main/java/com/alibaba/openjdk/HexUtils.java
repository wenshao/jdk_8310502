package com.alibaba.openjdk;

import com.alibaba.fastjson2.util.JDKUtils;

import java.nio.ByteOrder;

import static com.alibaba.fastjson2.util.JDKUtils.LATIN1;
import static com.alibaba.fastjson2.util.JDKUtils.UTF16;

public class HexUtils {
    static final boolean COMPACT_STRINGS = true;
    public static String toHexString(int i) {
        char[] hex256 = UUIDUtils.HEX256;

        int i0 = (i >> 24) & 0xff;
        int i1 = (i >> 16) & 0xff;
        int i2 = (i >> 8) & 0xff;
        int i3 = i & 0xff;

        char c0 = hex256[i0];
        char c1 = hex256[i1];
        char c2 = hex256[i2];
        char c3 = hex256[i3];

        final byte coder;
        final int charSize;
        final int off;
        if (COMPACT_STRINGS) {
            coder = LATIN1;
            charSize = 1;
            off = 0;
        } else {
            coder = UTF16;
            charSize = 2;
            off = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? 1 : 0;
        }

        byte[] bytes;
        if ((i >> 4) == 0) {
            bytes = new byte[1 * charSize];
            bytes[0 * charSize + off] = (byte) c3;
        } else if ((i >> 8) == 0) {
            bytes = new byte[2 * charSize];
            bytes[0 * charSize + off] = (byte) (c3 >> 8);
            bytes[1 * charSize + off] = (byte) c3;
        } else if ((i >> 12) == 0) {
            bytes = new byte[3 * charSize];
            bytes[0 * charSize + off] = (byte) c2;
            bytes[1 * charSize + off] = (byte) (c3 >> 8);
            bytes[2 * charSize + off] = (byte) c3;
        } else if ((i >> 16) == 0) {
            bytes = new byte[4 * charSize];
            bytes[0 * charSize + off] = (byte) (c2 >> 8);
            bytes[1 * charSize + off] = (byte) c2;
            bytes[2 * charSize + off] = (byte) (c3 >> 8);
            bytes[3 * charSize + off] = (byte) c3;
        } else if ((i >> 20) == 0) {
            bytes = new byte[5 * charSize];
            bytes[0 * charSize + off] = (byte) c1;
            bytes[1 * charSize + off] = (byte) (c2 >> 8);
            bytes[2 * charSize + off] = (byte) c2;
            bytes[3 * charSize + off] = (byte) (c3 >> 8);
            bytes[4 * charSize + off] = (byte) c3;
        } else if ((i >> 24) == 0) {
            bytes = new byte[6 * charSize];
            bytes[0 * charSize + off] = (byte) (c1 >> 8);
            bytes[1 * charSize + off] = (byte) c1;
            bytes[2 * charSize + off] = (byte) (c2 >> 8);
            bytes[3 * charSize + off] = (byte) c2;
            bytes[4 * charSize + off] = (byte) (c3 >> 8);
            bytes[5 * charSize + off] = (byte) c3;
        } else if ((i >> 28) == 0) {
            bytes = new byte[7 * charSize];
            bytes[0 * charSize + off] = (byte) c0;
            bytes[1 * charSize + off] = (byte) (c1 >> 8);
            bytes[2 * charSize + off] = (byte) c1;
            bytes[3 * charSize + off] = (byte) (c2 >> 8);
            bytes[4 * charSize + off] = (byte) c2;
            bytes[5 * charSize + off] = (byte) (c3 >> 8);
            bytes[6 * charSize + off] = (byte) c3;
        } else {
            bytes = new byte[8 * charSize];
            bytes[0 * charSize + off] = (byte) (c0 >> 8);
            bytes[1 * charSize + off] = (byte) c0;
            bytes[2 * charSize + off] = (byte) (c1 >> 8);
            bytes[3 * charSize + off] = (byte) c1;
            bytes[4 * charSize + off] = (byte) (c2 >> 8);
            bytes[5 * charSize + off] = (byte) c2;
            bytes[6 * charSize + off] = (byte) (c3 >> 8);
            bytes[7 * charSize + off] = (byte) c3;
        }

        return JDKUtils.STRING_CREATOR_JDK11.apply(bytes, coder);
    }
}
