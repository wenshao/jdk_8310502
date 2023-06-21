package com.alibaba.openjdk;

import com.alibaba.fastjson2.util.JDKUtils;

import static com.alibaba.fastjson2.util.JDKUtils.LATIN1;
import static com.alibaba.fastjson2.util.JDKUtils.UTF16;
import static com.alibaba.openjdk.UUIDUtils.putChar;

public class HexUtils {
    static final boolean COMPACT_STRINGS = true;
    public static String toHexString(int i) {
        char[] hex256 = UUIDUtils.HEX256;

        int i0 = (i >> 24) & 255;
        int i1 = (i >> 16) & 255;
        int i2 = (i >> 8) & 255;
        int i3 = i & 255;

        char c0 = hex256[i0];
        char c1 = hex256[i1];
        char c2 = hex256[i2];
        char c3 = hex256[i3];

        byte[] bytes;
        if (COMPACT_STRINGS) {
            if ((i >> 4) == 0) {
                bytes = new byte[1];
                bytes[0] = (byte) c3;
            } else if ((i >> 8) == 0) {
                bytes = new byte[2];
                bytes[0] = (byte) (c3 >> 8);
                bytes[1] = (byte) c3;
            } else if ((i >> 12) == 0) {
                bytes = new byte[3];
                bytes[0] = (byte) c2;
                bytes[1] = (byte) (c3 >> 8);
                bytes[2] = (byte) c3;
            } else if ((i >> 16) == 0) {
                bytes = new byte[4];
                bytes[0] = (byte) (c2 >> 8);
                bytes[1] = (byte) c2;
                bytes[2] = (byte) (c3 >> 8);
                bytes[3] = (byte) c3;
            } else if ((i >> 20) == 0) {
                bytes = new byte[5];
                bytes[0] = (byte) c1;
                bytes[1] = (byte) (c2 >> 8);
                bytes[2] = (byte) c2;
                bytes[3] = (byte) (c3 >> 8);
                bytes[4] = (byte) c3;
            } else if ((i >> 24) == 0) {
                bytes = new byte[6];
                bytes[0] = (byte) (c1 >> 8);
                bytes[1] = (byte) c1;
                bytes[2] = (byte) (c2 >> 8);
                bytes[3] = (byte) c2;
                bytes[4] = (byte) (c3 >> 8);
                bytes[5] = (byte) c3;
            } else if ((i >> 28) == 0) {
                bytes = new byte[7];
                bytes[0] = (byte) c0;
                bytes[1] = (byte) (c1 >> 8);
                bytes[2] = (byte) c1;
                bytes[3] = (byte) (c2 >> 8);
                bytes[4] = (byte) c2;
                bytes[5] = (byte) (c3 >> 8);
                bytes[6] = (byte) c3;
            } else {
                bytes = new byte[8];
                bytes[0] = (byte) (c0 >> 8);
                bytes[1] = (byte) c0;
                bytes[2] = (byte) (c1 >> 8);
                bytes[3] = (byte) c1;
                bytes[4] = (byte) (c2 >> 8);
                bytes[5] = (byte) c2;
                bytes[6] = (byte) (c3 >> 8);
                bytes[7] = (byte) c3;
            }

            return JDKUtils.STRING_CREATOR_JDK11.apply(bytes, LATIN1);
        } else {
            if ((i >> 4) == 0) {
                bytes = new byte[2];
                putChar(bytes, 0,  (byte) c3);
            } else if ((i >> 8) == 0) {
                bytes = new byte[4];
                putChar(bytes, 0, (byte) (c3 >> 8));
                putChar(bytes, 1, (byte) c3);
            } else if ((i >> 12) == 0) {
                bytes = new byte[6];
                putChar(bytes, 0, (byte) c2);
                putChar(bytes, 1, (byte) (c3 >> 8));
                putChar(bytes, 2, (byte) c3);
            } else if ((i >> 16) == 0) {
                bytes = new byte[8];
                putChar(bytes, 0, (byte) (c2 >> 8));
                putChar(bytes, 1, (byte) c2);
                putChar(bytes, 2, (byte) (c3 >> 8));
                putChar(bytes, 3, (byte) c3);
            } else if ((i >> 20) == 0) {
                bytes = new byte[10];
                putChar(bytes, 0, (byte) c1);
                putChar(bytes, 1, (byte) (c2 >> 8));
                putChar(bytes, 2, (byte) c2);
                putChar(bytes, 3, (byte) (c3 >> 8));
                putChar(bytes, 4, (byte) c3);
            } else if ((i >> 24) == 0) {
                bytes = new byte[12];
                putChar(bytes, 0, (byte) (c1 >> 8));
                putChar(bytes, 1, (byte) c1);
                putChar(bytes, 2, (byte) (c2 >> 8));
                putChar(bytes, 3, (byte) c2);
                putChar(bytes, 4, (byte) (c3 >> 8));
                putChar(bytes, 5, (byte) c3);
            } else if ((i >> 28) == 0) {
                bytes = new byte[14];
                putChar(bytes, 0, (byte) c0);
                putChar(bytes, 1, (byte) (c1 >> 8));
                putChar(bytes, 2, (byte) c1);
                putChar(bytes, 3, (byte) (c2 >> 8));
                putChar(bytes, 4, (byte) c2);
                putChar(bytes, 5, (byte) (c3 >> 8));
                putChar(bytes, 6, (byte) c3);
            } else {
                bytes = new byte[16];
                putChar(bytes, 0, (byte) (c0 >> 8));
                putChar(bytes, 1, (byte) c0);
                putChar(bytes, 2, (byte) (c1 >> 8));
                putChar(bytes, 3, (byte) c1);
                putChar(bytes, 4, (byte) (c2 >> 8));
                putChar(bytes, 5, (byte) c2);
                putChar(bytes, 6, (byte) (c3 >> 8));
                putChar(bytes, 7, (byte) c3);
            }

            return JDKUtils.STRING_CREATOR_JDK11.apply(bytes, UTF16);
        }
    }
}
