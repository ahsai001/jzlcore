/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.ui.components;

import com.alee.global.StyleConstants;
import com.alee.laf.panel.WebPanel;
import java.awt.Insets;

/**
 *
 * @author ahmad
 */
public final class Panel extends WebPanel{
 
         
    public Panel() {
        super();
        setUndecorated(false);
        setRound(StyleConstants.decorationRound);
        int margin = 40;
        setMargin(new Insets(margin, margin, margin, margin));   
    
    }
        
    
        
   
    
    
}
