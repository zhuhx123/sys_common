package com.ivymei.system.common.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kandy on 2016/12/17.
 */
public class HttpPluginTest {

    public static String postByJson(String url, String jsonParam) {
        DataOutputStream outputStream=null;
        BufferedReader br=null;
        String result=null;
        HttpURLConnection connection =null;
        try {
            URL realUrl = new URL(url);
             connection = (HttpURLConnection) realUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
            outputStream=new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonParam);
            outputStream.flush();

            br=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

            String line=null;
            while ((line=br.readLine())!=null){
                result+=line;
            }
            System.out.println("result:"+result);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(outputStream!=null){
                    outputStream.close();
                }
                if(br!=null){
                    br.close();
                }
                if(connection!=null){
                    connection.disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
