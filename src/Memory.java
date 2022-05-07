public class Memory {
    int[] bootstrapper = new int[] {};
    byte memory[] = new byte[65536];

    public Memory() {
        for(int i = 0; i < 256; ++i) {
            memory[i] = (byte)bootstrapper[i];
        }
    }
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

    public byte read(int addr) {
        if(addr < 0 || addr > 65535)
            throw new IllegalArgumentException("Address out of range.");
        return memory[addr];
    }
    public byte read(short addr) {
        return read(uWord(addr));
    }
    private short readWord(int addr) {
        if(addr < 0 || addr > 65535)
            throw new IllegalArgumentException("Address out of range.");
        return (short)(read(addr) | (read(addr + 1) << 8));
    }
    public short readWord(short addr){
        return readWord(uWord(addr));
    }
    public void write(byte value, int addr) {
        if(addr < 0 || addr > 65535)
            throw new IllegalArgumentException("Address out of range.");
        memory[addr] = value;
    }
    public void writeWord(short value, int addr) {
        write((byte)(value & 0xff), addr);
        write((byte)(value >> 8), addr + 1);
    }
}
