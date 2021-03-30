/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.entities.UserSt;
import com.mycompany.myapp.entities.UserSt_bp;
import com.mycompany.myapp.services.ServiceBS;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class UsrSt_ajout_Form extends SideMenuBaseFormSM {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    String usrst_bp="";
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    Form current;
          TextArea  zz = new TextField("", "Description", 20, TextField.EMAILADDR);
          TextArea descus = new TextArea("",3, 20);
          


    public UsrSt_ajout_Form(Resources res,String username, int id,String img,Sprint x)  {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
         username1=username;
        id1=id;
        img1=img;
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        // ArrayList<Sprint> sprints = srv.getSprint(username);
       // System.err.println(sprints);
   
     


        Container titleCmp = BoxLayout.encloseY(
                       
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("  ", "Title"),
                                    new Label("Sprint: "+x.getDesc(), "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            ),
                        GridLayout.encloseIn(2)
                );
     
        
                  this.getToolbar().addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK_IOS,e -> new UserSt_Form(res, username, id, img, x).show());

       tb.setTitleComponent(titleCmp);
     
                        
     
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
         
  for (int i=0 ; i<srv.getus_bp(username).size() ;i++){
      cont(res,srv.getus_bp(username).get(i));
  }
  Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
  Image pic = res.getImage("ajj.png");
         Button b = new Button(pic);
                 
         Label desc1 = new Label("Description");
         desc1.setUIID("PaddedLabel");
        descus.setUIID("TextFieldBlack");
        c.add(desc1);
        c.add(descus);
     //   c.add(b);
      
        add(c);
        add(b);
        
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                srv.add_us(x.getId(), descus.getText(), usrst_bp);
              new  UserSt_Form(res, username1, id1, img1, x).show();
                
            }
        });
       
        setupSideMenu(res,img);
    }
    
    public void cont (Resources res,UserSt_bp u) {
           Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
           Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
           Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       Image i= null;

     i = res.getImage("add.png");
        
           i = i.scaled(80, 80);
           Button k = new Button(i);
         Label l0 = new Label("Description: ");
         l0.setUIID("PaddedLabel");
         SpanLabel l1 = new SpanLabel(u.getDesc());
         l1.setUIID("TextFieldBlack");
         c1.add(l0);
         c1.add(l1);
        
  
            
           Label v = new Label("                       ");
           c3.add(c1);
       c2.add(v);
         c2.add(k);
         c3.add(c2);
         c4.setUIID("InputContainerForeground");
         
         c4.add(c3);
           add(c4);
           k.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                 descus.setText(descus.getText()+" " +u.getDesc())  ;
                 usrst_bp=u.getDesc();
                       
               }
           });
        
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
