package com.template.memberapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_members";
    //database version
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tb_member_master";

    MemberModel memberModel;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        //creating table

        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY,  mname TEXT ,mobile int unique, role TEXT, subFee  int ,depAmt int,loanAmt int,gender TEXT,DOB TEXT,DOJ TEXT,marriStatus TEXT,DOM TEXT,caste TEXT, religion TEXT ,category TEXT, adhar int )";
        db.execSQL(query);

        Log.e("","table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean registerMember(String mName,String mob,String rol,String subFee,String dep, String loan,String gender,
    String dob,String doj,String mstatus,String dom,String cast,String relign,String catgory,String adr
    ){
        Log.e("Databasehelper", "inserting data");
        SQLiteDatabase database=this.getWritableDatabase();


        ContentValues values=new ContentValues();
            values.put("mname",mName);
            values.put("mobile",mob);
            values.put("role",rol);
            values.put("subFee",subFee);
            values.put("depAmt",dep);
            values.put("loanAmt",loan);
            values.put("gender",gender);
            values.put("DOB",dob);
            values.put("DOJ",doj);
            values.put("marriStatus",mstatus);
            values.put("DOM",dom);
            values.put("caste",cast);
            values.put("religion",relign);
            values.put("category",catgory);
            values.put("adhar",adr);

            database.insert(TABLE_NAME,null,values);

        Log.e("Databasehelper", "data inserted");
        database.close();
        return true;
    }

    public ArrayList<MemberModel> getMemberDtFromDb(){
        Log.e("Databasehelper", "getting data ");
        SQLiteDatabase database = this.getReadableDatabase();
        // MovieModel model = new MovieModel();
        ArrayList<MemberModel> result = new ArrayList<>();
        try {
            Cursor c = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            while (c.moveToNext()) {

                String name = c.getString(c.getColumnIndex("mname"));
                String mobile = c.getString(c.getColumnIndex("mobile"));
                String role = c.getString(c.getColumnIndex("role"));
                String subFee = c.getString(c.getColumnIndex("subFee"));
                String depAmt = c.getString(c.getColumnIndex("depAmt"));
                String loanAmt = c.getString(c.getColumnIndex("loanAmt"));
                String gender = c.getString(c.getColumnIndex("gender"));
                String DOB = c.getString(c.getColumnIndex("DOB"));
                String DOJ = c.getString(c.getColumnIndex("DOJ"));
                String marriStatus = c.getString(c.getColumnIndex("marriStatus"));
                String DOM = c.getString(c.getColumnIndex("DOM"));
                String caste = c.getString(c.getColumnIndex("caste"));
                String religion=c.getString(c.getColumnIndex("religion"));
                String category=c.getString(c.getColumnIndex("category"));
                String adhar=c.getString(c.getColumnIndex("adhar"));


                memberModel=new MemberModel();
                memberModel.setmName(name);
                memberModel.setmContact(mobile);
                memberModel.setRole(role);
                memberModel.setSubFee(subFee);
                memberModel.setDepAmt(depAmt);
                memberModel.setLoanAmt(loanAmt);
                memberModel.setGender(gender);
                memberModel.setDob(DOB);
                memberModel.setDoj(DOJ);
                memberModel.setMarritalStatus(marriStatus);
                memberModel.setDoMariage(DOM);
                memberModel.setCast(caste);
                memberModel.setReligion(religion);
                memberModel.setCategory(category);
                memberModel.setAdharCard(adhar);

                result.add(memberModel);
                Log.e("","retrived data"+result.size());
            }
            c.close();
            database.close();


        }catch (Exception e){
            e.printStackTrace();
            Log.e("","error while retriving "+e.getMessage());

        }
        return result;

    }

/*
    public void registerMember2(ArrayList<MemberModel> list){
        Log.e("Databasehelper", "inserting data");

        SimpleDateFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        }
        SQLiteDatabase database=this.getWritableDatabase();

//        database.delete(TABLE_NAME,"ID="+1,null);
       // Log.e("Databasehelper", "existing deleted ");


        for(int i=0 ; i<list.size(); i++){
            ContentValues values=new ContentValues();
            values.put("mname",list.get(i).getmName());
            values.put("mobile",list.get(i).getmContact());
            values.put("role",list.get(i).getRole());
            values.put("subFee",list.get(i).getSubFee());
            values.put("depAmt",list.get(i).getDepAmt());
            values.put("loanAmt",list.get(i).getLoanAmt());
            values.put("gender",list.get(i).getGender());

            String dob=list.get(i).getDob();
            String dateInString = "7-Jun-2013";
            Date date = formatter.parse(dateInString);

            values.put("DOB",list.get(i).getDob());
            values.put("DOJ",list.get(i).getDoj());
            values.put("marriStatus",list.get(i).getMarritalStatus());
            values.put("DOM",list.get(i).getDoMariage());
            values.put("caste",list.get(i).getCast());
            values.put("religion",list.get(i).getReligion());
            values.put("category",list.get(i).getCategory());
            values.put("adhar",list.get(i).getAdharCard());

            database.insert(TABLE_NAME,null,values);
        }



        Log.e("Databasehelper", "data inserted");
        database.close();

    }
*/
}
