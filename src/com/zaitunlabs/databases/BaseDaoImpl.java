/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.databases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ahmad
 */
public abstract class BaseDaoImpl<T extends Entity> implements BaseDao<T>{
    protected final String tableName;
    protected final String columnId;
    protected final String[] dataColumns;
    
    public BaseDaoImpl(String tableName, String columnId, String[] dataColumns) {
        this.tableName = tableName;
        this.columnId = columnId;
        this.dataColumns = dataColumns;
    }
    
    @Override
    public void create(T entity) throws SQLException {
        String sql = generateQueryCreate(tableName, dataColumns);
        Object[] paramaters = collectDataFrom(entity);

        PreparedStatement preparedStatement = createPreparedStatement(sql, paramaters,
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        int id = resultSet.getInt(1);
        entity.setId(id);
    }

    @Override
    public void update(T entity) throws SQLException {
        String sql = generateQueryUpdate(tableName, dataColumns, columnId);

        List<Object> parameters = new ArrayList<>();
        parameters.addAll(Arrays.asList(collectDataFrom(entity)));
        parameters.add(entity.getId());

        PreparedStatement preparedStatement = 
                createPreparedStatement(sql, parameters.toArray(), null);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(T entity) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + columnId + "=?";
        PreparedStatement preparedStatement = createPreparedStatement(sql, 
                new Object[] { entity.getId() }, null);

        preparedStatement.setInt(1, entity.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<T> getAll() throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        return fetchDatas(sql, null);
    }

    @Override
    public T findById(Integer id) throws SQLException {
        String sql = "SELECT *FROM " + tableName + " WHERE " + columnId + "=?";
        return fetchData(sql, new Object[]{
            id
        });
    }
    
    
    
    protected abstract Object[] collectDataFrom(T entity);
    protected abstract T createNewEntityFrom(ResultSet resultSet) throws SQLException;

    
    protected List<T> fetchDatas(String query, Object[] parameters) throws SQLException {
        PreparedStatement preparedStatement = createPreparedStatement(query, parameters, null);

        List<T> entities = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            T entity = createNewEntityFrom(resultSet);
            entities.add(entity);
        }
        return entities;
    }

    protected T fetchData(String query, Object[] parameters) throws SQLException {
        PreparedStatement preparedStatement = createPreparedStatement(query, parameters, null);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        if (resultSet.getRow() == 0) {
            return null;
        }

        return createNewEntityFrom(resultSet);
    }

    private PreparedStatement createPreparedStatement(String query, Object[] parameters, 
            Integer autoGenerateKey) throws SQLException {
        PreparedStatement preparedStatement;
        
        if (autoGenerateKey != null) {
            preparedStatement = getConnection().prepareStatement(query, autoGenerateKey);
        } else {
            preparedStatement = getConnection().prepareStatement(query);
        }
        
        setValueParameters(preparedStatement, parameters);
        return preparedStatement;
    }

    private void setValueParameters(PreparedStatement preparedStatement, Object[] parameters) 
            throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                Object value = parameters[i];
                setValueParameterFor(preparedStatement, i+1, value);
            }
        } else {
            System.out.println("No Parameter is given for " + preparedStatement);
        }
    }

    private void setValueParameterFor(PreparedStatement preparedStatement, 
            int position, Object value) throws SQLException {
        
        if (value instanceof String) {
            preparedStatement.setString(position, (String) value);
        } else if (value instanceof Integer) {
            preparedStatement.setInt(position, (Integer) value);
        } else if (value instanceof Double) {
            preparedStatement.setDouble(position, (Double) value);
        } else if (value instanceof Long) {
            preparedStatement.setLong(position, (Long) value);
        } else if (value instanceof Date) {
            preparedStatement.setDate(position, (Date) value);
        }
    }
    
    private Connection getConnection() throws SQLException {
        return ConnectionHelper.getConnection();
    }

    private String generateQueryUpdate(String tableName, String[] dataColumns, String columnId) {
        String query = "UPDATE " + tableName + " SET ";

        for (int i = 0; i < getMaxColumnsData() - 1; i++) {
            query += dataColumns[i] + "=?, ";
        }

        query += dataColumns[getMaxColumnsData() - 1] + "=?";
        query += " WHERE " + columnId + "=?";

        System.out.println("UPDATE QUERY : " + query);
        return query;
    }

    private String generateQueryCreate(String tableName, String[] dataColumns) {
        String query = "INSERT INTO " + tableName + "(";

        for (int i = 0; i < getMaxColumnsData() - 1; i++) {
            query += dataColumns[i] + ", ";
        }
        query += dataColumns[getMaxColumnsData() - 1] + ") VALUES(";

        for (int i = 0; i < getMaxColumnsData() - 1; i++) {
            query += "?, ";
        }
        query += "?)";

        System.out.println("INSERT QUERY : " + query);
        return query;
    }

    private int getMaxColumnsData() {
        return dataColumns.length;
    }
}
