package com.example.paratuel.beginwiththeend.app;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Paratuel on 2/9/2015.
 */
public class Project {

    String name;
    Date dueDate;
    String category;
    String notes;

    ArrayList<Project> Projects = new ArrayList<Project>();

    Project(){
     name = "Untitled";
     dueDate = new Date(1,1,2015);
     category = "";
     notes = "";
    }

    Project(String n, Date dd, String c, String nt){
        name = n;
        dueDate = dd;
        category = c;
        notes = nt;
    }





}
