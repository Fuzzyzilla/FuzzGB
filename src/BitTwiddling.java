public class BitTwiddling {
    byte lowByteOf(short word) {
        int lowValue = Short.toUnsignedInt(word);
        return (byte)(lowValue & 0xff);
    }
    byte highByteOf(short word) {
        int highValue = Short.toUnsignedInt(word);
        return (byte)(highValue >> 0xff);
    }

}
