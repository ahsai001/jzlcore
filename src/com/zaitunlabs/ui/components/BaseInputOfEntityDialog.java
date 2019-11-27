/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.ui.components;

import com.zaitunlabs.databases.BaseDao;
import com.zaitunlabs.databases.Entity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/**
 *
 * @author ahmad
 */
public abstract class BaseInputOfEntityDialog<T extends Entity> extends JDialog{
    protected T entity;
    
    public BaseInputOfEntityDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    
    public BaseInputOfEntityDialog(){
        super();
    }
    protected void showSuccedWarning(String message){
        JOptionPane.showMessageDialog(rootPane, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    protected void showInfoWarning(String message){
        JOptionPane.showMessageDialog(rootPane, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    protected void showFailedWarning(String message){
        JOptionPane.showMessageDialog(rootPane, message, "Failed", JOptionPane.WARNING_MESSAGE);
    }
    
    protected abstract boolean isValidAllInput();
    protected abstract void setViewWithEntity(T entity);
    protected abstract JTextComponent[] getInputComponentsForClearance();
    protected abstract BaseDao<T> getDaoImpl();
    protected abstract T createNewEntity();
    protected abstract void setEntityFromView(T entity);
    protected abstract BaseButton getSaveButton();
    protected abstract BaseButton getCancelButton();
    protected abstract BaseButton getClearButton();
    
    
    protected void clearInput(){
        entity = null;
        
        for(JTextComponent textComponent : getInputComponentsForClearance()){
            if(textComponent instanceof JTextField || textComponent instanceof JTextArea){
                textComponent.setText("");
            }
            
            if(textComponent instanceof JFormattedTextField){
                JFormattedTextField formattedTextField = ((JFormattedTextField)textComponent);
                formattedTextField.setValue(0);
            }
        }
    }
    
    private void saveEntity(){
        entity = createNewEntity();
        
        setEntityFromView(entity);
        try {
            BaseDao<T> dao = getDaoImpl();
            dao.create(entity);
            showSuccedWarning("Data baru berhasil disimpan");
        } catch (Exception e) {
            showFailedWarning("Data baru gagal disimpan");
        }
    }
    
    
    private void updateEntity(){
        setEntityFromView(entity);
        
        BaseDao<T> dao = getDaoImpl();
        try {
            dao.update(entity);
            showSuccedWarning("Data berhasil diperbaharui");
            clearInput();
        } catch (Exception e) {
            showFailedWarning("Data gagal diperbaharui");
        }
        
    }
    
    private void save(){
        if(!isValidAllInput()){
            return;
        }
        
        if(entity != null){
            updateEntity();
        } else {
            saveEntity();
        }
    }
    
    
    protected void initAction(){
        getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        
        getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInput();
            }
        });
        
        getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    
    public void setEntity(final T entity){
        this.entity = entity;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setViewWithEntity(entity);
            }
        });
    }
}
