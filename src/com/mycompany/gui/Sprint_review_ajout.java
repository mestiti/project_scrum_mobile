/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sprint_retrospective;
import com.mycompany.myapp.entities.Sprint_review;
import com.mycompany.myapp.services.ServiceSprint_review;
import java.util.ArrayList;

/**
 *
 * @author Hajer
 */
public class Sprint_review_ajout extends SideMenuBaseFormSM{
    
     
     private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
            ServiceSprint_review srsr = new ServiceSprint_review();

    ArrayList<Sprint_review> review = srsr.getReview(username1);
    
    public Sprint_review_ajout(Resources res,String img,String username, int id){
 super("Newsfeed", BoxLayout.y());        
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
         getContentPane().setScrollVisible(false);
         TextField tsprint = new TextField("");
        tsprint.setUIID("TextFieldBlack");
        addStringValue("Sprint                :", tsprint);
        
       TextField tequipe = new TextField("");
        tequipe.setUIID("TextFieldBlack");
        addStringValue("Nom Equipe      :", tequipe);
        
        TextField tprojet = new TextField("");
        tprojet.setUIID("TextFieldBlack");
        addStringValue("Nom Projet        :", tprojet);
        
         TextField tpo = new TextField("");
        tpo.setUIID("TextFieldBlack");
        addStringValue("Nom Po             :", tpo);
        
        TextField r1 = new TextField("");
        r1.setUIID("TextFieldBlack");
        addStringValue("Remarque Team", r1);
        
        TextField r2 = new TextField("");
        r2.setUIID("TextFieldBlack");
        addStringValue("Remarque Po     :", r2);
        
        
        Picker tDate = new Picker();
        tDate.setUIID("TextFieldBlack");
        addStringValue2("Date                   :", tDate);
        
          
        
        
        
        
        
        FontImage arrowDown1 = FontImage.createMaterial(FontImage.MATERIAL_CHECK, "Label", 5);
        Button loginButton = new Button("save");
        loginButton.setUIID("SaveButton");
        loginButton.setIcon(arrowDown1);
        add(loginButton);
        
       
        
    
        loginButton.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
         
         String datef=(new SimpleDateFormat("yyyy-MM-dd")).format(tDate.getDate());
         Sprint_review sr = new Sprint_review(r1.getText(), r2.getText(), datef, tequipe.getText(),  tprojet.getText(), tsprint.getText(), tpo.getText());
          if ((tsprint.getText().length()==0)||(tequipe.getText().length()==0)||(tprojet.getText().length()==0)||(tpo.getText().length()==0)||(r1.getText().length()==0)||(r2.getText().length()==0)||(r2.getText().length()==0))
                    Dialog.show("Alert", "Les champs ne sont pas remplis", new Command("OK"));
                else
                {
                    try {
                        //srva.addTask(a);
                        if( srsr.getInstance().addTask(sr)){
                            Dialog.show("Success","Sprint Review ajouté",new Command("OK"));
                        
                         new Sprint_review_Form(res, img, username, id).show();}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
     }
 });

        
          
         
         
         
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
                                    new Label("Sprint Review", "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            )
                        
                );
        
                tb.setTitleComponent(titleCmp);
                

        setupSideMenu(res,img);
         img1=img;
         username1=username;
         id1=id;
        
    }
  
    public void createDemo(Resources res) {
        getContentPane().setScrollVisible(false);
         TextField username = new TextField("");
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField("sandeep@gmail.com", "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        //addStringValue("E-Mail", email);
        
        TextField password = new TextField("sandeep", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
       // addStringValue("Password", password);
        
        
        
    }
    
      private void addStringValue2(String s, Picker v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
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

    @Override
    protected void showOtherForm1(Resources res) {
    }

    @Override
    protected void editprofile(Resources res) {
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

    @Override
    protected void calendrier(Resources res) {
    }

    @Override
    protected void List_demandes(Resources res) {
    }
    
}
