package com.alibaba.openjdk;

import com.alibaba.fastjson2.util.JDKUtils;

import java.nio.ByteOrder;

import static com.alibaba.fastjson2.util.JDKUtils.LATIN1;
import static com.alibaba.fastjson2.util.JDKUtils.UTF16;

public class HexUtils {
    static final boolean COMPACT_STRINGS = true;

    public static String toHexString(int i) {
        char[] hex256 = UUIDUtils.DigitCache.HEX256;

        int i0 = (i >> 24) & 0xff;
        int i1 = (i >> 16) & 0xff;
        int i2 = (i >> 8) & 0xff;
        int i3 = i & 0xff;

        char c0 = hex256[i0];
        char c1 = hex256[i1];
        char c2 = hex256[i2];
        char c3 = hex256[i3];

        byte[] buf;
        if (COMPACT_STRINGS) {
            if ((i >> 4) == 0) {
                buf = new byte[1];
                buf[0] = (byte) c3;
            } else if ((i >> 8) == 0) {
                buf = new byte[2];
                ByteArray.setChar(buf, 0, c3);
            } else if ((i >> 12) == 0) {
                buf = new byte[3];
                buf[0] = (byte) c2;
                ByteArray.setChar(buf, 1, c3);
            } else if ((i >> 16) == 0) {
                buf = new byte[4];
                ByteArray.setInt(buf, 0, (c2 << 16) | c3);
            } else if ((i >> 20) == 0) {
                buf = new byte[5];
                buf[0] = (byte) c1;
                ByteArray.setInt(buf, 1, (c2 << 16) | c3);
            } else if ((i >> 24) == 0) {
                buf = new byte[6];
                ByteArray.setChar(buf, 0, c1);
                ByteArray.setInt(buf, 2, (c2 << 16) | c3);
            } else if ((i >> 28) == 0) {
                buf = new byte[7];
                buf[0] = (byte) c0;
                ByteArray.setChar(buf, 1, c1);
                ByteArray.setInt(buf, 3, (c2 << 16) | c3);
            } else {
                buf = new byte[8];
                ByteArray.setLong(buf, 0, ((long) c0 << 48) | ((long) c1 << 32) | ((long) c2 << 16) | c3);
            }

            return JDKUtils.STRING_CREATOR_JDK11.apply(buf, LATIN1);
        }

        final int off = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? 1 : 0;
        if ((i >> 4) == 0) {
            buf = new byte[2];
            buf[off] = (byte) c3;
        } else if ((i >> 8) == 0) {
            buf = new byte[4];
            buf[off] = (byte) (c3 >> 8);
            buf[2 + off] = (byte) c3;
        } else if ((i >> 12) == 0) {
            buf = new byte[6];
            buf[off] = (byte) c2;
            buf[2 + off] = (byte) (c3 >> 8);
            buf[4 + off] = (byte) c3;
        } else if ((i >> 16) == 0) {
            buf = new byte[8];
            buf[off] = (byte) (c2 >> 8);
            buf[2 + off] = (byte) c2;
            buf[4 + off] = (byte) (c3 >> 8);
            buf[6 + off] = (byte) c3;
        } else if ((i >> 20) == 0) {
            buf = new byte[10];
            buf[off] = (byte) c1;
            buf[2 + off] = (byte) (c2 >> 8);
            buf[4 + off] = (byte) c2;
            buf[6 + off] = (byte) (c3 >> 8);
            buf[8 + off] = (byte) c3;
        } else if ((i >> 24) == 0) {
            buf = new byte[12];
            buf[off] = (byte) (c1 >> 8);
            buf[2 + off] = (byte) c1;
            buf[4 + off] = (byte) (c2 >> 8);
            buf[6 + off] = (byte) c2;
            buf[8 + off] = (byte) (c3 >> 8);
            buf[10 + off] = (byte) c3;
        } else if ((i >> 28) == 0) {
            buf = new byte[14];
            buf[off] = (byte) c0;
            buf[2 + off] = (byte) (c1 >> 8);
            buf[4 + off] = (byte) c1;
            buf[6 + off] = (byte) (c2 >> 8);
            buf[8 + off] = (byte) c2;
            buf[10 + off] = (byte) (c3 >> 8);
            buf[12 + off] = (byte) c3;
        } else {
            buf = new byte[16];
            buf[off] = (byte) (c0 >> 8);
            buf[2 + off] = (byte) c0;
            buf[4 + off] = (byte) (c1 >> 8);
            buf[6 + off] = (byte) c1;
            buf[8 + off] = (byte) (c2 >> 8);
            buf[10 + off] = (byte) c2;
            buf[12 + off] = (byte) (c3 >> 8);
            buf[14 + off] = (byte) c3;
        }

        return JDKUtils.STRING_CREATOR_JDK11.apply(buf, UTF16);
    }

    public static String toHexString(long i) {
        char[] hex256 = UUIDUtils.DigitCache.HEX256;

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

        byte[] buf;
        if (COMPACT_STRINGS) {
            if ((i >> 4) == 0) {
                buf = new byte[1];
                buf[0] = (byte) c7;
            } else if ((i >> 8) == 0) {
                buf = new byte[2];
                ByteArray.setChar(buf, 0, c7);
            } else if ((i >> 12) == 0) {
                buf = new byte[3];
                buf[0] = (byte) c6;
                ByteArray.setChar(buf, 1, c7);
            } else if ((i >> 16) == 0) {
                buf = new byte[4];
                ByteArray.setInt(buf, 0, (c6 << 16) | c7);
            } else if ((i >> 20) == 0) {
                buf = new byte[5];
                buf[0] = (byte) c5;
                ByteArray.setInt(buf, 1, (c6 << 16) | c7);
            } else if ((i >> 24) == 0) {
                buf = new byte[6];
                ByteArray.setChar(buf, 0, c5);
                ByteArray.setInt(buf, 2, (c6 << 16) | c7);
            } else if ((i >> 28) == 0) {
                buf = new byte[7];
                buf[0] = (byte) c4;
                ByteArray.setChar(buf, 1, c5);
                ByteArray.setInt(buf, 3, (c6 << 16) | c7);
            } else if ((i >> 32) == 0) {
                buf = new byte[8];
                ByteArray.setLong(buf, 0, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            } else if ((i >> 36) == 0) {
                buf = new byte[9];
                buf[0] = (byte) c3;
                ByteArray.setLong(buf, 1, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            } else if ((i >> 40) == 0) {
                buf = new byte[10];
                ByteArray.setChar(buf, 0, c3);
                ByteArray.setLong(buf, 2, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            } else if ((i >> 44) == 0) {
                buf = new byte[11];
                buf[0] = (byte) c2;
                ByteArray.setChar(buf, 1, c3);
                ByteArray.setLong(buf, 3, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            } else if ((i >> 48) == 0) {
                buf = new byte[12];
                ByteArray.setInt(buf, 0, (c2 << 16) | c3);
                ByteArray.setLong(buf, 4, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            } else if ((i >> 52) == 0) {
                buf = new byte[13];
                buf[0] = (byte) c1;
                ByteArray.setInt(buf, 1, (c2 << 16) | c3);
                ByteArray.setLong(buf, 5, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            } else if ((i >> 56) == 0) {
                buf = new byte[14];
                ByteArray.setChar(buf, 0, c1);
                ByteArray.setInt(buf, 2, (c2 << 16) | c3);
                ByteArray.setLong(buf, 6, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            } else if ((i >> 60) == 0) {
                buf = new byte[15];
                buf[0] = (byte) c0;
                ByteArray.setChar(buf, 1, c1);
                ByteArray.setInt(buf, 3, (c2 << 16) | c3);
                ByteArray.setLong(buf, 7, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            } else {
                buf = new byte[16];
                ByteArray.setLong(buf, 0, ((long) c0 << 48) | ((long) c1 << 32) | ((long) c2 << 16) | c3);
                ByteArray.setLong(buf, 8, ((long) c4 << 48) | ((long) c5 << 32) | ((long) c6 << 16) | c7);
            }

            return JDKUtils.STRING_CREATOR_JDK11.apply(buf, LATIN1);
        }

        final int off = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? 1 : 0;
        if ((i >> 4) == 0) {
            buf = new byte[2];
            buf[off] = (byte) c7;
        } else if ((i >> 8) == 0) {
            buf = new byte[4];
            buf[off] = (byte) (c7 >> 8);
            buf[2 + off] = (byte) c7;
        } else if ((i >> 12) == 0) {
            buf = new byte[6];
            buf[off] = (byte) c6;
            buf[2 + off] = (byte) (c7 >> 8);
            buf[4 + off] = (byte) c7;
        } else if ((i >> 16) == 0) {
            buf = new byte[8];
            buf[off] = (byte) (c6 >> 8);
            buf[2 + off] = (byte) c6;
            buf[4 + off] = (byte) (c7 >> 8);
            buf[6 + off] = (byte) c7;
        } else if ((i >> 20) == 0) {
            buf = new byte[10];
            buf[off] = (byte) c5;
            buf[2 + off] = (byte) (c6 >> 8);
            buf[4 + off] = (byte) c6;
            buf[6 + off] = (byte) (c7 >> 8);
            buf[8 + off] = (byte) c7;
        } else if ((i >> 24) == 0) {
            buf = new byte[12];
            buf[off] = (byte) (c5 >> 8);
            buf[2 + off] = (byte) c5;
            buf[4 + off] = (byte) (c6 >> 8);
            buf[6 + off] = (byte) c6;
            buf[8 + off] = (byte) (c7 >> 8);
            buf[10 + off] = (byte) c7;
        } else if ((i >> 28) == 0) {
            buf = new byte[14];
            buf[off] = (byte) c4;
            buf[2 + off] = (byte) (c5 >> 8);
            buf[4 + off] = (byte) c5;
            buf[6 + off] = (byte) (c6 >> 8);
            buf[8 + off] = (byte) c6;
            buf[10 + off] = (byte) (c7 >> 8);
            buf[12 + off] = (byte) c7;
        } else if ((i >> 32) == 0) {
            buf = new byte[16];
            buf[off] = (byte) (c4 >> 8);
            buf[2 + off] = (byte) c4;
            buf[4 + off] = (byte) (c5 >> 8);
            buf[6 + off] = (byte) c5;
            buf[8 + off] = (byte) (c6 >> 8);
            buf[10 + off] = (byte) c6;
            buf[12 + off] = (byte) (c7 >> 8);
            buf[14 + off] = (byte) c7;
        } else if ((i >> 36) == 0) {
            buf = new byte[18];
            buf[off] = (byte) c3;
            buf[2 + off] = (byte) (c4 >> 8);
            buf[4 + off] = (byte) c4;
            buf[6 + off] = (byte) (c5 >> 8);
            buf[8 + off] = (byte) c5;
            buf[10 + off] = (byte) (c6 >> 8);
            buf[12 + off] = (byte) c6;
            buf[14 + off] = (byte) (c7 >> 8);
            buf[16 + off] = (byte) c7;
        } else if ((i >> 40) == 0) {
            buf = new byte[20];
            buf[off] = (byte) (c3 >> 8);
            buf[2 + off] = (byte) c3;
            buf[4 + off] = (byte) (c4 >> 8);
            buf[6 + off] = (byte) c4;
            buf[8 + off] = (byte) (c5 >> 8);
            buf[10 + off] = (byte) c5;
            buf[12 + off] = (byte) (c6 >> 8);
            buf[14 + off] = (byte) c6;
            buf[16 + off] = (byte) (c7 >> 8);
            buf[18 + off] = (byte) c7;
        } else if ((i >> 44) == 0) {
            buf = new byte[22];
            buf[off] = (byte) c2;
            buf[2 + off] = (byte) (c3 >> 8);
            buf[4 + off] = (byte) c3;
            buf[6 + off] = (byte) (c4 >> 8);
            buf[8 + off] = (byte) c4;
            buf[10 + off] = (byte) (c5 >> 8);
            buf[12 + off] = (byte) c5;
            buf[14 + off] = (byte) (c6 >> 8);
            buf[16 + off] = (byte) c6;
            buf[18 + off] = (byte) (c7 >> 8);
            buf[20 + off] = (byte) c7;
        } else if ((i >> 48) == 0) {
            buf = new byte[24];
            buf[off] = (byte) (c2 >> 8);
            buf[2 + off] = (byte) c2;
            buf[4 + off] = (byte) (c3 >> 8);
            buf[6 + off] = (byte) c3;
            buf[8 + off] = (byte) (c4 >> 8);
            buf[10 + off] = (byte) c4;
            buf[12 + off] = (byte) (c5 >> 8);
            buf[14 + off] = (byte) c5;
            buf[16 + off] = (byte) (c6 >> 8);
            buf[18 + off] = (byte) c6;
            buf[20 + off] = (byte) (c7 >> 8);
            buf[22 + off] = (byte) c7;
        } else if ((i >> 52) == 0) {
            buf = new byte[26];
            buf[off] = (byte) c1;
            buf[2 + off] = (byte) (c2 >> 8);
            buf[4 + off] = (byte) c2;
            buf[6 + off] = (byte) (c3 >> 8);
            buf[8 + off] = (byte) c3;
            buf[10 + off] = (byte) (c4 >> 8);
            buf[12 + off] = (byte) c4;
            buf[14 + off] = (byte) (c5 >> 8);
            buf[16 + off] = (byte) c5;
            buf[18 + off] = (byte) (c6 >> 8);
            buf[20 + off] = (byte) c6;
            buf[22 + off] = (byte) (c7 >> 8);
            buf[24 + off] = (byte) c7;
        } else if ((i >> 56) == 0) {
            buf = new byte[28];
            buf[off] = (byte) (c1 >> 8);
            buf[2 + off] = (byte) c1;
            buf[4 + off] = (byte) (c2 >> 8);
            buf[6 + off] = (byte) c2;
            buf[8 + off] = (byte) (c3 >> 8);
            buf[10 + off] = (byte) c3;
            buf[12 + off] = (byte) (c4 >> 8);
            buf[14 + off] = (byte) c4;
            buf[16 + off] = (byte) (c5 >> 8);
            buf[18 + off] = (byte) c5;
            buf[20 + off] = (byte) (c6 >> 8);
            buf[22 + off] = (byte) c6;
            buf[24 + off] = (byte) (c7 >> 8);
            buf[26 + off] = (byte) c7;
        } else if ((i >> 60) == 0) {
            buf = new byte[30];
            buf[off] = (byte) c0;
            buf[2 + off] =(byte) (c1 >> 8);
            buf[4 + off] = (byte) c1;
            buf[6 + off] = (byte) (c2 >> 8);
            buf[8 + off] = (byte) c2;
            buf[10 + off] = (byte) (c3 >> 8);
            buf[12 + off] = (byte) c3;
            buf[14 + off] = (byte) (c4 >> 8);
            buf[16 + off] = (byte) c4;
            buf[18 + off] = (byte) (c5 >> 8);
            buf[20 + off] = (byte) c5;
            buf[22 + off] = (byte) (c6 >> 8);
            buf[24 + off] = (byte) c6;
            buf[26 + off] = (byte) (c7 >> 8);
            buf[28 + off] = (byte) c7;
        } else {
            buf = new byte[32];
            buf[off] = (byte) (c0 >> 8);
            buf[2 + off] = (byte) c0;
            buf[4 + off] =(byte) (c1 >> 8);
            buf[6 + off] = (byte) c1;
            buf[8 + off] = (byte) (c2 >> 8);
            buf[10 + off] = (byte) c2;
            buf[12 + off] = (byte) (c3 >> 8);
            buf[14 + off] = (byte) c3;
            buf[16 + off] = (byte) (c4 >> 8);
            buf[18 + off] = (byte) c4;
            buf[20 + off] = (byte) (c5 >> 8);
            buf[22 + off] = (byte) c5;
            buf[24 + off] = (byte) (c6 >> 8);
            buf[26 + off] = (byte) c6;
            buf[28 + off] = (byte) (c7 >> 8);
            buf[30 + off] = (byte) c7;
        }

        return JDKUtils.STRING_CREATOR_JDK11.apply(buf, UTF16);
    }
}
