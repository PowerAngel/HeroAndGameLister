package com.example.golvmopp.heroandgamelister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LatestHeroMatchesActivity extends AppCompatActivity
{
    private ArrayAdapter<String> adapter;
    String myLogTag = "MyTag";
    String winner = null;
    ArrayList<Match> ArrayMatch = new ArrayList<Match>();
    ArrayList<Hero> HeroesArray = new ArrayList<Hero>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_hero_matches);

        ArrayMatch = getIntent().getParcelableArrayListExtra("ArrayMatches");
        ArrayList<String> ArrayGames = new ArrayList<String>();

        HeroesArray = getIntent().getParcelableArrayListExtra("HeroesArray");

        for(int i = 0; i < ArrayMatch.size(); i++)
        {
            if(ArrayMatch.get(i).getRadiantWin().equals("false"))
            {
                winner = "Dire";
            }
            else if(ArrayMatch.get(i).getRadiantWin().equals("true"))
            {
                winner = "Radiant";
            }

            int minutes = ArrayMatch.get(i).getDuration()/60;
            int seconds = ArrayMatch.get(i).getDuration() - (minutes * 60);

            String temp = "Match ID: " + ArrayMatch.get(i).getMatchID() +
                    "\n || Duration: " + minutes + " minutes " + seconds + " seconds" +
                    "\n || Winner: " + winner;
            ArrayGames.add(temp);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.custom_textview, ArrayGames);
        ListView listView = (ListView) findViewById(R.id.listViewGames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    //Gamet väljs
                    Intent intent = new Intent(getApplicationContext(), SpecificGameActivity.class);
                    intent.putParcelableArrayListExtra("ArrayMatch", ArrayMatch);
                    intent.putParcelableArrayListExtra("HeroesArray", HeroesArray);
                    intent.putExtra("position", position);
                    startActivity(intent);

                } catch (Exception e) {
                    Log.v(myLogTag, "Nu gick något fel");
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_latest_hero_matches, menu);
        return true;
    }

    /*@Override
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
    }*/
}
