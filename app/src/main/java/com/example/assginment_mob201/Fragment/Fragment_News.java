package com.example.assginment_mob201.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.assginment_mob201.Adapter.ItemListViewNewsAdapter;
import com.example.assginment_mob201.Entity.NewXML;
import com.example.assginment_mob201.R;
import com.example.assginment_mob201.Until.Server;
import com.example.assginment_mob201.XMLParse.XMLParseNews;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Fragment_News extends Fragment {
    private ListView lvNews;
    private List<NewXML> xmlParseNewsList;
    private ItemListViewNewsAdapter itemListViewNewsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        this.initByViewId(view);
        this.initListNewXML();
        this.initAsyncTask();
        this.initListView();
        return view;
    }

    private void initAsyncTask() {
        AsyncTask<String, Void, String> asyncTask = new RSSFeed().execute(Server.getUrlXMLNews());
    }

    private class RSSFeed extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Đang tải ... ");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLParseNews xmlParseNews = new XMLParseNews();
            try {
                Document document = xmlParseNews.getDocument(s);
                NodeList nodeList = document.getElementsByTagName("item");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    String title = xmlParseNews.getValue(element, "title");
                    String link = xmlParseNews.getValue(element, "link");
                    String pubDate = xmlParseNews.getValue(element, "pubDate");
                    Log.d("description", pubDate);
                    NewXML newXML = new NewXML(title, pubDate, link);
                    xmlParseNewsList.add(newXML);
                }
                itemListViewNewsAdapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
    }

    private void initListView() {
        this.itemListViewNewsAdapter = new ItemListViewNewsAdapter(getContext(), this.xmlParseNewsList);
        this.lvNews.setAdapter(itemListViewNewsAdapter);
        this.lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewXML newXML = xmlParseNewsList.get(position);
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                Fragment_WebView_News fragmentWebViewNews = new Fragment_WebView_News();
                Bundle bundle = new Bundle();
                bundle.putString("link", newXML.getLink());
                fragmentWebViewNews.setArguments(bundle);
                appCompatActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_fragmentContainer, fragmentWebViewNews)
                        .addToBackStack(fragmentWebViewNews.getClass().getName())
                        .commit();
            }
        });
    }


    private void initListNewXML() {
        this.xmlParseNewsList = new ArrayList<>();
    }

    private void initByViewId(View view) {
        this.lvNews = view.findViewById(R.id.lv_news);
    }

}