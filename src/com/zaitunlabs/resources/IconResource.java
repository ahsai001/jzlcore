/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.resources;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author ahmad
 */
public class IconResource {
    private static final IconResource ICON = new IconResource();
    /**
     * @param args the command line arguments
     */
    
    private ImageIcon grab (String fullFileName){
        URL url = getClass().getClassLoader().getResource(fullFileName);
        return new ImageIcon(url);
    }

    private ImageIcon grabFromMetaInf (String filename){
        return grab("META-INF/"+filename);
    }

    public static ImageIcon getSaveIcon(){
        return ICON.grabFromMetaInf("save.png");
    }

    public static ImageIcon getClearIcon(){
        return ICON.grabFromMetaInf("clear.png");
    }

    public static ImageIcon getAddIcon(){
        return ICON.grabFromMetaInf("add.png");
    }

    public static ImageIcon getEditIcon(){
        return ICON.grabFromMetaInf("edit.png");
    }

    public static ImageIcon getCancelIcon(){
        return ICON.grabFromMetaInf("cancel.png");
    }

    public static ImageIcon getSearchIcon(){
        return ICON.grabFromMetaInf("search.png");
    }

    public static ImageIcon getLoginIcon(){
        return ICON.grabFromMetaInf("login.png");
    }

    public static ImageIcon getLogoutIcon(){
        return ICON.grabFromMetaInf("logout.png");
    }

    public static ImageIcon getPersonIcon(){
        return ICON.grabFromMetaInf("Person.png");
    }

    public static ImageIcon getLockIcon(){
        return ICON.grabFromMetaInf("lock.png");
    }

    public static ImageIcon getUsernameIcon(){
        return ICON.grabFromMetaInf("username.png");
    }

    public static ImageIcon getStaffIcon(){
        return ICON.grabFromMetaInf("staff.png");
    }
}
