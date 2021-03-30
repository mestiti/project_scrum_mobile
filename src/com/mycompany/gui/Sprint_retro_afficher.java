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
import com.mycompany.myapp.entities.Sprint_retrospective;
import com.mycompany.myapp.services.ServiceAbsence;
import com.mycompany.myapp.services.ServiceSprint_retro;
import java.util.ArrayList;

/**
 *
 * @author Hajer
 */
public class Sprint_retro_afficher extends SideMenuBaseFormSM{
     private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceAbsence srva = new ServiceAbsence();
    Form current;
    ArrayList<Absence> absences = srva.getAbsence(username1);
    ServiceSprint_retro srvr = new ServiceSprint_retro();
    
public Sprint_retro_afficher(Resources res, int x,Form f,String username,int idu ,int ids,String img){
 super("Newsfeed", BoxLayout.y());        
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
        ArrayList<Sprint_retrospective> retro1 = srvr.getRetro(username);
         getContentPane().setScrollVisible(false);
         
        TextField nomsprint = new TextField(""+retro1.get(x).getDescription());
        nomsprint.setUIID("TextFieldBlack");
        addStringValue("Sprint          :", nomsprint);
        
        TextField equipe = new TextField(""+retro1.get(x).getNom_equipe());
        equipe.setUIID("TextFieldBlack");
        addStringValue("Nom Equipe", equipe);
        
        TextField projet = new TextField(""+retro1.get(x).getNom_projet());
        projet.setUIID("TextFieldBlack");
        addStringValue("Nom Projet :", projet);
         
         TextField tfusernamee = new TextField(""+retro1.get(x).getDate_sprint_retrospective());
        tfusernamee.setUIID("TextFieldBlack");
        addStringValue("Date            :", tfusernamee);
        
       
        TextField desc = new TextField(""+retro1.get(x).getDescription_TODO());
        desc.setUIID("TextFieldBlack");
        addStringValue("Description :", desc);
FontImage arrowDown2 = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "Label", 5);
FontImage arrowDown3 = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Label", 5);
                Button delete = new Button("Delete");
        delete.setUIID("DeleteButton");
        delete.setIcon(arrowDown2);
        add(delete);
        
        Button modifier = new Button("Modifier");
        modifier.setUIID("SaveButton");
        modifier.setIcon(arrowDown3);
        add(modifier);
        
        modifier.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
         
         
          Sprint_retrospective sp = new Sprint_retrospective();
         int id = sp.getId_sprint_retrospective();

         sp.setDate_sprint_retrospective(tfusernamee.getText());
         sp.setDescription_TODO(desc.getText());
        sp.setId_sprint_retrospective(id);
         
              srvr.modifretro(sp, idu);         
              new Sprint_Retro_Form(res, img1, username1, id1).show();
     }
 });
        
        delete.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
        
if( srvr.getInstance().delete(idu)){
                            Dialog.show("Success","Sprint Retrospective supprimer",new Command("OK"));
                        
                               new Sprint_Retro_Form(res, img1, username1, id1).show();
}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
         
         
         
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
                                    new Label("Sprint Retrospective", "Title"),
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
        new ProfileFormSM(res,username1,id1,img1).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
                 new Sprint_form(res,username1,id1,img1).show();

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

    @Override
    protected void calendrier(Resources res) {
    }

    @Override
    protected void List_demandes(Resources res) {
    }
    
}
