package etu1869.framework;

public class FileUpload {
    private String name;
    private String path;
    private byte[] file;

    public FileUpload(String name, String path, byte[] file) {
        this.name = name;
        this.path = path;
        this.file = file;
    }
    
    public FileUpload() {
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }
    
}
