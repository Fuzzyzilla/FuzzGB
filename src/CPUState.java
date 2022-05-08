public class CPUState {
    public static byte F_ZERO = (byte)0x80;

    public byte a, b, c, d, e, h, l, f;
    public short pc, sp;

    public void reset() {
        pc = sp = 0;
        a = b = c = d = e = h = l = 0;
        f = 0;
    }
    public short getBC() {
        return BitTwiddling.glue(b, c);
    }
    public short getDE() {
        return BitTwiddling.glue(d, e);
    }
    public void incDE() {
        e++;
        if(e==0)
            d++;
    }
    public short getHL() {
        return BitTwiddling.glue(h, l);
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
