/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.entities.Sprint_retrospective;
import com.mycompany.myapp.entities.Sprint_review;
import com.mycompany.myapp.services.ServiceBS;
import com.mycompany.myapp.services.ServiceSprint_retro;
import com.mycompany.myapp.services.ServiceSprint_review;
import java.util.ArrayList;

/**
 *
 * @author Hajer
 */
public class Sprint_review_Form extends SideMenuBaseFormSM{
    
     private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceSprint_review srsr = new ServiceSprint_review();

    Form current;
              
                     //ArrayList<Sprint_retrospective> retro1 = srvr.getRetro(username1);
              
      public Sprint_review_Form(Resources res,String img,String username, int id){
        super(BoxLayout.y());
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
                     ArrayList<Sprint_review> review = srsr.getReview(username);

       // System.err.println(sprints);
       int k = review.size();
       
       
       //for (int i=0;i<k;i++){
        //addButtonBottom(res,arrowDown, sprints.get(i).getDesc(), 0x5ae29d, true,i,username,id,sprints.get(i).getId(),img);
           //initGuiBuilderComponents(res, absences.get(i).getUsername(), i, username, id, absences.get(i).getId_absence(), img);
        
        //}
        System.err.println(k);
       
               // initGuiBuilderComponents(res);
               FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
               

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
       
        
       
        
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
         
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            fab.setUIID("FloatingActionButton");
                new Sprint_review_ajout(res, img, username, id).show();
            
      
        });
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        

        
        
        
        this.getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : this.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        this.getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : this.getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        this.getContentPane().animateLayout(150);
    }
}, 4);



for(int i=0;i<k;i++){
                       this.addButtonBottom(res,arrowDown, review.get(i).getRemarque_review_equipe(), 0x5ae29d, true,i,username,id,review.get(i).getId_sprint_review(),img);
                       this.show();
                       

               }
        
        
        
        
        
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
    
     private void addButtonBottom(Resources res,Image arrowDown, String text, int color, boolean first,int x,String username
    ,int idu,int ids,String img) {
        MultiButton finishLandingPage = new MultiButton(text);
        Label l = new Label(text);
     //Button ff = new Button("hahahaha");
     //ff.setIcon(arrowDown);
     //add(ff);
        
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        finishLandingPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                //new Sprint_retro_afficher(res,x,current,username,ids,ids,img).show();
                //refreshTheme();
                
                new Sprint_review_affich(res, x, current, username, ids, ids, img).show();
                
            }
        });
        
       
        
       
        
        
        
        
        
       
        this.add(finishLandingPage);
        this.show();
        
    }
     
      private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
      
      public void createDemo(Resources res, int x,Form f,String username1,int idu ,int ids,String img) {
        /*getContentPane().setScrollVisible(false);
         TextField username = new TextField(""+retro1.get(x).getDate_sprint_retrospective());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
        
        TextField date = new TextField(""+retro1.get(x).getNom_projet());
        date.setUIID("TextFieldBlack");
        addStringValue("Date", date);
        
        TextField heure = new TextField(""+retro1.get(x).getDate_sprint_retrospective());
        heure.setUIID("TextFieldBlack");
        addStringValue("Heure", heure);
        
        TextField nbr = new TextField(""+retro1.get(x).getDescription_TODO());
        nbr.setUIID("TextFieldBlack");
        addStringValue("Nombre", nbr);*/

       
        
        
        
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
