/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import static com.codename1.ui.Image.createImage;
import com.codename1.ui.Label;
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
import com.mycompany.myapp.services.ServiceBS;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Asus
 */
public class UserSt_Form extends SideMenuBaseFormSM {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    Form current;
    Sprint x;
  

    public UserSt_Form(Resources res,String username, int id,String img,Sprint x)  {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        this.x=x;
         username1=username;
        id1=id;
        img1=img;
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
         ArrayList<Sprint> sprints = srv.getSprint(username);
       // System.err.println(sprints);
       int k = sprints.size();
     
      
  FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
         
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            fab.setUIID("FloatingActionButton");
            new UsrSt_ajout_Form(res,username,id,img,x).show();

        });
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("Sprint: "+x.getDesc(), "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            ),
                        GridLayout.encloseIn(2)
                );
        
        
        tb.setTitleComponent(titleCmp);
                        
     Button o = new Button("check all tasks");
     
        add(o);
        Image pic = res.getImage("bar2.png");
                pic = pic.scaled(1320, 40);
               add(pic);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
         
  for (int i=0 ; i<srv.getus(x.getId()).size() ;i++){
      cont(res,srv.getus(x.getId()).get(i));
  }
       
        setupSideMenu(res,img);
    }
    
    public void cont (Resources res, UserSt u) {
        
           
     Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     Container cb = new Container(new BoxLayout(BoxLayout.X_AXIS));
      
       Image de = res.getImage("dell.png");
       Button del = new Button(de);
       
       
        Image mid = res.getImage("edit.png");
        mid = mid.scaled(120, 120);
        Button update = new Button(mid);
      
         Label l0 = new Label("Description: ");
         l0.setUIID("PaddedLabel");
         SpanLabel l1 = new SpanLabel(u.getDesc());
         l1.setUIID("TextFieldBlack");
         c2.add(l0);
         c2.add(l1);
         
         
          Label l2 = new Label("Nombre de tache: ");
         l2.setUIID("PaddedLabel");
         Label l3 = new Label(u.getNbr()+"");
         c3.add(l2);
         c3.add(l3);
         
         c5.add(del);
         c5.add(update);
         Label v = new Label("     ");
       //  c4.add(v);
         c4.add(c2);
         c4.add(c3);
         c4.add(c5);
         
         
        Image i= null;
        
  
            i = res.getImage("usrst.png");
        
           i = i.scaled(180, 250);
           Button k = new Button(i);
           c1.add(k);
           c1.add(c4);
           
           add(c1);
           Image pic = res.getImage("bar2.png");
                pic = pic.scaled(1320, 40);
               add(pic);
               del.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   if(Dialog.show("Check ", "Are you sure to delete this UserStory","Ok", "Cancel") == true){
                srv.dell_us(u.getId());
                new UserSt_Form(res, username1, id1, img1, x).show();
                  }

               }
           }
   );
           k.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             new Tache_Form(res, username1, id1, img1, x, u, 1).show();
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
