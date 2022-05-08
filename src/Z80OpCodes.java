import java.beans.beancontext.BeanContextServiceProviderBeanInfo;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Z80OpCodes {
    private static BiConsumer<CPUState, Memory> opcodes[] = new BiConsumer[255];
    static {
        BiConsumer<CPUState, Memory> unsupportedOp = (CPUState c, Memory m) -> {
            System.out.println("Unknown opcode " + Integer.toHexString(m.read(c.pc)) + " @ " + c.pc);
        };

        Arrays.fill(opcodes, unsupportedOp);

        //NOP
        opcodes[0x00] = (CPUState c, Memory m) -> {
            c.pc++;
        };
        //LD BC, nn
        opcodes[0x01] = (CPUState c, Memory m) -> {
            c.b = m.read(c.pc + 1);
            c.c = m.read(c.pc + 2);
            c.pc += 3;
        };
        //LD .(BC), A
        opcodes[0x02] = (CPUState c, Memory m) -> {
            m.write(c.a, c.getBC());
            c.pc++;
        };
        //INC C
        opcodes[0x0c] = (CPUState c, Memory m) -> {
           c.c++;
           c.pc++;
        };
        //LD C, n
        opcodes[0x0e] = (CPUState c, Memory m) -> {
            c.c = m.read(c.pc + 1);
            c.pc+=2;
        };
        //LD DE, nn
        opcodes[0x11] = (CPUState c, Memory m) -> {
            c.d = m.read(c.pc + 1);
            c.e = m.read(c.pc + 2);
            c.pc += 3;
        };
        //INC DE
        opcodes[0x13] = (CPUState c, Memory m) -> {
            c.incDE();
            c.pc++;
        };
        //LD A, (DE)
        opcodes[0x1a] = (CPUState c, Memory m) -> {
            c.a = m.read(c.getDE());
            c.pc++;
        };
        //JR NZ, n
        opcodes[0x20] = (CPUState c, Memory m) -> {
            if(c.notZero())
                c.pc += m.uByte(m.read(c.pc + 1));
            else
                c.pc += 2;
        };
        //LD HL, nn
        opcodes[0x21] = (CPUState c, Memory m) -> {
            c.l = m.read(c.pc + 1);
            c.h = m.read(c.pc + 2);
            c.pc += 3;
        };
        //LD SP, nn
        opcodes[0x31] = (CPUState c, Memory m) -> {
            c.sp = m.readWord((short)(c.pc + 1));
            c.pc+=3;
        };
        //LDD (HL), A
        opcodes[0x32] = (CPUState c, Memory m) -> {
            m.write(c.a, c.getHL());
            c.decHL();
            c.pc++;
        };
        //LD A, n
        opcodes[0x3e] = (CPUState c, Memory m) -> {
            c.a = m.read(c.pc + 1);
            c.pc += 2;
        };
        //LD (HL), A
        opcodes[0x77] = (CPUState c, Memory m) -> {
            m.write(c.a, c.getHL());
            c.pc++;
        };
        //LD A, E
        opcodes[0x7b] = (CPUState c, Memory m) -> {
            c.a = c.e;
            c.pc++;
        };
        //XOR A
        opcodes[0xaf] = (CPUState c, Memory m) -> {
            c.a = 0;
            c.pc++;
        };
        //Two byte opcodes:
        opcodes[0xcb] = (CPUState c, Memory m) -> {

        };
        //CALL nn
        opcodes[0xCD] = (CPUState c, Memory m) -> {
            m.writeWord(c.pc, c.sp);
            c.sp += 2;
            c.pc = m.readWord((short)(c.pc + 1));
        };
        //LDH (0xff00 + n), A
        opcodes[0xe0] = (CPUState c, Memory m) -> {
            m.write(c.a, 0xff00 + m.uByte(m.read(c.pc + 1)));
            c.pc+=2;
        };
        //LDH (0xff00 + C), A
        opcodes[0xe2] = (CPUState c, Memory m) -> {
            m.write(c.a, 0xff00 + m.uByte(c.c));
            c.pc++;
        };
        //CP n
        opcodes[0xfe] = (CPUState c, Memory m) -> {
            //TODO:
        };
    }

    public static void execute(CPUState c, Memory m) {
        int instr = Byte.toUnsignedInt(m.read(c.pc));
        opcodes[instr].accept(c, m);
    }
}
