package ru.otus.spring.boot.dao;

import java.io.InputStream;

public interface CsvDataLoader {

    InputStream loadData(String path);
}
