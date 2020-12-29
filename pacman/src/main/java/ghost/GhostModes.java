package ghost;

enum Type {
    SCATTER,
    CHASE,
    FRIGHTEN;
}

public class GhostModes {

    private Type mode;
    public int framesLeft;

    public GhostModes(Type mode, int seconds) {
        this.mode = mode;
        this.framesLeft = seconds * 60;
        
    }
    public Type getMode() {
        return this.mode;
    }
    
}