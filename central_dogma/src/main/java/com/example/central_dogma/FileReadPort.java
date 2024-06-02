package com.example.central_dogma;

public interface FileReadPort {

    void initFile(String projectName, String repositoryName, String fileName);
    void watchFile(String projectName, String repositoryName, String fileName);
}
