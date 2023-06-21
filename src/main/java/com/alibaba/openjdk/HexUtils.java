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

    public static String toHexString(long i) {
        char[] hex256 = UUIDUtils.HEX256;

        int i0 = ((int) (i >> 56)) & 0xff;
        int i1 = ((int) (i >> 48)) & 0xff;
        int i2 = ((int) (i >> 40)) & 0xff;
        int i3 = ((int) (i >> 32)) & 0xff;
        int i4 = ((int) (i >> 24)) & 0xff;
        int i5 = ((int) (i >> 16)) & 0xff;
        int i6 = ((int) (i >> 8)) & 0xff;
        int i7 = ((int) i) & 0xff;

        char c0 = hex256[i0];
        char c1 = hex256[i1];
        char c2 = hex256[i2];
        char c3 = hex256[i3];
        char c4 = hex256[i4];
        char c5 = hex256[i5];
        char c6 = hex256[i6];
        char c7 = hex256[i7];

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
        } else if ((i >> 32) == 0) {
            bytes = new byte[8 * charSize];
            bytes[0 * charSize + off] = (byte) (c0 >> 8);
            bytes[1 * charSize + off] = (byte) c0;
            bytes[2 * charSize + off] = (byte) (c1 >> 8);
            bytes[3 * charSize + off] = (byte) c1;
            bytes[4 * charSize + off] = (byte) (c2 >> 8);
            bytes[5 * charSize + off] = (byte) c2;
            bytes[6 * charSize + off] = (byte) (c3 >> 8);
            bytes[7 * charSize + off] = (byte) c3;
        } else if ((i >> 36) == 0) {
            bytes = new byte[9 * charSize];
            bytes[0 * charSize + off] = (byte) c0;
            bytes[1 * charSize + off] = (byte) (c1 >> 8);
            bytes[2 * charSize + off] = (byte) c1;
            bytes[3 * charSize + off] = (byte) (c2 >> 8);
            bytes[4 * charSize + off] = (byte) c2;
            bytes[5 * charSize + off] = (byte) (c3 >> 8);
            bytes[6 * charSize + off] = (byte) c3;
            bytes[7 * charSize + off] = (byte) (c4 >> 8);
            bytes[8 * charSize + off] = (byte) c4;
        } else if ((i >> 40) == 0) {
            bytes = new byte[10 * charSize];
            bytes[0 * charSize + off] = (byte) (c0 >> 8);
            bytes[1 * charSize + off] = (byte) c0;
            bytes[2 * charSize + off] = (byte) (c1 >> 8);
            bytes[3 * charSize + off] = (byte) c1;
            bytes[4 * charSize + off] = (byte) (c2 >> 8);
            bytes[5 * charSize + off] = (byte) c2;
            bytes[6 * charSize + off] = (byte) (c3 >> 8);
            bytes[7 * charSize + off] = (byte) c3;
            bytes[8 * charSize + off] = (byte) (c4 >> 8);
            bytes[9 * charSize + off] = (byte) c4;
        } else if ((i >> 44) == 0) {
            bytes = new byte[11 * charSize];
            bytes[0 * charSize + off] = (byte) c0;
            bytes[1 * charSize + off] = (byte) (c1 >> 8);
            bytes[2 * charSize + off] = (byte) c1;
            bytes[3 * charSize + off] = (byte) (c2 >> 8);
            bytes[4 * charSize + off] = (byte) c2;
            bytes[5 * charSize + off] = (byte) (c3 >> 8);
            bytes[6 * charSize + off] = (byte) c3;
            bytes[7 * charSize + off] = (byte) (c4 >> 8);
            bytes[8 * charSize + off] = (byte) c4;
            bytes[9 * charSize + off] = (byte) (c5 >> 8);
            bytes[10 * charSize + off] = (byte) c5;
        } else if ((i >> 48) == 0) {
            bytes = new byte[12 * charSize];
            bytes[0 * charSize + off] = (byte) (c0 >> 8);
            bytes[1 * charSize + off] = (byte) c0;
            bytes[2 * charSize + off] = (byte) (c1 >> 8);
            bytes[3 * charSize + off] = (byte) c1;
            bytes[4 * charSize + off] = (byte) (c2 >> 8);
            bytes[5 * charSize + off] = (byte) c2;
            bytes[6 * charSize + off] = (byte) (c3 >> 8);
            bytes[7 * charSize + off] = (byte) c3;
            bytes[8 * charSize + off] = (byte) (c4 >> 8);
            bytes[9 * charSize + off] = (byte) c4;
            bytes[10 * charSize + off] = (byte) (c5 >> 8);
            bytes[11 * charSize + off] = (byte) c5;
        } else if ((i >> 52) == 0) {
            bytes = new byte[13 * charSize];
            bytes[0 * charSize + off] = (byte) c0;
            bytes[1 * charSize + off] = (byte) (c1 >> 8);
            bytes[2 * charSize + off] = (byte) c1;
            bytes[3 * charSize + off] = (byte) (c2 >> 8);
            bytes[4 * charSize + off] = (byte) c2;
            bytes[5 * charSize + off] = (byte) (c3 >> 8);
            bytes[6 * charSize + off] = (byte) c3;
            bytes[7 * charSize + off] = (byte) (c4 >> 8);
            bytes[8 * charSize + off] = (byte) c4;
            bytes[9 * charSize + off] = (byte) (c5 >> 8);
            bytes[10 * charSize + off] = (byte) c5;
            bytes[11 * charSize + off] = (byte) (c6 >> 8);
            bytes[12 * charSize + off] = (byte) c6;
        } else if ((i >> 56) == 0) {
            bytes = new byte[14 * charSize];
            bytes[0 * charSize + off] = (byte) (c0 >> 8);
            bytes[1 * charSize + off] = (byte) c0;
            bytes[2 * charSize + off] = (byte) (c1 >> 8);
            bytes[3 * charSize + off] = (byte) c1;
            bytes[4 * charSize + off] = (byte) (c2 >> 8);
            bytes[5 * charSize + off] = (byte) c2;
            bytes[6 * charSize + off] = (byte) (c3 >> 8);
            bytes[7 * charSize + off] = (byte) c3;
            bytes[8 * charSize + off] = (byte) (c4 >> 8);
            bytes[9 * charSize + off] = (byte) c4;
            bytes[10 * charSize + off] = (byte) (c5 >> 8);
            bytes[11 * charSize + off] = (byte) c5;
            bytes[12 * charSize + off] = (byte) (c6 >> 8);
            bytes[13 * charSize + off] = (byte) c6;
        } else if ((i >> 60) == 0) {
            bytes = new byte[13 * charSize];
            bytes[0 * charSize + off] = (byte) c0;
            bytes[1 * charSize + off] = (byte) (c1 >> 8);
            bytes[2 * charSize + off] = (byte) c1;
            bytes[3 * charSize + off] = (byte) (c2 >> 8);
            bytes[4 * charSize + off] = (byte) c2;
            bytes[5 * charSize + off] = (byte) (c3 >> 8);
            bytes[6 * charSize + off] = (byte) c3;
            bytes[7 * charSize + off] = (byte) (c4 >> 8);
            bytes[8 * charSize + off] = (byte) c4;
            bytes[9 * charSize + off] = (byte) (c5 >> 8);
            bytes[10 * charSize + off] = (byte) c5;
            bytes[11 * charSize + off] = (byte) (c6 >> 8);
            bytes[12 * charSize + off] = (byte) c6;
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
