package com.example.parshva.myapplication;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.net.URLEncoder;


public class BackGroundTask extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {

        String login_url="https://parshva.000webhostapp.com/login.php";
        String method=params[0];
        String responce="";
        if (method.equals("login"))
        {
            String login_name = params[1];
            String login_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data= URLEncoder.encode("item_id","UTF-8")+"="+ URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_id","UTF-8")+"="+ URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String line="";
                while ((line =bufferedReader.readLine())!=null)
                {
                    responce= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return responce;

            }
            catch (MalformedURLException e)
            {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responce;

    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);

    }
    @Override
    protected void onPostExecute(String result) {



    }

}
