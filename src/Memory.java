import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;

public class Memory {
    byte memory[] = new byte[65536];

    public Memory(String bootloaderPath) {
        try(FileInputStream f = new FileInputStream(bootloaderPath)){
            byte[] bytes = f.readNBytes(256);
            for(int i = 0; i < 256; ++ i)
                memory[i] = bytes[i];
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    static short uByte(byte b) {
        return (short)Byte.toUnsignedInt(b);
    }
    static int uWord(short s) {
        return Short.toUnsignedInt(s);
    }

    public byte read(int addr) {
        return memory[uWord((short)addr)];
    }
    public byte read(short addr) {
        return read(uWord(addr));
    }
    private short readWord(int addr) {
        return BitTwiddling.glue(read(addr), read(addr + 1));
    }
    public short readWord(short addr){
        return readWord(uWord(addr));
    }
    public void write(byte value, int addr) {
        memory[uWord((short)addr)] = value;
    }
    public void writeWord(short value, int addr) {
        write(BitTwiddling.lowByteOf(value), addr);
        write(BitTwiddling.highByteOf(value), addr + 1);
    }
}
