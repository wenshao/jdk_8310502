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
                0x3030, 0x3031, 0x3032, 0x3033, 0x3034, 0x3035, 0x3036, 0x3037, 0x3038, 0x3039,
                0x3061, 0x3062, 0x3063, 0x3064, 0x3065, 0x3066, 0x3130, 0x3131, 0x3132, 0x3133,
                0x3134, 0x3135, 0x3136, 0x3137, 0x3138, 0x3139, 0x3161, 0x3162, 0x3163, 0x3164,
                0x3165, 0x3166, 0x3230, 0x3231, 0x3232, 0x3233, 0x3234, 0x3235, 0x3236, 0x3237,
                0x3238, 0x3239, 0x3261, 0x3262, 0x3263, 0x3264, 0x3265, 0x3266, 0x3330, 0x3331,
                0x3332, 0x3333, 0x3334, 0x3335, 0x3336, 0x3337, 0x3338, 0x3339, 0x3361, 0x3362,
                0x3363, 0x3364, 0x3365, 0x3366, 0x3430, 0x3431, 0x3432, 0x3433, 0x3434, 0x3435,
                0x3436, 0x3437, 0x3438, 0x3439, 0x3461, 0x3462, 0x3463, 0x3464, 0x3465, 0x3466,
                0x3530, 0x3531, 0x3532, 0x3533, 0x3534, 0x3535, 0x3536, 0x3537, 0x3538, 0x3539,
                0x3561, 0x3562, 0x3563, 0x3564, 0x3565, 0x3566, 0x3630, 0x3631, 0x3632, 0x3633,
                0x3634, 0x3635, 0x3636, 0x3637, 0x3638, 0x3639, 0x3661, 0x3662, 0x3663, 0x3664,
                0x3665, 0x3666, 0x3730, 0x3731, 0x3732, 0x3733, 0x3734, 0x3735, 0x3736, 0x3737,
                0x3738, 0x3739, 0x3761, 0x3762, 0x3763, 0x3764, 0x3765, 0x3766, 0x3830, 0x3831,
                0x3832, 0x3833, 0x3834, 0x3835, 0x3836, 0x3837, 0x3838, 0x3839, 0x3861, 0x3862,
                0x3863, 0x3864, 0x3865, 0x3866, 0x3930, 0x3931, 0x3932, 0x3933, 0x3934, 0x3935,
                0x3936, 0x3937, 0x3938, 0x3939, 0x3961, 0x3962, 0x3963, 0x3964, 0x3965, 0x3966,
                0x6130, 0x6131, 0x6132, 0x6133, 0x6134, 0x6135, 0x6136, 0x6137, 0x6138, 0x6139,
                0x6161, 0x6162, 0x6163, 0x6164, 0x6165, 0x6166, 0x6230, 0x6231, 0x6232, 0x6233,
                0x6234, 0x6235, 0x6236, 0x6237, 0x6238, 0x6239, 0x6261, 0x6262, 0x6263, 0x6264,
                0x6265, 0x6266, 0x6330, 0x6331, 0x6332, 0x6333, 0x6334, 0x6335, 0x6336, 0x6337,
                0x6338, 0x6339, 0x6361, 0x6362, 0x6363, 0x6364, 0x6365, 0x6366, 0x6430, 0x6431,
                0x6432, 0x6433, 0x6434, 0x6435, 0x6436, 0x6437, 0x6438, 0x6439, 0x6461, 0x6462,
                0x6463, 0x6464, 0x6465, 0x6466, 0x6530, 0x6531, 0x6532, 0x6533, 0x6534, 0x6535,
                0x6536, 0x6537, 0x6538, 0x6539, 0x6561, 0x6562, 0x6563, 0x6564, 0x6565, 0x6566,
                0x6630, 0x6631, 0x6632, 0x6633, 0x6634, 0x6635, 0x6636, 0x6637, 0x6638, 0x6639,
                0x6661, 0x6662, 0x6663, 0x6664, 0x6665, 0x6666
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
        char i = hex256[((int) (msb >> 56)) & 0xff];
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
