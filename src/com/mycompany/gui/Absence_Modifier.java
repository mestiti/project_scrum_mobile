/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.services.ServiceAbsence;
import java.util.ArrayList;

/**
 *
 * @author Hajer
 */
public class Absence_Modifier extends SideMenuBaseFormSM{
    
     private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceAbsence srva = new ServiceAbsence();
    Form current;
    ArrayList<Absence> absences = srva.getAbsence(username1);
    
        public Absence_Modifier(Resources res, int x,Form f,String username,int idu ,int ids,String img){
 super("Newsfeed", BoxLayout.y());        
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
        ArrayList<Absence> absences = srva.getAbsence(username);
         getContentPane().setScrollVisible(false);
         TextField tfusernamee = new TextField(""+absences.get(x).getUsername());
        tfusernamee.setUIID("TextFieldBlack");
        addStringValue("Username", tfusernamee);
        
       
        
        TextField date = new TextField(""+absences.get(x).getDate());
        date.setUIID("TextFieldBlack");
        addStringValue("Date", date);
        
         TextField heure = new TextField(""+absences.get(x).getHeure());
        heure.setUIID("TextFieldBlack");
        addStringValue("Heure", heure);
        
        TextField nbr = new TextField(""+absences.get(x).getNbre());
        nbr.setUIID("TextFieldBlack");
        addStringValue("Nombre", nbr);
        
        FontImage arrowDown1 = FontImage.createMaterial(FontImage.MATERIAL_CHECK, "Label", 5);
        Button loginButton = new Button("Modifier");
        loginButton.setUIID("ModiferButton");
        loginButton.setIcon(arrowDown1);
        add(loginButton);
        
        
        
        /* b.addActionListener((e)->{
                             Reclamation s = new Reclamation ();
                             int   id = s.getId();
                             s.setProbleme(tfpb.getText());
                             s.setId(id);
                             ServiceReclamation ser = new ServiceReclamation();
                             ser.modifReclamation(s, r.getId());
                             Dialog.show("Alert", "You Want To Edit ? ", "OK", "Cancel");
                             tfpb.clear();
                             ReclamationForm h=new ReclamationForm(current);
                             h.getF().show();
                         });}*/
       
        
    
        loginButton.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
         
         /*String datef=(new SimpleDateFormat("yyyy-MM-dd")).format(tDate.getDate());
         System.err.println(datef);
         System.err.println(theure.getText());
         System.err.println(Integer.parseInt(tnbr.getText()));
         System.err.println( tfusernamee.getText());
         Absence a = new Absence(datef, theure.getText(), Integer.parseInt(tnbr.getText()), tfusernamee.getText());*/
         
         Absence ab = new Absence();
         int id = ab.getId_absence();

         ab.setDate(date.getText());
         ab.setHeure(heure.getText());
       int nbb = Integer.parseInt(nbr.getText());
        ab.setNbre(nbb);
         ab.setId_absence(id);
         srva.modifReclamation(ab, idu);
         
                          new Absence_Form(res,img1,username1,id1).show();


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
                                    new Label("Absneces", "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            )
                        
                );
        
                tb.setTitleComponent(titleCmp);
                

        setupSideMenu(res,img);
         img1=img;
         username1=username;
         //id1=id;
        
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
    }

    @Override
    protected void showOtherForm1(Resources res) {
    }

    @Override
    protected void editprofile(Resources res) {
    }

    @Override
    protected void showForm2(Resources res) {
    }

    @Override
    protected void showForm3(Resources res) {
    }

    @Override
    protected void showForm4(Resources res) {
    }

    @Override
    protected void calendrier(Resources res) {
    }

    @Override
    protected void List_demandes(Resources res) {
    }
    
}
