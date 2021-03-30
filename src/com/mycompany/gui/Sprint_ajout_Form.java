/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.isTablet;
import static com.codename1.ui.CN.openGallery;
import static com.codename1.ui.CN1Constants.GALLERY_IMAGE;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.SOUTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.services.ServiceBS;
import java.io.IOException;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

/**
 *
 * @author Hajer
 */
public class Sprint_ajout_Form extends SideMenuBaseFormSM{
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    int id_bs;
    
    Form current;
    
    
    public Sprint_ajout_Form(Resources res,String img,String username, int id){
 super("Newsfeed", BoxLayout.y());        
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
        id_bs=srv.getid_bs(username);
        // createDemo(res);
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("Sprints", "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            )
                        
                );
        
                tb.setTitleComponent(titleCmp);
                
                  this.getToolbar().addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK_IOS,e -> new Sprint_form(res, username, id, img).show());

        setupSideMenu(res,img);
         img1=img;
         username1=username;
         id1=id;
         Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Image de = res.getImage("ajj.png");
         Button b = new Button(de);
                 
         Label desc1 = new Label("Description");
         desc1.setUIID("PaddedLabel");
        TextField desc = new TextField("", "Description", 20, TextField.EMAILADDR);
        desc.setUIID("TextFieldBlack");
        c.add(desc1);
        c.add(desc);
      
        add(c);
        
        
         
         
         Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Label d = new Label("Date Début");
         d.setUIID("PaddedLabel");
        Picker d1 = new Picker();
     
        d1.setUIID("TextFieldBlack");
         c1.add(d);
         c1.add(d1);
        add(c1);
        
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Label d0 = new Label("Date Limite");
         d0.setUIID("PaddedLabel");
        Picker d2 = new Picker();
     
        d2.setUIID("TextFieldBlack");
         c2.add(d0);
         c2.add(d2);
        add(c2);
        
         Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Label d5 = new Label("Nbr de userstory estimé");
         d5.setUIID("PaddedLabel");
         
         Slider qte = new Slider();
        qte.setMinValue(2);
        qte.setMaxValue(12);
        qte.setEditable(true);
        Label qteLabel = new Label("Nbr : Non specifié");

        qte.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
              d5.setText("Nbr de userstory estimé "+qte.getProgress());
              qteLabel.setText(""+qte.getProgress());
            }
        });
      
         c3.add(d5);
         c3.add(qte);
       
        add(c3);
        

 
         
         b.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
         System.err.println(desc.getText());
         String datedb=(new SimpleDateFormat("yyyy-MM-dd")).format(d1.getDate());
         String datefn=(new SimpleDateFormat("yyyy-MM-dd")).format(d2.getDate());
      
         Sprint s = new Sprint(4, desc.getText(), datedb, datefn,Integer.parseInt(qteLabel.getText()), " ", " ", id_bs);
         srv.add_sprint(s);
          new Sprint_form(res,username,id,img).show();
         
         
     }
 });
         add(b);
         
         
        
    }
  
    public void createDemo(Resources res) {
        getContentPane().setScrollVisible(false);
        TextField desc = new TextField("", "Description", 20, TextField.EMAILADDR);
        desc.setUIID("TextFieldBlack");
        addStringValue("Description", desc);

        
        TextField email = new TextField(""+"", "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        
        
        
    }
      private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
      public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(100);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
  
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileFormSM(res,username1,id1,img1).show();
    }
    protected void showOtherForm1(Resources res) {
        new Sprint_form(res,username1,id1,img1).show();
    }
    
    protected void showsprint(Resources res,int id) {
        
    }
    @Override
    protected void editprofile(Resources res) {
        new EditPro_form1(res,username1,id1,img1).show();
    }

    @Override
    protected void showForm2(Resources res) {
        new Absence_Form(res,img1,username1,id1).show();

    }
     @Override
    protected void showForm3(Resources res) {
        new Sprint_Retro_Form(res, img1, username1, id1).show();
    }

    @Override
    protected void showForm4(Resources res) {
                new Sprint_review_Form(res, img1, username1, id1).show();

    }
    
    
}
