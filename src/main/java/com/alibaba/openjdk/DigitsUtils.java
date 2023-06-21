package com.alibaba.openjdk;

import com.alibaba.fastjson2.util.JDKUtils;

import java.nio.charset.StandardCharsets;

import static com.alibaba.fastjson2.util.JDKUtils.LATIN1;

public class DigitsUtils {
    public static final int[] DIGITS_K = new int[1000];

    private static final byte[] MIN_INT_BYTES = "-2147483648".getBytes();
    private static final byte[] MIN_INT_BYTES_UTF16 = "-2147483648".getBytes(StandardCharsets.UTF_16);

    static {
        for (int i = 0; i < DIGITS_K.length; i++) {
            DIGITS_K[i] = (i < 10 ? (2 << 24) : i < 100 ? (1 << 24) : 0)
                    + (((i / 100) + '0') << 16)
                    + ((((i / 10) % 10) + '0') << 8)
                    + i % 10 + '0';
        }
    }

    public static String toString(int value) {
        if (value == Integer.MIN_VALUE) {
            return "-2147483648";
        }

        int i = value < 0 ? -value : value;

        int pos = 0;
        final int q1 = i / 1000;
        if (q1 == 0) {
            int v = DIGITS_K[i];
            final int start = v >> 24;
            byte[] buf = new byte[3 - start + (value < 0 ? 1 : 0)];
            if (value < 0) {
                buf[0] = '-';
                pos = 1;
            }

            if (start == 0) {
                buf[pos] = (byte) (v >> 16);
                buf[pos + 1] = (byte) (v >> 8);
                pos += 2;
            } else if (start == 1) {
                buf[pos++] = (byte) (v >> 8);
            }
            buf[pos] = (byte) v;
            return JDKUtils.STRING_CREATOR_JDK11.apply(buf, LATIN1);
        }

        final int r1 = i - q1 * 1000;
        final int q2 = q1 / 1000;
        final int v1 = DIGITS_K[r1];
        if (q2 == 0) {
            final int v2 = DIGITS_K[q1];
            int start = v2 >> 24;

            byte[] buf = new byte[3 - start + (value < 0 ? 4 : 3)];
            if (value < 0) {
                buf[0] = '-';
                pos = 1;
            }

            if (start == 0) {
                buf[pos] = (byte) (v2 >> 16);
                buf[pos + 1] = (byte) (v2 >> 8);
                pos += 2;
            } else if (start == 1) {
                buf[pos++] = (byte) (v2 >> 8);
            }
            buf[pos] = (byte) v2;
            buf[pos + 1] = (byte) (v1 >> 16);
            buf[pos + 2] = (byte) (v1 >> 8);
            buf[pos + 3] = (byte) v1;
            return JDKUtils.STRING_CREATOR_JDK11.apply(buf, LATIN1);
        }

        final int r2 = q1 - q2 * 1000;
        final int q3 = q2 / 1000;
        final int v2 = DIGITS_K[r2];
        byte[] buf;
        if (q3 == 0) {
            int v = DIGITS_K[q2];
            final int start = v >> 24;

            buf = new byte[3 - start + (value < 0 ? 7 : 6)];
            if (value < 0) {
                buf[0] = '-';
                pos = 1;
            }

            if (start == 0) {
                buf[pos] = (byte) (v >> 16);
                buf[pos + 1] = (byte) (v >> 8);
                pos += 2;
            } else if (start == 1) {
                buf[pos++] = (byte) (v >> 8);
            }
            buf[pos++] = (byte) v;
        } else {
            buf = new byte[value < 0 ? 11 : 10];
            if (value < 0) {
                buf[0] = '-';
                pos = 1;
            }

            final int r3 = q2 - q3 * 1000;
            buf[pos] = (byte) (q3 + '0');
            int v = DIGITS_K[r3];
            buf[pos + 1] = (byte) (v >> 16);
            buf[pos + 2] = (byte) (v >> 8);
            buf[pos + 3] = (byte) v;
            pos += 4;
        }

        buf[pos] = (byte) (v2 >> 16);
        buf[pos + 1] = (byte) (v2 >> 8);
        buf[pos + 2] = (byte) v2;
        buf[pos + 3] = (byte) (v1 >> 16);
        buf[pos + 4] = (byte) (v1 >> 8);
        buf[pos + 5] = (byte) v1;
        return JDKUtils.STRING_CREATOR_JDK11.apply(buf, LATIN1);
    }
}
