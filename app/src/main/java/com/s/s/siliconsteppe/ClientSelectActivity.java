package com.s.s.siliconsteppe;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.s.s.siliconsteppe.wifimanager.ClientScanResult;
import com.s.s.siliconsteppe.wifimanager.WifiApiManager;

import java.util.ArrayList;

public class ClientSelectActivity extends Activity {


    static final int SocketServerPORT = 8080;
    Intent i;
    TextView textPort;
    private String username;
    private WifiApiManager wifiApManager;
    private String[] values;

    //-------------- Client
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_sharing);

        wifiApManager = new WifiApiManager(this);
        listView = (ListView) findViewById(R.id.listView2);

        username = (String) getIntent().getExtras().get("name");


        SelectClient sendImage = new SelectClient();
        sendImage.execute((Void) null);

    }


    //Asynchronous class to select Client
    private class SelectClient extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            final ArrayList<ClientScanResult> clients = wifiApManager.getClientList(false);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    ClientScanResult clientScanResult;
                    values = new String[clients.size()];
                    for (int i = 0; i < clients.size(); i++) {
                        clientScanResult = clients.get(i);
                        values[i] = "IpAddress: " + clientScanResult.getIpAddress();//store client's ip addresses in values array
                    }
                    adapter = new ArrayAdapter<>(ClientSelectActivity.this, R.layout.list_white_text, R.id.list_content, values);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            i = new Intent(ClientSelectActivity.this, PrivateChatActivity.class);
                            i.putExtra("ipAddress", clients.get(position).getIpAddress());
                            i.putExtra("name", username);
                            startActivity(i);//start activity from this activity to PrivateChatActivity

                        }
                    });
                }
            });
            return true;
        }
    }
}