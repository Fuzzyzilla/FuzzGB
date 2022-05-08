public class CPUState {
    public static final byte F_ZERO = (byte)0x80;
    public static final int REG_A = 0, REG_B = 1, REG_C = 2, REG_D = 3, REG_E = 4,REG_H = 5, REG_L = 6, REG_F = 7,
            REG_PC = 8, REG_SP = 9, REG_BC = 10, REG_DE = 11, REG_HL = 12;

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
