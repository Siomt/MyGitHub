package com.zhouchatian.mygithub;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        Loadhtml loadhtml = new Loadhtml();
        loadhtml.execute();
    }
    //异步获取信息
private class Loadhtml extends AsyncTask<String, String, List<String>> {
        ProgressDialog bar;
         List list = new ArrayList();
        @Override
        protected List<String> doInBackground(String... params) {
            try {
                Connection connect = HttpConnection.connect(Constans.NetAddress+"/trending");
                connect.timeout(3000);
                connect.header("Accept-Encoding", "gzip,deflate,sdch");
                connect.header("Connection", "close");
                connect.validateTLSCertificates(false);
                connect.execute();
                Log.d("html", connect.get().html());

                Document content = Jsoup.parse(connect.get().html().toString());
                Elements divs = content.select("ol.repo-list");
                Document divcontions = Jsoup.parse(divs.toString());
                Elements element = divcontions.getElementsByTag("li");
                Log.d("element", element.toString());
                for(Element links : element) {
                    String title = links.getElementsByTag("a").text();
                    String link   = links.select("a").attr("href").trim();
                    String url  = Constans.NetAddress+link;
//                    ContentValues values = new ContentValues();
//                    values.put("Title", title);
//                    values.put("Url", url);
//                    db.insert("Cach", values);
                    list.add(title);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List result) {
            super.onPostExecute(result);
//            Log.d("doc", doc.toString().trim());
//            bar.dismiss();
//            ListItemAdapter adapter = new ListItemAdapter(context, usedatabase.getlist());
//            listmenu.setAdapter(adapter);
            AdapterList adapterList = new AdapterList(result , getApplicationContext());
            listView.setAdapter(adapterList);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            bar = new ProgressDialog(context);
//            bar.setMessage("正在加载数据····");
//            bar.setIndeterminate(false);
//            bar.setCancelable(false);
//            bar.show();
        }

    }
}
