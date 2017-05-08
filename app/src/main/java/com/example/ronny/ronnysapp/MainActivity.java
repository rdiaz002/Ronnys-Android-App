package com.example.ronny.ronnysapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String[] ChoiceList;
    private DrawerLayout drawer;
    private ListView listy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChoiceList = getResources().getStringArray(R.array.Choices);
        drawer =(DrawerLayout) findViewById(R.id.drawerlay);
        listy = (ListView) findViewById(R.id.left_drawer);
        listy.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,ChoiceList));
        listy.setOnItemClickListener(new DrawerItemClickListener());
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selector(position);
        }

        public void selector (int position){
            Fragment fragment;
            Log.v("Loghog",""+position);
            switch (position) {
                case 0:fragment = new Ball_Physics_Fragment();break;
                case 1:fragment = new Meme_Creator_Fragment();break;
                case 2:fragment = new Calculator_Fragment(); break;
                default:fragment = new default_fragment(); break;

            }
            FragmentManager fragman = getSupportFragmentManager();
            FragmentTransaction fragtrans = fragman.beginTransaction();
            fragtrans.replace(R.id.frame1,fragment)
                    .commit();


        }
    }
}
