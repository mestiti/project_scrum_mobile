/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.WebBrowser;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.entities.UserSt_bp;
import com.mycompany.myapp.services.ServiceBS;
import java.util.ArrayList;


/**
 *
 * @author Asus
 */
public class Sprint_form1 extends SideMenuBaseFormSM{
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    int id_bs;
    int x;

    Form current;

    public Sprint_form1(Resources res, int x,Form f,String username,int id ,int ids,String img) {
      
     
        
        super("Newsfeed", BoxLayout.y()); 
             this.x=x;

        ArrayList<Sprint> sprints = srv.getSprint(username);
        Sprint s = sprints.get(x);
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
                Container init = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Container c0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cb = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container cx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Image pic = res.getImage("bar.png");
        Image mid = res.getImage("edit.png");
        Image de = res.getImage("dell.png");
        Image vis = res.getImage("vis.png");
        pic = pic.scaled(80, 820);
        mid = mid.scaled(120, 120);

        cx.add(pic);
        
         Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Button del = new Button(de);
         Button viss = new Button(vis);
         Button modif = new Button(mid);
         Label o = new Label("Détail du Sprint :" +s.getDesc()+"          ");
         init.add(o);
               init.add(modif);
               c0.add(init);
         Label desc1 = new Label("Nom Sprint");
         desc1.setUIID("PaddedLabel");
        SpanLabel desc =new SpanLabel(s.getDesc());
        desc.setUIID("blacked");
        c.add(desc1);
        c.add(desc);
        
      
        c0.add(c);
        
        
         
         
         Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Label d = new Label("Date Début");
         d.setUIID("PaddedLabel");
        SpanLabel d1 =new SpanLabel(s.getDate1());
        d1.setUIID("blacked");
         c1.add(d);
         c1.add(d1);
        c0.add(c1);
        
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Label d0 = new Label("Date Limite");
         d0.setUIID("PaddedLabel");
        SpanLabel d2 =new SpanLabel(s.getDate1());
     
        d2.setUIID("blacked");
         c2.add(d0);
         c2.add(d2);
        c0.add(c2);
        
         Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Label d5 = new Label("Nbr de userstory");
         d5.setUIID("PaddedLabel");
         
         SpanLabel qte =new SpanLabel(""+s.getNbr());
         qte.setUIID("blacked");

      
      
         c3.add(d5);
         c3.add(qte);
       
        c0.add(c3);
        
        
        Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
         Label d6 = new Label("Nbr de userstory estimé");
         d6.setUIID("PaddedLabel");
         
         SpanLabel qte1 =new SpanLabel(""+s.getNbr1());
         qte1.setUIID("blacked");

      
      
         c4.add(d6);
         c4.add(qte1);
       
        c0.add(c4);
    
 
         
         del.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
          if(Dialog.show("Check ", "Are you sure to delete this sprint","Ok", "Cancel") == true){
                          srv.del_sprint(s.getId());
                new Sprint_form(res,username,id,img).show();
                  }
     }
 });
         
         viss.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
         new UserSt_Form(res, username, id, img, s).show();

         
     }
 });
         cx.add(c0);
         add(cx);
         cb.add(del);
         cb.add(viss);
         
         add(cb);
         
        username1=username;
        id1=id;
        img1=img;
             
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
