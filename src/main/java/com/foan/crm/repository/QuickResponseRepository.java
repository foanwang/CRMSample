package com.foan.crm.repository;

public interface QuickResponseRepository {
    void saveByKey(String key, String content, int expire);

    String findKey(String key) throws Exception;

    void deleteByKey(String key);
}
