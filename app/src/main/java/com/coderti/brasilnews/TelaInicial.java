package com.coderti.brasilnews;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity implements XmlParserResponse {
    XmlParser xmlParser = new XmlParser();
    private SwipeRefreshLayout swipeContainer;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private MessageAdapter messageAdapter;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tela_inicial);
//        ListView lista = (ListView) findViewById(R.id.lista);
        //http://g1.globo.com/dynamo/rss2.xml
        //http://rss.home.uol.com.br/index.xml
        List<String> urls = new ArrayList<String>();
        urls.add("http://g1.globo.com/dynamo/rss2.xml");
        urls.add("http://rss.home.uol.com.br/index.xml");

        xmlParser.delegate=this;
        xmlParser.execute(urls);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
//        XmlParser xmlParser = new XmlParser(urls);
//        xmlParser.readFeeds();
//        while (!xmlParser.isParsingComplete());
//
//        List<Rss> listRss = xmlParser.getRss();
//        List<String> listaStr = new ArrayList<String>();
//        if (listRss != null) {
//            for (Rss rss : listRss) {
//                for (Item item : rss.channel.items) {
//                    listaStr.add(item.link);
//                }
//            }
//        }
//        String[] noticias = {"BRUNO", "KARLA", "LUCAS", "LAILA","BRUNO", "KARLA", "LUCAS", "LAILA","BRUNO", "KARLA", "LUCAS", "LAILA","BRUNO", "KARLA", "LUCAS", "LAILA"};
//        String[] noticias = listaStr.toArray(new String[listaStr.size()]);
//        lista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noticias));
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                List<String> urls = new ArrayList<String>();
                urls.add("http://g1.globo.com/dynamo/rss2.xml");
                urls.add("http://rss.home.uol.com.br/index.xml");
                xmlParser.execute(urls);
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_inicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "TelaInicial Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.coderti.brasilnews/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "TelaInicial Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.coderti.brasilnews/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }

    public void processFinish(List<Message> output) {
        log("processFinish : output is null? : " + (output == null));
        List<String> listaStr = new ArrayList<String>();

        String[] noticias = new String[listaStr.size()];
        for (int i=0; i<listaStr.size();i++){
            noticias[i] = listaStr.get(i);
        }
        lista = (ListView) findViewById(R.id.lista);
        this.messageAdapter = new MessageAdapter(this, R.layout.row, output);
        lista.setAdapter(this.messageAdapter);
//        lista.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noticias));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message m = (Message) lista.getItemAtPosition(position);
                String url = m.getLink().toString();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setPackage("com.android.chrome");
                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    // Chrome is probably not installed
                    // Try with the default browser
                    i.setPackage(null);
                    startActivity(i);
                }
            }
        });
        if (swipeContainer.isRefreshing()) {
            swipeContainer.setRefreshing(false);
        }
        xmlParser = new XmlParser();
        xmlParser.delegate=this;
    }

    public void log(String str){
        Log.i("TelaInicial", str);
    }

    private class MessageAdapter extends ArrayAdapter<Message> {

        private List<Message> items;

        public MessageAdapter(Context context, int textViewResourceId, List<Message> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row, null);
            }
            Message m = items.get(position);
            if (m != null) {
                TextView tt = (TextView) v.findViewById(R.id.toptext);
//                TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                if (tt != null) {
                    tt.setText(m.getTitle());                            }
//                if(bt != null){
//                    bt.setText(m.getDescription());
//                }
            }
            return v;
        }
    }
}


