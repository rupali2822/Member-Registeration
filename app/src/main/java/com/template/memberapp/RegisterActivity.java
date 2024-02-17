package com.template.memberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
TextInputEditText etMobile,etName,etSubFee,etDAmt,etLAmt,etCat,etAdhar;
Button submit;
RadioGroup roleGroup,genderGroup,maritalGroup;
TextView tvDob,tvDoj,tvDoMarri;

DatePickerDialog datePickerDialog;
Spinner spCast,spReligion;
    String date="",role="",gender="",marriStatus="", mobile="",name="",subFee="",dAmt="",lAmt="",cast="",religion="",category="",adhar="";

DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etMobile=findViewById(R.id.etMobile);
        etName=findViewById(R.id.etName);
        etSubFee=findViewById(R.id.etSubFee);
        etDAmt=findViewById(R.id.depAmt);
        etLAmt=findViewById(R.id.loanAmt);
        etCat=findViewById(R.id.etCat);
        etAdhar=findViewById(R.id.etAdhar);

        tvDob=findViewById(R.id.tvDob);
        tvDoj=findViewById(R.id.tvDoj);
        tvDoMarri=findViewById(R.id.tvDoMarri);

        submit=findViewById(R.id.btnSubmit);
        roleGroup=findViewById(R.id.roleGroup);
        genderGroup=findViewById(R.id.genderGroup);
        maritalGroup=findViewById(R.id.maritalGroup);

        spCast=findViewById(R.id.spCast);
        spReligion=findViewById(R.id.spReligion);

        String dt=getTodaysDate();
//        tvDoj.setText(dt);
//        tvDob.setText(dt);
//        tvDoMarri.setText(dt);

        databaseHelper=new DatabaseHelper(RegisterActivity.this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Cast, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spCast.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Religions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spReligion.setAdapter(adapter2);


        spCast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cast="";
                cast= (String) parent.getSelectedItem();
                Log.e("","cast.."+cast);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                religion="";
                religion= (String) parent.getSelectedItem();
                Log.e("","religion.."+religion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tvDoj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker(tvDoj);
            }
        });
        tvDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker(tvDob);
            }
        });
        tvDoMarri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker(tvDoMarri);
            }
        });

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male:
                        gender="male";
                        break;
                    case R.id.female:
                        gender="female";
                        break;
                    case R.id.other:
                        gender="other";
                        break;
                    default:
                        gender="other";


                }
            }
        });
        roleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.secretary:
                        role="secretary";
                        break;
                    case R.id.member:
                        role="member";
                        break;
                    default:
                        role="member";


                }
            }
        });

        maritalGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.married:
                        marriStatus="married";
                        break;
                    case R.id.unmarried:
                        marriStatus="unmarried";
                        break;
                    default:
                        marriStatus="other";

                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mobile=etMobile.getText().toString();
                name=etName.getText().toString();
                subFee=etSubFee.getText().toString();
                dAmt=etDAmt.getText().toString();
                lAmt=etLAmt.getText().toString();
                category=etCat.getText().toString();
                adhar=etAdhar.getText().toString();

                Log.e("","mob:"+mobile+" name:"+name+" subFee:"+subFee+" dAmt:"+dAmt);
                Log.e("","lAmt:"+lAmt+" cat:"+category+" adhar:"+adhar);
                Log.e(""," cast:"+cast+" religion:"+religion);

                String dojDate=tvDoj.getText().toString();
                String doBDate=tvDob.getText().toString();
                String doMDate=tvDoMarri.getText().toString();


                if(mobile.isEmpty()){
                    etMobile.setError("Enter mobile number");
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else if(name.isEmpty()){
                    etName.setError("Enter your name");
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                }else if(subFee.isEmpty()){
                    etSubFee.setError("Enter subscription amount");
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                }else if(dAmt.isEmpty()){
                    etDAmt.setError("Enter deposit amount");
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                }else if(lAmt.isEmpty()){
                    etLAmt.setError("Enter loan amount");
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                }else if(category.isEmpty()){
                    etCat.setError("Enter your category");
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(adhar.isEmpty()){
                    etAdhar.setError("Enter your adhar no.");
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                }
                else if(dojDate.equals("MM DD YYYY")){
                    Toast.makeText(RegisterActivity.this, "select date of joining", Toast.LENGTH_SHORT).show();
                }else if(doBDate.equals("MM DD YYYY")){
                    Toast.makeText(RegisterActivity.this, "select date of birth", Toast.LENGTH_SHORT).show();

                }else if(doMDate.equals("MM DD YYYY")){
                    Toast.makeText(RegisterActivity.this, "select date of Marriage", Toast.LENGTH_SHORT).show();

                }else if(gender.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "select gender", Toast.LENGTH_SHORT).show();

                }else if(role.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "select your role", Toast.LENGTH_SHORT).show();

                }else if(marriStatus.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "select marrital status", Toast.LENGTH_SHORT).show();

                }else if(cast.contains("Select")){
                    Toast.makeText(RegisterActivity.this, "select caste ", Toast.LENGTH_SHORT).show();

                }else if(religion.contains("Select")){
                    Toast.makeText(RegisterActivity.this, "select Religion ", Toast.LENGTH_SHORT).show();

                }


                else {
                    dojDate = convertDateIntFormat(dojDate);
                    Log.e("", "dojDate:" + dojDate);

                    doBDate = convertDateIntFormat(doBDate);
                    Log.e("", "doBDate:" + doBDate);

                    doMDate = convertDateIntFormat(doMDate);
                    Log.e("", "doMDate:" + doMDate);

                    Log.e("", "role:" + role + " marri:" + marriStatus + " gender:" + gender);


                    Boolean status = databaseHelper.registerMember(name, mobile, role, subFee, dAmt, lAmt, gender, doBDate, dojDate, marriStatus, doMDate, cast, religion, category, adhar);
                    Log.e("", "insert status" + status);

                    if (status) {
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Failed to submit data", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });









        getSupportActionBar().setIcon(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("Member Registeration");


    }

    private String convertDateIntFormat(String txt) {
        String mDate = null;
        try {
            String selectedmonth = null;
            String month;
            String day;
            String year;
            String array[] = txt.split(" ");
            day = array[0];
            month = array[1];
            year = array[2];
            // Log.e("", "billDate month:" + month);

            Date dt = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            int mn = cal.get(Calendar.MONTH);
            System.out.println(mn == Calendar.FEBRUARY);
            mn = mn + 1;
            //  Log.e("", "billDate month....:" + mn);
            if (String.valueOf(mn).length() == 1) {
                selectedmonth = "0".concat(String.valueOf(mn));
                //  Log.e("", "billDate month.1digit...:" + selectedmonth);
            } else {
                selectedmonth = String.valueOf(mn);
            }
            if (day.length() == 1) {
                day = "0".concat(day);
                //  Log.e("", "billDate day.1digit...:" + day);
            }

//            mDate = day + "-" + selectedmonth + "-" + year;
            mDate = year + "-" + selectedmonth + "-" + day;

            Log.e("", "return date:" + mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return mDate;
    }


    private String getTodaysDate() {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);

        String monthString = String.valueOf(month);
        if (monthString.length()==1){
            monthString= "0"+monthString;
        }
        Log.e("","c month:"+monthString);
        //  selectedDate=year+"-"+monthString+"-"+day;
        return makeDateToString(day,month,year);
    }


    private String initDatePicker(TextView btnDate) {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String monthString = String.valueOf(month);
                if (monthString.length()==1){
                    monthString= "0"+monthString;
                }
                Log.e("","month:"+monthString);
                date = makeDateToString(dayOfMonth, month, year);

                Log.e("","set selected:"+date);
                btnDate.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

        datePickerDialog.show();
        return date;
    }

    private String makeDateToString(int day, int month, int year) {
        return day+" "+getMonthFormat(month)+" "+year;

//        return getMonthFormat(month)+" "+day+" "+year;
    }

    private String getMonthFormat(int month) {
        if (month==1)
            return "JAN";
        if (month==2)
            return "FEB";
        if (month==3)
            return "MAR";
        if (month==4)
            return "APR";
        if (month==5)
            return "MAY";
        if (month==6)
            return "JUN";
        if (month==7)
            return "JUL";
        if (month==8)
            return "AUG";
        if (month==9)
            return "SEP";
        if (month==10)
            return "OCT";
        if (month==11)
            return "NOV";
        if (month==12)
            return "DEC";

//default
        return "JAN";
    }


}