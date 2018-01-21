package com.lab409.crowdingsourcing.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Ni on 2016/11/2.
 */

public class HttpRequest {
    String url;
    URL fullUrl;
    HttpURLConnection connection;

    public HttpRequest(String u) throws MalformedURLException, IOException {
        url = u;
    }

    public void setGetParam(Map<String, String> param) throws IOException {
        if (param != null) {
            String u = url;
            u += "?";
            int index = 0;
            for (String key : param.keySet()) {
                u += key + "=" + param.get(key);
                if (index != param.size() - 1) {
                    u += "&";
                }
                index++;
            }
            url = u;
        }
        fullUrl = new URL(url);
        connection = (HttpURLConnection) fullUrl.openConnection();
        connection.setRequestMethod("GET");
    }

    public void setPostParam(Map<String, String> param, String token) throws IOException {
        fullUrl = new URL(url);
        connection = (HttpURLConnection) fullUrl.openConnection();
        connection.setRequestMethod("POST");

        connection = (HttpURLConnection) fullUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);

        connection.setRequestProperty("Content-Type", "application/json");
        if (token != null) {
            connection.addRequestProperty("PRIVATE-TOKEN", token);
        }
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());

//        String params = "{"+
//                "\"email\":\"1gqerF@QQ.COM\","+
//                "\"password\":\"aASDGASD\","+
//                "\"username\":\"fasffasd\","+
//                "\"name\":\"c\""+
//                "}";

        String params = "{";
        int index = 0;
        for (String key : param.keySet()) {
            params += "\"" + key + "\":\"" + param.get(key) + "\"";
            if (index != param.size() - 1) {
                params += ",";
            } else {
                params += "}";
            }
            index++;
        }
        System.out.println(params);
        out.write(params);
        out.flush();
        out.close();
        System.out.println("test");
    }

    public String getResponse() throws IOException {
        InputStream in = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String strLine, strResponse = "";
        while ((strLine = reader.readLine()) != null) {
            strResponse += strLine + "\n";
        }
        return strResponse;
    }

    public int getResponseCode() throws IOException {
        return connection.getResponseCode();
    }

    public int getGitlabId() throws IOException {
        String temp = getResponse();
        int index = temp.indexOf("\"id\":");
        String number = "";
        while (temp.charAt(index + 5) != ',') {
            number += temp.charAt(index + 5);
            index++;
        }
        return Integer.valueOf(number);
    }
}


