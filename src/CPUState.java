public class CPUState {
    public static byte F_ZERO = (byte)0x80;

    public byte a, b, c, d, e, h, l, f;
    public short pc, sp;

    public short getBC() {
        return (short)(b << 8 | c);
    }
    public short getDE() {
        return (short)(d << 8 | e);
    }
    public void incDE() {
        e++;
        if(e==0)
            d++;
    }
    public short getHL() {
        return (short)(h << 8 | l);
    }
    public void decHL() {
        l--;
        if(l==-1)
            h--;
    }
    public boolean notZero() {
        return (f & F_ZERO) != 0;
    }
}
