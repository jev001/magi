package org.magi.module.commons.db;

import java.util.List;

public interface PersistenceProcess<T> {

    void process(List<T> modes);

    void process(T model);

}
