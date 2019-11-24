/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.databases;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ahmad
 */
public interface BaseDao<T> {
    void create(T model) throws SQLException;
    void delete(T model) throws SQLException;
    void update(T model) throws SQLException;
    List<T> getAll() throws SQLException;
    T findById(Integer id) throws SQLException;   
}
