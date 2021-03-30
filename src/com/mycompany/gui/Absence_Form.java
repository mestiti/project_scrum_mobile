/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.Accordion;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ComponentStateChangeEvent;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.services.ServiceAbsence;
import com.mycompany.myapp.services.ServiceBS;
import java.util.ArrayList;

/**
 *
 * @author Hajer
 */
public class Absence_Form extends SideMenuBaseFormSM{
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceAbsence srva = new ServiceAbsence();
    Form current;
    ArrayList<Absence> absences = srva.getAbsence(username1);
    public Absence_Form(Resources res,String img,String username, int id){
        super(BoxLayout.y());
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
         
       // System.err.println(sprints);
       int k = absences.size();
       
       
       //for (int i=0;i<k;i++){
        //addButtonBottom(res,arrowDown, sprints.get(i).getDesc(), 0x5ae29d, true,i,username,id,sprints.get(i).getId(),img);
           //initGuiBuilderComponents(res, absences.get(i).getUsername(), i, username, id, absences.get(i).getId_absence(), img);
        
        //}
       
               // initGuiBuilderComponents(res);
               FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
               for(int i=0;i<k;i++){
                       addButtonBottom(res,arrowDown, absences.get(i).getUsername(), 0x5ae29d, true,i,username,id,absences.get(i).getId_absence(),img);

               }

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
       
        
        /*gui_Label_5.setShowEvenIfBlank(true);
        gui_Label_6.setShowEvenIfBlank(true);
        gui_Label_7.setShowEvenIfBlank(true);
        gui_Label_8.setShowEvenIfBlank(true);
        gui_Label_9.setShowEvenIfBlank(true);
        
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setEditable(false);
        gui_Text_Area_1_1.setRows(2);
        gui_Text_Area_1_1.setColumns(100);
        gui_Text_Area_1_1.setEditable(false);
        gui_Text_Area_1_2.setRows(2);
        gui_Text_Area_1_2.setColumns(100);
        gui_Text_Area_1_2.setEditable(false);
        gui_Text_Area_1_3.setRows(2);
        gui_Text_Area_1_3.setColumns(100);
        gui_Text_Area_1_3.setEditable(false);
        gui_Text_Area_1_4.setRows(10);
        gui_Text_Area_1_4.setColumns(100);
        gui_Text_Area_1_4.setEditable(false);*/
        
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
         
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            fab.setUIID("FloatingActionButton");
            new Absence_ajout_Form(res,img,username,id).show();
            
      
        });
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        

        
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("Absences", "Title"),
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
                
                 FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_UP, "Label", 3);
                finishLandingPage.setEmblem(arrowDown);
                createDemo(res,x,current,username,ids,idu,img);
                FontImage arrowDown2 = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "Label", 5);
                Button delete = new Button("Delete");
        delete.setUIID("DeleteButton");
        delete.setIcon(arrowDown2);
        add(delete);
        
        delete.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
         srva.delete(ids);
         
     }
 });
                refreshTheme();
            }
        });
        
        
        
        
        
       
        add(FlowLayout.encloseIn(finishLandingPage));
        
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
        getContentPane().setScrollVisible(false);
         TextField username = new TextField(""+absences.get(x).getUsername());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
        
        TextField date = new TextField(""+absences.get(x).getDate());
        date.setUIID("TextFieldBlack");
        addStringValue("Date", date);
        
        TextField heure = new TextField(""+absences.get(x).getHeure());
        heure.setUIID("TextFieldBlack");
        addStringValue("Heure", heure);
        
        TextField nbr = new TextField(""+absences.get(x).getNbre());
        nbr.setUIID("TextFieldBlack");
        addStringValue("Nombre", nbr);

       
        
        
        
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
    
     private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_1 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_1 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_7 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_2 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_2 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_8 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_3 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_3 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_3 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_9 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_1_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_1_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_4_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private com.codename1.ui.Label gui_Label_4_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_Container_3_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_3_4 = new com.codename1.ui.Label();
    private com.codename1.ui.Label gui_Label_2_4 = new com.codename1.ui.Label();
    private com.codename1.ui.TextArea gui_Text_Area_1_4 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Label gui_Label_5 = new com.codename1.ui.Label();

    
    
    /*private void initGuiBuilderComponents(Resources res,String text, int x,String username ,int idu,int ida,String img) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");
      
        addComponent(gui_Container_1_4);
        gui_Container_1_4.setName("Container_1_4");
        gui_Container_1_4.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2_4);
        gui_Container_2_4.setName("Container_2_4");
        gui_Container_2_4.addComponent(gui_Label_1_4);
        gui_Label_1_4.setText("Mar 08");
        gui_Label_1_4.setUIID("SmallFontLabel");
        gui_Label_1_4.setName("Label_1_4");
        gui_Container_1_4.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4_4);
        gui_Container_4_4.setName("Container_4_4");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        gui_Container_4_4.addComponent(gui_Label_4_4);
        gui_Label_4_4.setUIID("Padding2");
        gui_Label_4_4.setName("Label_4_4");
        gui_Label_4_4.setIcon(res.getImage("label_round.png"));
        gui_Container_1_4.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3_4);
        gui_Container_3_4.setName("Container_3_4");
        gui_Container_3_4.addComponent(gui_Label_3_4);
        gui_Container_3_4.addComponent(gui_Label_2_4);
        gui_Container_3_4.addComponent(gui_Text_Area_1_4);
        gui_Label_3_4.setText(text);
        gui_Label_3_4.setName("Label_3_4");
        gui_Label_2_4.setText("Popular tweets this week");
        gui_Label_2_4.setUIID("RedLabel");
        gui_Label_2_4.setName("Label_2_4");
        gui_Text_Area_1_4.setText("Hi Adrian, there is a new announcement for you from Oxford Learning  Lab. Hello we completly...");
        gui_Text_Area_1_4.setUIID("SmallFontLabel");
        gui_Text_Area_1_4.setName("Text_Area_1_4");
        gui_Container_2_4.setName("Container_2_4");
        gui_Container_4_4.setName("Container_4_4");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
        gui_Container_3_4.setName("Container_3_4");
        addComponent(gui_Label_5);
        gui_Container_1.setName("Container_1");
        gui_Label_6.setText("");
        gui_Label_6.setUIID("Separator");
        gui_Label_6.setName("Label_6");
        gui_Container_1_1.setName("Container_1_1");
        gui_Label_7.setText("");
        gui_Label_7.setUIID("Separator");
        gui_Label_7.setName("Label_7");
        gui_Container_1_2.setName("Container_1_2");
        gui_Label_8.setText("");
        gui_Label_8.setUIID("Separator");
        gui_Label_8.setName("Label_8");
        gui_Container_1_3.setName("Container_1_3");
        gui_Label_9.setText("");
        gui_Label_9.setUIID("Separator");
        gui_Label_9.setName("Label_9");
        gui_Container_1_4.setName("Container_1_4");
        gui_Label_5.setText("");
        gui_Label_5.setUIID("Separator");
        gui_Label_5.setName("Label_5");
        
        
    }*/// </editor-fold>
    
    
    
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileFormSM(res,username1,id1,img1).show();
    }

    @Override
    protected void showOtherForm1(Resources res) {
         new Sprint_form(res,username1,id1,img1).show();
    }

    @Override
    protected void showForm2(Resources res) {
        
                 new Absence_Form(res,img1,username1,id1).show();

    }


    @Override
    protected void editprofile(Resources res) {
        new EditPro_form1(res,username1,id1,img1).show();
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
