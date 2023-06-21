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

        byte[] buf;
        if ((i >> 4) == 0) {
            buf = new byte[1 * charSize];
            buf[0 * charSize + off] = (byte) c3;
        } else if ((i >> 8) == 0) {
            buf = new byte[2 * charSize];
            buf[0 * charSize + off] = (byte) (c3 >> 8);
            buf[1 * charSize + off] = (byte) c3;
        } else if ((i >> 12) == 0) {
            buf = new byte[3 * charSize];
            buf[0 * charSize + off] = (byte) c2;
            buf[1 * charSize + off] = (byte) (c3 >> 8);
            buf[2 * charSize + off] = (byte) c3;
        } else if ((i >> 16) == 0) {
            buf = new byte[4 * charSize];
            buf[0 * charSize + off] = (byte) (c2 >> 8);
            buf[1 * charSize + off] = (byte) c2;
            buf[2 * charSize + off] = (byte) (c3 >> 8);
            buf[3 * charSize + off] = (byte) c3;
        } else if ((i >> 20) == 0) {
            buf = new byte[5 * charSize];
            buf[0 * charSize + off] = (byte) c1;
            buf[1 * charSize + off] = (byte) (c2 >> 8);
            buf[2 * charSize + off] = (byte) c2;
            buf[3 * charSize + off] = (byte) (c3 >> 8);
            buf[4 * charSize + off] = (byte) c3;
        } else if ((i >> 24) == 0) {
            buf = new byte[6 * charSize];
            buf[0 * charSize + off] = (byte) (c1 >> 8);
            buf[1 * charSize + off] = (byte) c1;
            buf[2 * charSize + off] = (byte) (c2 >> 8);
            buf[3 * charSize + off] = (byte) c2;
            buf[4 * charSize + off] = (byte) (c3 >> 8);
            buf[5 * charSize + off] = (byte) c3;
        } else if ((i >> 28) == 0) {
            buf = new byte[7 * charSize];
            buf[0 * charSize + off] = (byte) c0;
            buf[1 * charSize + off] = (byte) (c1 >> 8);
            buf[2 * charSize + off] = (byte) c1;
            buf[3 * charSize + off] = (byte) (c2 >> 8);
            buf[4 * charSize + off] = (byte) c2;
            buf[5 * charSize + off] = (byte) (c3 >> 8);
            buf[6 * charSize + off] = (byte) c3;
        } else {
            buf = new byte[8 * charSize];
            buf[0 * charSize + off] = (byte) (c0 >> 8);
            buf[1 * charSize + off] = (byte) c0;
            buf[2 * charSize + off] = (byte) (c1 >> 8);
            buf[3 * charSize + off] = (byte) c1;
            buf[4 * charSize + off] = (byte) (c2 >> 8);
            buf[5 * charSize + off] = (byte) c2;
            buf[6 * charSize + off] = (byte) (c3 >> 8);
            buf[7 * charSize + off] = (byte) c3;
        }

        return JDKUtils.STRING_CREATOR_JDK11.apply(buf, coder);
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

        byte[] buf;
        if ((i >> 4) == 0) {
            buf = new byte[1 * charSize];
            buf[0 * charSize + off] = (byte) c3;
        } else if ((i >> 8) == 0) {
            buf = new byte[2 * charSize];
            buf[0 * charSize + off] = (byte) (c3 >> 8);
            buf[1 * charSize + off] = (byte) c3;
        } else if ((i >> 12) == 0) {
            buf = new byte[3 * charSize];
            buf[0 * charSize + off] = (byte) c2;
            buf[1 * charSize + off] = (byte) (c3 >> 8);
            buf[2 * charSize + off] = (byte) c3;
        } else if ((i >> 16) == 0) {
            buf = new byte[4 * charSize];
            buf[0 * charSize + off] = (byte) (c2 >> 8);
            buf[1 * charSize + off] = (byte) c2;
            buf[2 * charSize + off] = (byte) (c3 >> 8);
            buf[3 * charSize + off] = (byte) c3;
        } else if ((i >> 20) == 0) {
            buf = new byte[5 * charSize];
            buf[0 * charSize + off] = (byte) c1;
            buf[1 * charSize + off] = (byte) (c2 >> 8);
            buf[2 * charSize + off] = (byte) c2;
            buf[3 * charSize + off] = (byte) (c3 >> 8);
            buf[4 * charSize + off] = (byte) c3;
        } else if ((i >> 24) == 0) {
            buf = new byte[6 * charSize];
            buf[0 * charSize + off] = (byte) (c1 >> 8);
            buf[1 * charSize + off] = (byte) c1;
            buf[2 * charSize + off] = (byte) (c2 >> 8);
            buf[3 * charSize + off] = (byte) c2;
            buf[4 * charSize + off] = (byte) (c3 >> 8);
            buf[5 * charSize + off] = (byte) c3;
        } else if ((i >> 28) == 0) {
            buf = new byte[7 * charSize];
            buf[0 * charSize + off] = (byte) c0;
            buf[1 * charSize + off] = (byte) (c1 >> 8);
            buf[2 * charSize + off] = (byte) c1;
            buf[3 * charSize + off] = (byte) (c2 >> 8);
            buf[4 * charSize + off] = (byte) c2;
            buf[5 * charSize + off] = (byte) (c3 >> 8);
            buf[6 * charSize + off] = (byte) c3;
        } else if ((i >> 32) == 0) {
            buf = new byte[8 * charSize];
            buf[0 * charSize + off] = (byte) (c0 >> 8);
            buf[1 * charSize + off] = (byte) c0;
            buf[2 * charSize + off] = (byte) (c1 >> 8);
            buf[3 * charSize + off] = (byte) c1;
            buf[4 * charSize + off] = (byte) (c2 >> 8);
            buf[5 * charSize + off] = (byte) c2;
            buf[6 * charSize + off] = (byte) (c3 >> 8);
            buf[7 * charSize + off] = (byte) c3;
        } else if ((i >> 36) == 0) {
            buf = new byte[9 * charSize];
            buf[0 * charSize + off] = (byte) c0;
            buf[1 * charSize + off] = (byte) (c1 >> 8);
            buf[2 * charSize + off] = (byte) c1;
            buf[3 * charSize + off] = (byte) (c2 >> 8);
            buf[4 * charSize + off] = (byte) c2;
            buf[5 * charSize + off] = (byte) (c3 >> 8);
            buf[6 * charSize + off] = (byte) c3;
            buf[7 * charSize + off] = (byte) (c4 >> 8);
            buf[8 * charSize + off] = (byte) c4;
        } else if ((i >> 40) == 0) {
            buf = new byte[10 * charSize];
            buf[0 * charSize + off] = (byte) (c0 >> 8);
            buf[1 * charSize + off] = (byte) c0;
            buf[2 * charSize + off] = (byte) (c1 >> 8);
            buf[3 * charSize + off] = (byte) c1;
            buf[4 * charSize + off] = (byte) (c2 >> 8);
            buf[5 * charSize + off] = (byte) c2;
            buf[6 * charSize + off] = (byte) (c3 >> 8);
            buf[7 * charSize + off] = (byte) c3;
            buf[8 * charSize + off] = (byte) (c4 >> 8);
            buf[9 * charSize + off] = (byte) c4;
        } else if ((i >> 44) == 0) {
            buf = new byte[11 * charSize];
            buf[0 * charSize + off] = (byte) c0;
            buf[1 * charSize + off] = (byte) (c1 >> 8);
            buf[2 * charSize + off] = (byte) c1;
            buf[3 * charSize + off] = (byte) (c2 >> 8);
            buf[4 * charSize + off] = (byte) c2;
            buf[5 * charSize + off] = (byte) (c3 >> 8);
            buf[6 * charSize + off] = (byte) c3;
            buf[7 * charSize + off] = (byte) (c4 >> 8);
            buf[8 * charSize + off] = (byte) c4;
            buf[9 * charSize + off] = (byte) (c5 >> 8);
            buf[10 * charSize + off] = (byte) c5;
        } else if ((i >> 48) == 0) {
            buf = new byte[12 * charSize];
            buf[0 * charSize + off] = (byte) (c0 >> 8);
            buf[1 * charSize + off] = (byte) c0;
            buf[2 * charSize + off] = (byte) (c1 >> 8);
            buf[3 * charSize + off] = (byte) c1;
            buf[4 * charSize + off] = (byte) (c2 >> 8);
            buf[5 * charSize + off] = (byte) c2;
            buf[6 * charSize + off] = (byte) (c3 >> 8);
            buf[7 * charSize + off] = (byte) c3;
            buf[8 * charSize + off] = (byte) (c4 >> 8);
            buf[9 * charSize + off] = (byte) c4;
            buf[10 * charSize + off] = (byte) (c5 >> 8);
            buf[11 * charSize + off] = (byte) c5;
        } else if ((i >> 52) == 0) {
            buf = new byte[13 * charSize];
            buf[0 * charSize + off] = (byte) c0;
            buf[1 * charSize + off] = (byte) (c1 >> 8);
            buf[2 * charSize + off] = (byte) c1;
            buf[3 * charSize + off] = (byte) (c2 >> 8);
            buf[4 * charSize + off] = (byte) c2;
            buf[5 * charSize + off] = (byte) (c3 >> 8);
            buf[6 * charSize + off] = (byte) c3;
            buf[7 * charSize + off] = (byte) (c4 >> 8);
            buf[8 * charSize + off] = (byte) c4;
            buf[9 * charSize + off] = (byte) (c5 >> 8);
            buf[10 * charSize + off] = (byte) c5;
            buf[11 * charSize + off] = (byte) (c6 >> 8);
            buf[12 * charSize + off] = (byte) c6;
        } else if ((i >> 56) == 0) {
            buf = new byte[14 * charSize];
            buf[0 * charSize + off] = (byte) (c0 >> 8);
            buf[1 * charSize + off] = (byte) c0;
            buf[2 * charSize + off] = (byte) (c1 >> 8);
            buf[3 * charSize + off] = (byte) c1;
            buf[4 * charSize + off] = (byte) (c2 >> 8);
            buf[5 * charSize + off] = (byte) c2;
            buf[6 * charSize + off] = (byte) (c3 >> 8);
            buf[7 * charSize + off] = (byte) c3;
            buf[8 * charSize + off] = (byte) (c4 >> 8);
            buf[9 * charSize + off] = (byte) c4;
            buf[10 * charSize + off] = (byte) (c5 >> 8);
            buf[11 * charSize + off] = (byte) c5;
            buf[12 * charSize + off] = (byte) (c6 >> 8);
            buf[13 * charSize + off] = (byte) c6;
        } else if ((i >> 60) == 0) {
            buf = new byte[13 * charSize];
            buf[0 * charSize + off] = (byte) c0;
            buf[1 * charSize + off] = (byte) (c1 >> 8);
            buf[2 * charSize + off] = (byte) c1;
            buf[3 * charSize + off] = (byte) (c2 >> 8);
            buf[4 * charSize + off] = (byte) c2;
            buf[5 * charSize + off] = (byte) (c3 >> 8);
            buf[6 * charSize + off] = (byte) c3;
            buf[7 * charSize + off] = (byte) (c4 >> 8);
            buf[8 * charSize + off] = (byte) c4;
            buf[9 * charSize + off] = (byte) (c5 >> 8);
            buf[10 * charSize + off] = (byte) c5;
            buf[11 * charSize + off] = (byte) (c6 >> 8);
            buf[12 * charSize + off] = (byte) c6;
        } else {
            buf = new byte[8 * charSize];
            buf[0 * charSize + off] = (byte) (c0 >> 8);
            buf[1 * charSize + off] = (byte) c0;
            buf[2 * charSize + off] = (byte) (c1 >> 8);
            buf[3 * charSize + off] = (byte) c1;
            buf[4 * charSize + off] = (byte) (c2 >> 8);
            buf[5 * charSize + off] = (byte) c2;
            buf[6 * charSize + off] = (byte) (c3 >> 8);
            buf[7 * charSize + off] = (byte) c3;
        }

        return JDKUtils.STRING_CREATOR_JDK11.apply(buf, coder);
    }
}
