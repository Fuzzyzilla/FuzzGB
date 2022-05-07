public class Memory {
    static short uByte(byte b) {
        short s = 0;
        s |= b;
        return s;
    }
    static int uWord(short s) {
        int i = 0;
        i |= s;
        return i;
    }
}
