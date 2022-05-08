import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitTwiddlingTest {
    @Test
    void lowByteOf() {
        assertEquals((byte)0xff, BitTwiddling.lowByteOf((short)0x42ff));
        assertEquals((byte)0x00, BitTwiddling.lowByteOf((short)0x4200));
        assertEquals((byte)0xab, BitTwiddling.lowByteOf((short)0xcdab));
        assertEquals((byte)0x12, BitTwiddling.lowByteOf((short)0x3412));
    }

    @Test
    void highByteOf() {
        assertEquals((byte)0xff, BitTwiddling.highByteOf((short)0xff42));
        assertEquals((byte)0x00, BitTwiddling.highByteOf((short)0x0042));
        assertEquals((byte)0xab, BitTwiddling.highByteOf((short)0xabcd));
        assertEquals((byte)0x12, BitTwiddling.highByteOf((short)0x1234));
    }

    @Test
    void ror() {
        assertEquals((byte)0b00000001, BitTwiddling.ror((byte)0b10000000, (byte)7));
        assertEquals((byte)0b01000000, BitTwiddling.ror((byte)0b10000000, (byte)1));
        assertEquals((byte)0b10000000, BitTwiddling.ror((byte)0b10000000, (byte)8));
        assertEquals((byte)0b01010101, BitTwiddling.ror((byte)0b10101010, (byte)1));
        assertEquals((byte)0b10101010, BitTwiddling.ror((byte)0b01010101, (byte)1));
    }

    @Test
    void rol() {
        assertEquals((byte)0b00000001, BitTwiddling.rol((byte)0b10000000, (byte)1));
        assertEquals((byte)0b00000010, BitTwiddling.rol((byte)0b10000000, (byte)2));
        assertEquals((byte)0b10000000, BitTwiddling.rol((byte)0b10000000, (byte)8));
        assertEquals((byte)0b01010101, BitTwiddling.rol((byte)0b10101010, (byte)1));
        assertEquals((byte)0b10101010, BitTwiddling.rol((byte)0b01010101, (byte)1));
    }

    @Test
    void glue() {
        assertEquals((short)0x0000, BitTwiddling.glue((byte)0, (byte)0));
        assertEquals((short)0xff00, BitTwiddling.glue((byte)0xff, (byte)0));
        assertEquals((short)0x00ff, BitTwiddling.glue((byte)0x00, (byte)0xff));
        assertEquals((short)0xabcd, BitTwiddling.glue((byte)0xab, (byte)0xcd));
        assertEquals((short)0x1234, BitTwiddling.glue((byte)0x12, (byte)0x34));
    }
}