/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;


import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.util.Resources;

/**
 * The demo class is the base class for all the demos in the application
 *
 * @author Shai Almog
 */
public abstract class Demo {
    private Resources res;
    public void init(Resources res) {
        this.res = res;
    }
    
    protected Resources getResources() {
        return res;
    }
    
    public abstract String getDisplayName();
    public abstract Image getDemoIcon();
    public abstract Container createDemo();
    
    public Container createDemo(Form parentForm) {
        return createDemo();
    }
}
