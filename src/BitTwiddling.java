public class BitTwiddling {
    public static byte lowByteOf(short word) {
        int lowValue = Short.toUnsignedInt(word);
        return (byte)(lowValue & 0xff);
    }
    public static byte highByteOf(short word) {
        int highValue = Short.toUnsignedInt(word);
        return (byte)(highValue >> 8);
    }
    public static short glue(byte high, byte low) {
        int value = Byte.toUnsignedInt(high) << 8 | Byte.toUnsignedInt(low);
        return (short)value;
    }
    public static byte ror(byte r, byte amount) {
        int rorAmount = Byte.toUnsignedInt(amount);
        rorAmount %= 8;
        int rInt = Byte.toUnsignedInt(r);
        int lostBits = rInt & ((1 << rorAmount) - 1);
        int result = rInt >> rorAmount;
        result |= lostBits << (8-rorAmount);

        return (byte)result;
    }
    public static byte rol(byte r, byte amount) {
        return ror(r, (byte)(8 - amount));
    }
}
