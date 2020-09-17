package com.example.androidpractice;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Mission09_MainFragement extends Fragment {

   EditText nameInput;
   EditText ageInput;

   Button birthButton;

   public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

   Date selectDate;

   public Mission09_MainFragement(){

   }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState)
   {
       ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mission09_fragment,container,false);

       nameInput = rootView.findViewById(R.id.usernameInput09);
       ageInput = rootView.findViewById(R.id.userAgeInput09);

       birthButton = rootView.findViewById(R.id.birthButton09);
       birthButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDateDialog();
           }
       });
       Button saveButton = rootView.findViewById(R.id.saveButton09);
       saveButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                String nameStr = nameInput.getText().toString();
                String ageStr = ageInput.getText().toString();
                String birthStr = birthButton.getText().toString();

               Toast.makeText(getContext(),"이름"+nameStr+"나이"+ageStr+"생년월일"+birthStr,Toast.LENGTH_SHORT).show();
           }
       });

       Date curDate = new Date();
       setSelectedDate(curDate);

       return rootView;
   }
   private void showDateDialog(){
       String birthDateStr = birthButton.getText().toString();

       Calendar calendar = Calendar.getInstance();
       Date curBirthDate = new Date();
       try{
           curBirthDate = dateFormat.parse(birthDateStr);
       }catch (Exception ex){
           ex.printStackTrace();
       }

       calendar.setTime(curBirthDate);

       int curYear = calendar.get(Calendar.YEAR);
       int curMonth = calendar.get(Calendar.MONTH);
       int curDay = calendar.get(Calendar.DAY_OF_MONTH);

       DatePickerDialog dialog = new DatePickerDialog(getContext(), birthDateSetListener,curYear,curMonth,curDay);
       dialog.show();

   }
   private DatePickerDialog.OnDateSetListener birthDateSetListener = new DatePickerDialog.OnDateSetListener() {
       @Override
       public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
           Calendar selectedCalendar = Calendar.getInstance();
           selectedCalendar.set(Calendar.YEAR , year);
           selectedCalendar.set(Calendar.MONTH, month);
           selectedCalendar.set(Calendar.DATE, dayOfMonth);

           Date curDate = selectedCalendar.getTime();
           setSelectedDate(curDate);
       }
   };

   private void setSelectedDate(Date curDate){
       selectDate = curDate;

       String selectedDateStr = dateFormat.format(curDate);
       birthButton.setText(selectedDateStr);
   }
}
