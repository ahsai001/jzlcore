/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.databases;

/**
 *
 * @author ahmad
 */
public abstract class Entity {
    protected int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
          int hash = 7;
          hash = 37*hash + this.id;
          return hash;
    }
    
    
    
}
