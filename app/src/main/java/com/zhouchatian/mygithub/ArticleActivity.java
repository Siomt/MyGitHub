package com.zhouchatian.mygithub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
/**
 * 详情界面
 * Created by Mr.Robot on 2017/5/5.
 * https://github.com/TheSadFrog
 */

public class ArticleActivity extends AppCompatActivity {

    private WebView webView;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Intent intent = getIntent();
        url =  intent.getStringExtra("url");
        if(!url.isEmpty()){
            LoadHtml loadhtml = new LoadHtml();
            loadhtml.execute();
        }
        webView = (WebView) findViewById(R.id.web_view_article);
    }
    //异步获取信息
    private class LoadHtml extends AsyncTask<String, String, String> {
        ProgressDialog bar;
        String readme;
        @Override
        protected String doInBackground(String... params) {
            try {
                Connection connect = HttpConnection.connect(url);
                connect.timeout(3000);
                connect.header("Accept-Encoding", "gzip,deflate,sdch");
                connect.header("Connection", "close");
                connect.validateTLSCertificates(false);
                connect.execute();
                Log.d("html", connect.get().html());

                Document content = Jsoup.parse(connect.get().html().toString());
                Elements divs = content.select("div#readme");
                readme  = divs.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return readme;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(webView != null){
                webView.loadData(result,"text/html","UTF-8");
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
