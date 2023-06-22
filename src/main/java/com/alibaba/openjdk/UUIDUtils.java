package com.alibaba.openjdk;

import com.alibaba.fastjson2.util.JDKUtils;

import java.nio.ByteOrder;
import java.util.UUID;

import static com.alibaba.fastjson2.util.JDKUtils.LATIN1;
import static com.alibaba.fastjson2.util.JDKUtils.UTF16;

public class UUIDUtils {
    static final class DigitCache {
        static final char[] HEX256;
        static {
            HEX256 = new char[256];
            for (int i = 0; i < 256; i++) {
                int hi = (i >> 4) & 15;
                int lo = i & 15;
                HEX256[i] = (char) (((hi < 10 ? '0' + hi : 'a' + hi - 10) << 8)
                        + (lo < 10 ? '0' + lo : 'a' + lo - 10));
            }
        }
    }

    public static String fastUUID(UUID uuid) {
        return fastUUID0(uuid.getLeastSignificantBits(), uuid.getMostSignificantBits(), true);
    }

    public static String fastUUID2(UUID uuid) {
        return fastUUID0(uuid.getLeastSignificantBits(), uuid.getMostSignificantBits(), false);
    }

    static String fastUUID0(long lsb, long msb, boolean COMPACT_STRINGS) {
        char[] hex256 = DigitCache.HEX256;

        char i0 = hex256[((int) (msb >> 56)) & 0xff];
        char i1 = hex256[((int) (msb >> 48)) & 0xff];
        char i2 = hex256[((int) (msb >> 40)) & 0xff];
        char i3 = hex256[((int) (msb >> 32)) & 0xff];
        char i4 = hex256[(((int) msb) >> 24) & 0xff];
        char i5 = hex256[(((int) msb) >> 16) & 0xff];
        char i6 = hex256[(((int) msb) >> 8) & 0xff];
        char i7 = hex256[((int) msb) & 0xff];
        char i8 = hex256[(((int) (lsb >> 56))) & 0xff];
        char i9 = hex256[(((int) (lsb >> 48))) & 0xff];
        char i10 = hex256[(((int) (lsb >> 40))) & 0xff];
        char i11 = hex256[((int) (lsb >> 32)) & 0xff];
        char i12 = hex256[(((int) lsb) >> 24) & 0xff];
        char i13 = hex256[(((int) lsb) >> 16) & 0xff];
        char i14 = hex256[(((int) lsb) >> 8) & 0xff];
        char i15 = hex256[((int) lsb) & 0xff];


        if (COMPACT_STRINGS) {
            byte[] buf = new byte[36];

            ByteArray.setLong(buf, 0, ((long) i0 << 48) | ((long) i1 << 32) | ((long) i2 << 16) | i3);
            buf[8] = '-';
            ByteArray.setInt(buf, 9, (i4 << 16) | i5);
            buf[13] = '-';
            ByteArray.setInt(buf, 14, (i6 << 16) | i7);
            buf[18] = '-';
            ByteArray.setInt(buf, 19, (i8 << 16) | i9);
            buf[23] = '-';
            ByteArray.setLong(buf, 24, ((long) i10 << 48) | ((long) i11 << 32) | ((long) i12 << 16) | i13);
            ByteArray.setInt(buf, 32, (i14 << 16) | i15);

            return JDKUtils.STRING_CREATOR_JDK11.apply(buf, LATIN1);
        }

        byte[] buf = new byte[72];
        int off = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN ? 1 : 0;
        buf[0 + off] = (byte) (i0 >> 8);
        buf[2 + off] = (byte) i0;
        buf[4 + off] = (byte) (i1 >> 8);
        buf[6 + off] = (byte) i1;
        buf[8 + off] = (byte) (i2 >> 8);
        buf[10 + off] = (byte) i2;
        buf[12 + off] = (byte) (i3 >> 8);
        buf[14 + off] = (byte) i3;
        buf[16 + off] = '-';
        buf[18 + off] = (byte) (i4 >> 8);
        buf[20 + off] = (byte) i4;
        buf[22 + off] = (byte) (i5 >> 8);
        buf[24 + off] = (byte) i5;
        buf[26 + off] = '-';
        buf[28 + off] = (byte) (i6 >> 8);
        buf[30 + off] = (byte) i6;
        buf[32 + off] = (byte) (i7 >> 8);
        buf[34 + off] = (byte) i7;
        buf[36 + off] = '-';
        buf[38 + off] = (byte) (i8 >> 8);
        buf[40 + off] = (byte) i8;
        buf[42 + off] = (byte) (i9 >> 8);
        buf[44 + off] = (byte) i9;
        buf[46 + off] = '-';
        buf[48 + off] = (byte) (i10 >> 8);
        buf[50 + off] = (byte) i10;
        buf[52 + off] = (byte) (i11 >> 8);
        buf[54 + off] = (byte) i11;
        buf[56 + off] = (byte) (i12 >> 8);
        buf[58 + off] = (byte) i12;
        buf[60 + off] = (byte) (i13 >> 8);
        buf[62 + off] = (byte) i13;
        buf[64 + off] = (byte) (i14 >> 8);
        buf[66 + off] = (byte) i14;
        buf[68 + off] = (byte) (i15 >> 8);
        buf[70 + off] = (byte) i15;

        return JDKUtils.STRING_CREATOR_JDK11.apply(buf, UTF16);
    }

    static final boolean bigEndian = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;

    static void putChar(byte[] val, int index, int c) {
        index <<= 1;
        if (bigEndian) {
            val[index] = (byte) (c >> 8);
            val[index + 1] = (byte) c;
        } else {
            val[index] = (byte) c;
            val[index + 1] = (byte) (c >> 8);
        }
    }
}
