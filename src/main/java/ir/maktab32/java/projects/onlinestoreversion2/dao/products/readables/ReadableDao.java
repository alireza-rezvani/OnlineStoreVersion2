package ir.maktab32.java.projects.onlinestoreversion2.dao.products.readables;

import ir.maktab32.java.projects.onlinestoreversion2.model.products.readables.Readable;

import java.util.List;

public interface ReadableDao {
    void addReadable(Readable readable);
    void deleteReadable(int readableId);
    void editReadable(int readableId, Readable readable);
    Readable findReadableById(int readableId);
    List<Readable> findAllReadables();
}
