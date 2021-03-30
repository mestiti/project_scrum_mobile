/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.gui.Sprint_form;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.entities.Tache;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.UserSt;
import com.mycompany.myapp.entities.UserSt_bp;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceBS {

    public ArrayList<User> users;
    public ArrayList<Sprint> sprints;
    public ArrayList<UserSt> usrst;
    public ArrayList<UserSt_bp> usrst_bp;
    public ArrayList<Tache> taches;
    public int id_bs;
    
    public static ServiceBS instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceBS() {
         req = new ConnectionRequest();
    }

    public static ServiceBS getInstance() {
        if (instance == null) {
            instance = new ServiceBS();
        }
        return instance;
    }


    public ArrayList<User> parseuser(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             User u = new User();
               float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                u.setUsername(obj.get("username").toString());
                u.setRoles(obj.get("roles").toString());
                u.setImg(obj.get("image_user").toString());
                users.add(u);
            }
        } catch (IOException ex) {
            
        }
        return users;
    }
    
    
    
    public ArrayList<User> getuser(String name){
        String url = Statics.BASE_URL+"/bs/user/"+name+"";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseuser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
   public ArrayList<Sprint> getSprint(String name){
    String url = Statics.BASE_URL+"/bs/sprints/"+name+"";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    sprints = parsesprint(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return sprints;
    }
   
   public ArrayList<Sprint> parsesprint(String jsonText){
        try {
            sprints=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             Sprint u = new Sprint();
               float id = Float.parseFloat(obj.get("id_sprint").toString());
               float nbr = Float.parseFloat(obj.get("f").toString());
               float id_bs = Float.parseFloat(obj.get("id_bs").toString());
               float nbr1 = Float.parseFloat(obj.get("liste_user_sroty_bs").toString());

                u.setId((int)id);
                u.setDesc(obj.get("description").toString());
                u.setDate1(obj.get("date_debut_sprint").toString());
                u.setDate2(obj.get("date_fin_sprint").toString());
                u.setNbr((int)nbr);
                u.setId_bs((int)id_bs);
                u.setEquipe(obj.get("nom_equipe").toString());
                u.setProjet(obj.get("nom_projet").toString());
                u.setNbr1((int)nbr1);
                
                sprints.add(u);
            }
        } catch (IOException ex) {
            
        }
        return sprints;
    }
   
   public boolean upimg(String t,int id) {
        String url = Statics.BASE_URL + "/bs/updateimg/img?img=" + t + "&id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
      public int getid_bs(String bs){
   String url = Statics.BASE_URL+"/bs/idbs/"+bs+"";

   req.setUrl(url);
   req.setPost(false);
   req.addResponseListener(new ActionListener<NetworkEvent>() {
   @Override
   public void actionPerformed(NetworkEvent evt) {
   id_bs = parseid_bs(new String(req.getResponseData()));
   req.removeResponseListener(this);
   }
   });
   
   
   NetworkManager.getInstance().addToQueueAndWait(req);
   return id_bs;
   }
      
      
 public int parseid_bs(String jsonText){
              int k=0 ;
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                float id = Float.parseFloat(obj.get("id_bs").toString());
         k=(int)id;
            }
        } catch (IOException ex) {
            
        }
        return k;
    }
 
 
  public boolean add_sprint(Sprint s) {
        String url = Statics.BASE_URL + "/bs/add_sprint/new?id=" + s.getId_bs() + "&desc=" + s.getDesc() + "&d1=" + s.getDate1()+ "&d2=" + s.getDate2()+"&nbr=" + s.getNbr();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
  
  public boolean del_sprint(int id_bs) {
        String url = Statics.BASE_URL + "/bs/sprint_del/del?id=" + id_bs;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
  
  
   public ArrayList<UserSt> parseus(String jsonText){
        try {
            usrst=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              UserSt u = new UserSt();
               float id = Float.parseFloat(obj.get("id_user_story_bs").toString());
               float ids = Float.parseFloat(obj.get("id_sprint").toString());
               float nbr = Float.parseFloat(obj.get("f").toString());

                u.setId((int)id);
                u.setDesc(obj.get("description_user_story_bs").toString());
                u.setIds((int)ids);
                u.setNbr((int)nbr);

                usrst.add(u);
            }
        } catch (IOException ex) {
            
        }
        return usrst;
    }
   
     public ArrayList<UserSt> getus(int id){
    String url = Statics.BASE_URL+"/bs/aff_usrst/aff?id="+id;
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    usrst = parseus(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return usrst;
    }
     
      public ArrayList<UserSt_bp> parseus_bp(String jsonText){
        try {
            usrst_bp=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              UserSt_bp u = new UserSt_bp();
               float id = Float.parseFloat(obj.get("id_user_story_backlog_produit").toString());
                u.setId((int)id);
                u.setDesc(obj.get("user_story_bp").toString());
                

                usrst_bp.add(u);
            }
        } catch (IOException ex) {
            
        }
        return usrst_bp;
    }
     
      public ArrayList<UserSt_bp> getus_bp(String id){
    String url = Statics.BASE_URL+"/bs/aff_usrst_bp/aff?id="+id;
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    usrst_bp = parseus_bp(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return usrst_bp;
    }
      
        public boolean add_us(int ids,String desc,String desc2) {
        String url = Statics.BASE_URL + "/bs/add_usrst_bs/new?ids=" + ids + "&desc=" + desc + "&desc1=" + desc2;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
        
        public boolean dell_us(int ids) {
        String url = Statics.BASE_URL + "/bs/dell_usrst_bs/?ids=" + ids ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
        
        public ArrayList<Tache> parsetache(String jsonText){
        try {
            taches=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             Tache u = new Tache();
               float id = Float.parseFloat(obj.get("id_Tache").toString());
               float id_us = Float.parseFloat(obj.get("ide_user_story_bs").toString());
                u.setId((int)id);
                u.setNom(obj.get("nom_tache").toString());
                u.setDesc(obj.get("description_tache").toString());
                u.setId_us((int)id_us);
                u.setDiff(obj.get("type_tache").toString());
                u.setPrio(obj.get("priotity").toString());
                u.setProg(obj.get("progress").toString());
                u.setEstim(obj.get("moyenne_estimation").toString());
                u.setD1(obj.get("date_fin_tache").toString());          
                taches.add(u);
            }
        } catch (IOException ex) {
            
        }
        return taches;
    }
        
         public ArrayList<Tache> get_tache_us(int id){
    String url = Statics.BASE_URL+"/bs/aff_usrst_tache/?id="+id;
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    taches = parsetache(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return taches;
    }
     
}