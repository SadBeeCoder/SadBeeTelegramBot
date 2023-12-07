package org.example;
import java.util.HashMap;
public class UserState {
    private State state;

    String[] className = new String[3];

    private String chosenClass;

    public String getChosenClass() {
        return chosenClass;
    }

    public void setChosenClass(String chosenClass) {
        this.chosenClass = chosenClass;
    }

    public String[] getClassName() {
        return className;
    }
    public void setClassName(String[] className) {
        this.className = className;
    }


    private int progress;
    private String gamerName = null;

    public String getGamerName() {
        return gamerName;
    }

    public String setGamerName(String gamerName) {
        this.gamerName = gamerName;
        return gamerName;
    }

    public UserState() {
        this.state = State.START;
        this.progress = 0;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
