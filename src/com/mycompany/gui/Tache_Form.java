package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.entities.Tache;
import com.mycompany.myapp.entities.UserSt;
import com.mycompany.myapp.entities.UserSt_bp;
import com.mycompany.myapp.services.ServiceBS;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Tache_Form extends SideMenuBaseFormSM {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    Form current;
    Sprint x;
       
          


        public Tache_Form(Resources res,String username, int id,String img,Sprint x,UserSt u,int check) {
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
        this.getToolbar().addMaterialCommandToRightBar("Back", FontImage.MATERIAL_ARROW_BACK_IOS,e -> new UserSt_Form(res, username, id, img, x).show());
        tb.setTitleComponent(titleCmp);
                        
     Button o = new Button("check all tasks");
     
        add(o);
        Image pic = res.getImage("bar2.png");
                pic = pic.scaled(1320, 40);
               add(pic);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
         
  for (int i=0 ; i<srv.get_tache_us(u.getId()).size() ;i++){
      cont(res,srv.get_tache_us(u.getId()).get(i));
  }
       
        setupSideMenu(res,img);
    }
    
    public void cont (Resources res, Tache u) {
        
     Container c0 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
     String color ="";
     
     if (u.getDiff().equals("extreme")) color="extr";
     if (u.getDiff().equals("hard")) color="high";
     if (u.getDiff().equals("meduim")) color="med";
     if (u.getDiff().equals("easy")) color="low";
     
      
         Label l0 = new Label("Nom Tache: ");
         l0.setUIID("PaddedLabel");
        Label l1 = new Label(u.getNom());
         l1.setUIID("TextFieldBlack");
         c2.add(l0);
         c2.add(l1);
         
         
          Label l2 = new Label("Date limite: ");
         l2.setUIID("PaddedLabel");
         Label l3 = new Label(u.getD1());
         l3.setUIID("TextFieldBlack");
         c3.add(l2);
         c3.add(l3);
         
         Label l4 = new Label("Diffuclté: ");
         l4.setUIID("PaddedLabel");
         Label l5 = new Label(u.getDiff());
         l5.setUIID(color);
         c0.add(l4);
         c0.add(l5);
         
        
         c4.add(c2);
         c4.add(c3);
         c4.add(c5);
         c4.add(c0);
         
         
        Image i= null;
        
  if(u.getPrio().equals("Critical")) i = res.getImage("crit.PNG");
  if(u.getPrio().equals("High")) i = res.getImage("high.PNG");
  if(u.getPrio().equals("Meduim")) i = res.getImage("nrml.PNG");
  if(u.getPrio().equals("low")) i = res.getImage("low.PNG");
        
          i = i.scaled(250, 250);
           Button k = new Button(i);
           c1.add(k);
           c1.add(c4);
           c1.setUIID("InputContainerForeground");
           add(c1);
           Image pic = res.getImage("bar2.png");
                pic = pic.scaled(1320, 40);
               
              
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
