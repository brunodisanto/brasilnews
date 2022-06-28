package com.coderti.brasilnews;

import android.os.AsyncTask;
import android.util.Log;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlParser extends AsyncTask<List<String>, Void, List<Message>> {
    public XmlParserResponse delegate = null;
    List<String> urls;
    List<String> retorno;

    public volatile boolean parsingComplete = false;

    public XmlParser(){}

    public XmlParser(List<String> urls){
        for (String str : urls){
            log("multiple urls: "+str);
        }
        this.urls=urls;
    };

    public boolean isParsingComplete(){
        return parsingComplete;
    }

    public XmlParser(String url){
        this.urls = new ArrayList<String>();
        this.urls.add(url);
        log("single url: "+url);
    };

    public void readFeeds(){
        log("read feeds");
        for (String strUrl : urls){
            URL url;
            try{

                url = new URL(strUrl);
                log("reading url: "+strUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(20000);
                conn.setConnectTimeout(30000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                InputStream stream = conn.getInputStream();

                InputStream in = stream;
                StringBuilder sb=new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String read;
                while((read=br.readLine()) != null) {
                    //System.out.println(read);
                    sb.append(read);
                }
                br.close();
                in.close();
                log("read: " + sb.toString());
                stream.close();
                log("fechou stream");
                conn.disconnect();
                log("desconectou");
//                XMLSerializer xmlSerializer = new XMLSerializer();
//                JSON json = xmlSerializer.read(sb.toString());
//                log(json.toString(2));
                if (this.retorno == null){
                    retorno = new ArrayList<String>();
                }
                this.retorno.add(sb.toString());
                log("adicionou no obj de retorno");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.parsingComplete = true;
        log("setou true no parsing complete " + this.parsingComplete);
    }

    protected List doInBackground(List<String> ... params) {
        List<Message> lstMsgRetorno = new ArrayList<Message>();
        if (params[0] != null){
            this.urls = params[0];
            for (String str : this.urls){
                DomFeedParser domFeedParser = new DomFeedParser(str);
                lstMsgRetorno.addAll(domFeedParser.parse());
            }
            Collections.sort(lstMsgRetorno);
            for (Message m : lstMsgRetorno){
                if (this.retorno==null) this.retorno=new ArrayList<String>();
                this.retorno.add(m.getTitle());
            }
            return lstMsgRetorno;
        }else return null;

    }

    @Override
    protected void onPostExecute(List<Message> list) {
//        for (String str : list)
//        log("onPostExecute: isnull: " + str);
        delegate.processFinish(list);
    }

    private void log(String str){
        Log.i("XmlParser", str);
    }
}