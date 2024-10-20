# AIDL-Client-and-Server-App-communication


1. Server Demo

<uses-permission android:name="android.permisson.BIND_SERVICE"/>

-> enable to aidl buildfeature in gradle
-> Create AIDL interface
-> build manually AIDL then jenrate class and package
-> automatically the stub AIDL class
-> Create a service and bind the AIDL stub
-> then in manifest file define the AIDL service
-> Add permission for BIND_Service


2-> Client App

 <queries>
        <package android:name="com.example.serveraidl.IMyAidlInterface"/>
    </queries>

    <uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"/>
    <uses-permission android:name="android.permisson.BIND_SERVICE"/>


-> enable to aidl buildfeature in gradle
-> Create same AIDL interface and same package of server
-> Add permission of AIDL package
-> Open mainactivity
-> onCreate trigger Service 
-> Serviceconnection getting call back with iBinder
-> Create local aidl reference and assign in onServiceConnected



 
