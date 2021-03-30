/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
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
import com.codename1.util.Base64;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Sprint_retrospective;
import com.mycompany.myapp.entities.Sprint_review;
import com.mycompany.myapp.services.ServiceAbsence;
import com.mycompany.myapp.services.ServiceSprint_retro;
import com.mycompany.myapp.services.ServiceSprint_review;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import com.teknikindustries.bulksms.SMS;

/**
 *
 * @author Hajer
 */
public class Sprint_review_affich extends SideMenuBaseFormSM{
    
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceAbsence srva = new ServiceAbsence();
    Form current;
    ArrayList<Absence> absences = srva.getAbsence(username1);
    ServiceSprint_retro srvr = new ServiceSprint_retro();
        ServiceSprint_review srsr = new ServiceSprint_review();

    
    public Sprint_review_affich(Resources res, int x,Form f,String username,int idu ,int ids,String img){
 super("Newsfeed", BoxLayout.y());        
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
                     ArrayList<Sprint_review> review = srsr.getReview(username);
         getContentPane().setScrollVisible(false);
         
         TextField nomsprint = new TextField(""+review.get(x).getDescription());
        nomsprint.setUIID("TextFieldBlack");
        addStringValue("Sprint                 :", nomsprint);
        
        TextField equipe = new TextField(""+review.get(x).getNom_equipe());
        equipe.setUIID("TextFieldBlack");
        addStringValue("Nom Equipe      :", equipe);
        
        TextField projet = new TextField(""+review.get(x).getNom_projet());
        projet.setUIID("TextFieldBlack");
        addStringValue("Nom Projet        :", projet);
        
        TextField po = new TextField(""+review.get(x).getUsername());
        po.setUIID("TextFieldBlack");
        addStringValue("Nom Po              :", po);
        
         TextField r1 = new TextField(""+review.get(x).getRemarque_review_equipe());
        r1.setUIID("TextFieldBlack");
        addStringValue("Remarque team", r1);
        
         TextField r2 = new TextField(""+review.get(x).getRemarque_review_product_owner());
        r2.setUIID("TextFieldBlack");
        addStringValue("Remarque Po    :", r2);
         
         TextField tfusernamee = new TextField(""+review.get(x).getDate_sprint_review());
        tfusernamee.setUIID("TextFieldBlack");
        addStringValue("Date                   :", tfusernamee);
        
       
        
FontImage arrowDown2 = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "Label", 5);
FontImage arrowDown3 = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Label", 5);
FontImage arrowDown4 = FontImage.createMaterial(FontImage.MATERIAL_SEND, "Label", 5);


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
         
         
          Sprint_review sr = new Sprint_review();
         int id = sr.getId_sprint_review();

         sr.setDate_sprint_review(tfusernamee.getText());
         sr.setRemarque_review_equipe(r1.getText());
                  sr.setRemarque_review_product_owner(r2.getText());

         
        sr.setId_sprint_review(id);
         
              srsr.modifreview(sr, idu);         
        new Sprint_review_Form(res, img1, username1, id1).show();
     }
 });
        
        delete.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
       if( srsr.getInstance().delete(idu)){
                            Dialog.show("Success","Sprint Review supprimer",new Command("OK"));
                        
         new Sprint_review_Form(res, img1, username1, id1).show();}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
       
         
     }
 });
        
         TextField tnumero = new TextField("");
        tnumero.setUIID("TextFieldBlack");
        addStringValue("Numero", tnumero);
       Button loginButton3 = new Button("Envoyer");
        loginButton3.setUIID("ModiferButton");
                loginButton3.setIcon(arrowDown4);

        add(loginButton3);
        loginButton3.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
         /*SMS sms = new SMS();
                            String nt = "+216" + tnumero.getText();
                            sms.SendSMS("hajerk", "Hajer123", "votre rendez_vous est le  " + tfusernamee.getText()+"", nt, "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");*/
                            
         
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
         //id1=id;
        
    }
    
    public void SMS() {
    
    String accountSID = "AC6b42b70edd3e8a300f2afb4b207de174";
String authToken = "fba6d5b8c4790ccca697931cf94c8455";
String fromPhone = "20597111";

Random r = new Random();
String val = "" + r.nextInt(10000);
while(val.length() < 4) {
    val = "0" + val;
}

Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", "20597111").
        queryParam("From", fromPhone).
        queryParam("Body", "ddddd").
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();

ConnectionRequest.setDefaultCacheMode(ConnectionRequest.CachingMode.OFF);

if(result.getResponseData() != null) {
    String error = (String)result.getResponseData().get("error_message");
    if(error != null) {
        ToastBar.showErrorMessage(error);
    }
} else {
    ToastBar.showErrorMessage("Error sending SMS: " + result.getResponseCode());
}
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
