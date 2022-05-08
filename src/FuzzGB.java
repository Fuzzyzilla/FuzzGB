public class FuzzGB {
    public static void main(String... args) {
        short cool = (short)65535;
        byte wow = (byte)(cool >> 12);

        System.out.print(cool);
        System.out.print(wow);

        Memory m = new Memory("src/bootloader.rom");

        CPUState c = new CPUState();
        c.reset();

        while(true) {
            Z80OpCodes.execute(c, m);
        }
    }
}
