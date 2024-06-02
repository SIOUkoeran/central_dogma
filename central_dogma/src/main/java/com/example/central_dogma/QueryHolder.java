package com.example.central_dogma;

public interface QueryHolder {

    void initCache(String fileName, String query);
    void updateCache(String fileName, String query);
}
