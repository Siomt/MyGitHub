package com.zhouchatian.mygithub.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhouchatian.mygithub.AdapterList;
import com.zhouchatian.mygithub.ArticleActivity;
import com.zhouchatian.mygithub.Constans;
import com.zhouchatian.mygithub.R;
import com.zhouchatian.mygithub.TrendingBean;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Mr.Robot on 2017/5/11.
 * GitHub:https://github.com/TheSadFrog
 */

public class TrendingFragment extends Fragment{

    private ListView listView;
    List<TrendingBean> list = new ArrayList();
    private Activity mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        mActivity = getActivity();
        listView = (ListView) view.findViewById(R.id.lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ArticleActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                startActivity(intent);
            }
        });
        TrendingFragment.LoadHtml loadHtml = new TrendingFragment.LoadHtml();
        loadHtml.execute();
        return view;
    }


    //异步获取信息
    private class LoadHtml extends AsyncTask<String, String, List<TrendingBean>> {
        ProgressDialog bar;
        //List list = new ArrayList();
        @Override
        protected List<TrendingBean> doInBackground(String... params) {
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
                    //String title = links.getElementsByTag("a").text();
                    String title  = links.select("h3").text();
                    String synopsis  = links.select("div.py-1").text();
                    String programmingLanguage = links.select("span.mr-3").text();
                    String totalStar = links.getElementsByTag("a").text();
                    String todayStar = links.select("span.float-right").text();
                    String link = links.select("a").attr("href").trim();
                    String url  = Constans.NetAddress+link;

//                    ContentValues values = new ContentValues();
//                    values.put("Title", title);
//                    values.put("Url", url);
//                   db.insert("Cach", values);
                    TrendingBean bean = new TrendingBean();
                    bean.setTitle(title);
                    bean.setSynopsis(synopsis);
                    bean.setProgrammingLanguage(programmingLanguage);
                    bean.setTotalStar(totalStar);
                    bean.setTodayStar(todayStar);
                    bean.setUrl(url);
                    list.add(bean);
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
            if(mActivity == null){
                mActivity = getActivity();
            }
            AdapterList adapterList = new AdapterList(result , mActivity);
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
