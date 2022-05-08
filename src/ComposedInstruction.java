public abstract class ComposedInstruction {
    public abstract void execute(CPUState c, Memory m);

    boolean tmpStoresByte = false;
    short tmp;
    protected int pcAdvance = 1;

    public ComposedInstruction loadIndirectImmediate() {
        pcAdvance+=2;
    };
    public ComposedInstruction storeIndirectImmediate(){
        pcAdvance+=2;
    };
    public ComposedInstruction loadImmediate8(){
        pcAdvance++;
    };
    public ComposedInstruction loadImmediate16(){
        pcAdvance+=2;
    };
    public ComposedInstruction storeReg(int reg){

    };
    public ComposedInstruction loadReg(int reg){

    };
    public ComposedInstruction addIntoReg(int reg);
    public ComposedInstruction decReg(int reg);
    public ComposedInstruction incReg(int reg);
}
