/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaitunlabs.ui.components;

import com.alee.laf.button.WebButton;
import javax.swing.ImageIcon;

/**
 *
 * @author ahmad
 */
public class BaseButton extends WebButton{
    public BaseButton(ImageIcon icon){
        super();
        init();
        setIcon(icon);
    }

    private void init() {
        setBoldFont();
        setDrawShade(true);
        setRound(5);
    }
}
