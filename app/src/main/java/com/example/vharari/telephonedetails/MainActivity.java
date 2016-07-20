package com.example.vharari.telephonedetails;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView1 = (TextView) findViewById(R.id.textView1);

        TelephonyManager  tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        String IMEINumber = tm.getDeviceId();
        String subscriberID = tm.getSubscriberId();
        String SIMSerialNo = tm.getSimSerialNumber();
        String SIMCountryISO = tm.getSimCountryIso();
        String SIMOperator = tm.getSimOperator();
        String SIMNetOperator = tm.getNetworkOperator();

        String strphoneType="";

        int phoneType=tm.getPhoneType();

        switch (phoneType)
        {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType="CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType="GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType="NONE";
                break;
        }
//        String
        boolean isRoaming=tm.isNetworkRoaming();
//        boolean canChangeDtmfToneLength = tm.canChangeDtmfToneLength();
        int getCallState = tm.getCallState();
        int getDataActivity = tm.getDataActivity();
        int getDataState = tm.getDataState();
//        String sVersion =

        String info = "Phone Details : \n";
        info += "\n IMEI Number : "+IMEINumber;
        info += "\n subscriberID : "+subscriberID;
        info += "\n SIMSerialNo : "+SIMSerialNo;
        info += "\n SIMCountryISO : "+SIMCountryISO;
        info += "\n SIMOperator : "+SIMOperator;
        info += "\n SIMNetOperator : "+SIMNetOperator;
        info += "\n strphoneType : "+strphoneType;
        info += "\n In Roaming? :"+isRoaming;
        info += "\n Call State : "+getCallState;
        info += "\n getDataActivity :"+getDataActivity;
        info += "\n getDataState : "+getDataState;
        info += "\n Software Version : "+tm.getDeviceSoftwareVersion();
        info += "\n getLine1Number : "+tm.getLine1Number();
        info += "\n getNetworkOperatorName : "+tm.getNetworkOperatorName();
        info += "\n getSimCountryIso : "+tm.getSimCountryIso();
        info += "\n getVoiceMailNumber : "+tm.getVoiceMailNumber();
        info += "\n getPhoneType : "+tm.getPhoneType();
        info += "\n getSimState : "+tm.getSimState();
        info += "\n isSmsCapable : "+tm.isSmsCapable();
//        info += "\n isWorldPhone : "+tm.isWorldPhone();
//        info += "\n isTtyModeSupported : "+tm.isTtyModeSupported();
        info += "\n isVoiceCapable : "+tm.isVoiceCapable();

        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, "PhoneInfo");
        try {
            file.createNewFile();
           /* FileOutputStream out = new FileOutputStream(file);
            out.write(info);*/
            PrintWriter out = new PrintWriter(file);
            out.write(info);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "File Path : "+filepath+" File exists : "+file.exists(), Toast.LENGTH_LONG).show();



        textView1.setText(info);

    }
}
