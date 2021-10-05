package com.example.appmobil.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.appmobil.modelos.Utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void,Void,Void>  {
    private Context mContext;
    private Session mSession;
    private String mSubject;
    private String mMessage;
    private ProgressDialog mProgressDialog;
    private InternetAddress[] myList;


    public JavaMailAPI(Context mContext, String mSubject, String mMessage, InternetAddress[] myList) {
        this.mContext = mContext;
        this.mSubject = mSubject;
        this.mMessage = mMessage;
        this.myList = myList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = ProgressDialog.show(mContext,"Sending message", "Please wait...",false,false);
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mProgressDialog.dismiss();
        Toast.makeText(mContext,"Message Sent",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected Void doInBackground(Void... params) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.ferreyros.com.pe");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        mSession = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Utils.email, Utils.pass);
                    }
                });

        try {
            MimeMessage mm = new MimeMessage(mSession);
            mm.setFrom(new InternetAddress(Utils.email));
            mm.addRecipients(Message.RecipientType.TO ,myList);
            mm.setSubject(mSubject);
            mm.setText(mMessage);
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
