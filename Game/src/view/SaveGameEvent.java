package view;

import java.io.File;

public class SaveGameEvent {
    private File file;

    protected SaveGameEvent(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    } 
}