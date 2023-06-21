package com.alibaba.openjdk;

import com.alibaba.fastjson2.util.JDKUtils;

import java.util.UUID;

import static com.alibaba.fastjson2.util.JDKUtils.LATIN1;
import static com.alibaba.fastjson2.util.JDKUtils.UTF16;

public class UUIDUtils {
//    static final char[] HEX256;
    static final char[] HEX256;

    static {
        /**
         *
         *  for (int i = 0; i < 256; i++) {
         *      int hi = (i >> 4) & 15;
         *      int lo = i & 15;
         *      HEX256[i] = (char) (((hi < 10 ? '0' + hi : 'a' + hi - 10) << 8)
         *              + (lo < 10 ? '0' + lo : 'a' + lo - 10));
         *  }
         */
        HEX256 = new char[]{
                12336, 12337, 12338, 12339, 12340, 12341, 12342, 12343, 12344, 12345,
                12385, 12386, 12387, 12388, 12389, 12390, 12592, 12593, 12594, 12595,
                12596, 12597, 12598, 12599, 12600, 12601, 12641, 12642, 12643, 12644,
                12645, 12646, 12848, 12849, 12850, 12851, 12852, 12853, 12854, 12855,
                12856, 12857, 12897, 12898, 12899, 12900, 12901, 12902, 13104, 13105,
                13106, 13107, 13108, 13109, 13110, 13111, 13112, 13113, 13153, 13154,
                13155, 13156, 13157, 13158, 13360, 13361, 13362, 13363, 13364, 13365,
                13366, 13367, 13368, 13369, 13409, 13410, 13411, 13412, 13413, 13414,
                13616, 13617, 13618, 13619, 13620, 13621, 13622, 13623, 13624, 13625,
                13665, 13666, 13667, 13668, 13669, 13670, 13872, 13873, 13874, 13875,
                13876, 13877, 13878, 13879, 13880, 13881, 13921, 13922, 13923, 13924,
                13925, 13926, 14128, 14129, 14130, 14131, 14132, 14133, 14134, 14135,
                14136, 14137, 14177, 14178, 14179, 14180, 14181, 14182, 14384, 14385,
                14386, 14387, 14388, 14389, 14390, 14391, 14392, 14393, 14433, 14434,
                14435, 14436, 14437, 14438, 14640, 14641, 14642, 14643, 14644, 14645,
                14646, 14647, 14648, 14649, 14689, 14690, 14691, 14692, 14693, 14694,
                24880, 24881, 24882, 24883, 24884, 24885, 24886, 24887, 24888, 24889,
                24929, 24930, 24931, 24932, 24933, 24934, 25136, 25137, 25138, 25139,
                25140, 25141, 25142, 25143, 25144, 25145, 25185, 25186, 25187, 25188,
                25189, 25190, 25392, 25393, 25394, 25395, 25396, 25397, 25398, 25399,
                25400, 25401, 25441, 25442, 25443, 25444, 25445, 25446, 25648, 25649,
                25650, 25651, 25652, 25653, 25654, 25655, 25656, 25657, 25697, 25698,
                25699, 25700, 25701, 25702, 25904, 25905, 25906, 25907, 25908, 25909,
                25910, 25911, 25912, 25913, 25953, 25954, 25955, 25956, 25957, 25958,
                26160, 26161, 26162, 26163, 26164, 26165, 26166, 26167, 26168, 26169,
                26209, 26210, 26211, 26212, 26213, 26214
        };
    }

    public static String fastUUID(UUID uuid) {
        return fastUUID(uuid.getLeastSignificantBits(), uuid.getMostSignificantBits(), true);
    }

    public static String fastUUID2(UUID uuid) {
        return fastUUID(uuid.getLeastSignificantBits(), uuid.getMostSignificantBits(), false);
    }

    static String fastUUID(long lsb, long msb, boolean COMPACT_STRINGS) {
        final char[] hex256 = HEX256;
        char i = hex256[((int) (msb >> 56)) & 255];
        char i1 = hex256[((int) (msb >> 48)) & 255];
        char i2 = hex256[((int) (msb >> 40)) & 255];
        char i3 = hex256[((int) (msb >> 32)) & 255];
        char i4 = hex256[(((int) msb) >> 24) & 255];
        char i5 = hex256[(((int) msb) >> 16) & 255];
        char i6 = hex256[(((int) msb) >> 8) & 255];
        char i7 = hex256[((int) msb) & 255];
        char i8 = hex256[(((int) (lsb >> 56))) & 255];
        char i9 = hex256[(((int) (lsb >> 48))) & 255];
        char i10 = hex256[(((int) (lsb >> 40))) & 255];
        char i11 = hex256[((int) (lsb >> 32)) & 255];
        char i12 = hex256[(((int) lsb) >> 24) & 255];
        char i13 = hex256[(((int) lsb) >> 16) & 255];
        char i14 = hex256[(((int) lsb) >> 8) & 255];
        char i15 = hex256[((int) lsb) & 255];

        if (COMPACT_STRINGS) {
            byte[] bytes = new byte[36];
            bytes[0] = (byte) (i >> 8);
            bytes[1] = (byte) i;
            bytes[2] = (byte) (i1 >> 8);
            bytes[3] = (byte) i1;
            bytes[4] = (byte) (i2 >> 8);
            bytes[5] = (byte) i2;
            bytes[6] = (byte) (i3 >> 8);
            bytes[7] = (byte) i3;
            bytes[8] = '-';
            bytes[9] = (byte) (i4 >> 8);
            bytes[10] = (byte) i4;
            bytes[11] = (byte) (i5 >> 8);
            bytes[12] = (byte) i5;
            bytes[13] = '-';
            bytes[14] = (byte) (i6 >> 8);
            bytes[15] = (byte) i6;
            bytes[16] = (byte) (i7 >> 8);
            bytes[17] = (byte) i7;
            bytes[18] = '-';
            bytes[19] = (byte) (i8 >> 8);
            bytes[20] = (byte) i8;
            bytes[21] = (byte) (i9 >> 8);
            bytes[22] = (byte) i9;
            bytes[23] = '-';
            bytes[24] = (byte) (i10 >> 8);
            bytes[25] = (byte) i10;
            bytes[26] = (byte) (i11 >> 8);
            bytes[27] = (byte) i11;
            bytes[28] = (byte) (i12 >> 8);
            bytes[29] = (byte) i12;
            bytes[30] = (byte) (i13 >> 8);
            bytes[31] = (byte) i13;
            bytes[32] = (byte) (i14 >> 8);
            bytes[33] = (byte) i14;
            bytes[34] = (byte) (i15 >> 8);
            bytes[35] = (byte) i15;
            return JDKUtils.STRING_CREATOR_JDK11.apply(bytes, LATIN1);
        } else {
            byte[] buf = new byte[72];
            putChar(buf, 0, (byte) (i >> 8));
            putChar(buf, 1, (byte) i);
            putChar(buf, 2, (byte) (i1 >> 8));
            putChar(buf, 3, (byte) i1);
            putChar(buf, 4, (byte) (i2 >> 8));
            putChar(buf, 5, (byte) i2);
            putChar(buf, 6, (byte) (i3 >> 8));
            putChar(buf, 7, (byte) i3);
            putChar(buf, 8, '-');
            putChar(buf, 9, (byte) (i4 >> 8));
            putChar(buf, 10, (byte) i4);
            putChar(buf, 11, (byte) (i5 >> 8));
            putChar(buf, 12, (byte) i5);
            putChar(buf, 13, '-');
            putChar(buf, 14, (byte) (i6 >> 8));
            putChar(buf, 15, (byte) i6);
            putChar(buf, 16, (byte) (i7 >> 8));
            putChar(buf, 17, (byte) i7);
            putChar(buf, 18, '-');
            putChar(buf, 19, (byte) (i8 >> 8));
            putChar(buf, 20, (byte) i8);
            putChar(buf, 21, (byte) (i9 >> 8));
            putChar(buf, 22, (byte) i9);
            putChar(buf, 23, '-');
            putChar(buf, 24, (byte) (i10 >> 8));
            putChar(buf, 25, (byte) i10);
            putChar(buf, 26, (byte) (i11 >> 8));
            putChar(buf, 27, (byte) i11);
            putChar(buf, 28, (byte) (i12 >> 8));
            putChar(buf, 29, (byte) i12);
            putChar(buf, 30, (byte) (i13 >> 8));
            putChar(buf, 31, (byte) i13);
            putChar(buf, 32, (byte) (i14 >> 8));
            putChar(buf, 33, (byte) i14);
            putChar(buf, 34, (byte) (i15 >> 8));
            putChar(buf, 35, (byte) i15);
            return JDKUtils.STRING_CREATOR_JDK11.apply(buf, UTF16);
        }
    }

    static final int HI_BYTE_SHIFT = 0;
    static final int LO_BYTE_SHIFT = 8;

    static void putChar(byte[] val, int index, int c) {
        index <<= 1;
        val[index++] = (byte) (c >> HI_BYTE_SHIFT);
        val[index] = (byte) (c >> LO_BYTE_SHIFT);
    }
}
