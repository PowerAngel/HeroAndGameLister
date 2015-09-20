package com.example.golvmopp.heroandgamelister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpecificGameActivity extends AppCompatActivity
{
    ArrayList<Match> ArrayMatch = new ArrayList<Match>();
    ArrayList<String> ArrayGameStats = new ArrayList<String>();
    ArrayList<Hero> HeroesArray = new ArrayList<Hero>();
    int position;
    TextView textViewMatchID = null;
    private ArrayAdapter<String> adapter;
    String myLogTag = "MyTag";
    String winner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_game);

        ArrayMatch = getIntent().getParcelableArrayListExtra("ArrayMatch");
        HeroesArray = getIntent().getParcelableArrayListExtra("HeroesArray");
        position = getIntent().getIntExtra("position", 0);

        ListView listView = (ListView) findViewById(R.id.listViewGameStats);

        if(ArrayMatch.get(position).getRadiantWin().equals("false"))
        {
            winner = "Dire";
        }
        else if(ArrayMatch.get(position).getRadiantWin().equals("true"))
        {
            winner = "Radiant";
        }

        int minutes = ArrayMatch.get(position).getDuration()/60;
        int seconds = ArrayMatch.get(position).getDuration() - (minutes * 60);
        String temp = "Match ID: " + ArrayMatch.get(position).getMatchID() +
                "\n || Duration: " + minutes + " minutes " + seconds + " seconds" +
                "\n || Winner: " + winner;
        ArrayGameStats.add(temp);
        ArrayGameStats.add("RADIANT TEAM: ");

        Log.v(myLogTag, "PlayerArray.size(): " + ArrayMatch.get(position).getPlayerArray().size());
        String heroName = null;

        for(int i = 0; i < ArrayMatch.get(position).getPlayerArray().size(); i++)
        {
            for(int k = 0; k < HeroesArray.size(); k++)
            {


                if(ArrayMatch.get(position).getPlayerArray().get(i).getHero().equals(HeroesArray.get(k).getID()))
                {
                    heroName = HeroesArray.get(k).getName();
                }
            }

            Log.v(myLogTag, "i: " + i + " || AccountID: " + ArrayMatch.get(position).getPlayerArray().get(i).getAccountID());
            temp = "Account ID: " + ArrayMatch.get(position).getPlayerArray().get(i).getAccountID() +
                    "\n || Hero: " + heroName +
                    "\n || K/D/A: " + ArrayMatch.get(position).getPlayerArray().get(i).getKills() +
                    "/" + ArrayMatch.get(position).getPlayerArray().get(i).getDeaths() +
                    "/" + ArrayMatch.get(position).getPlayerArray().get(i).getAssists() +
                    "\n || Level: " + ArrayMatch.get(position).getPlayerArray().get(i).getLevel() +
                    "\n || XPM: " + ArrayMatch.get(position).getPlayerArray().get(i).getXpm() +
                    " || GPM: " + ArrayMatch.get(position).getPlayerArray().get(i).getGpm() +
                    "\n || Hero damage: " + ArrayMatch.get(position).getPlayerArray().get(i).getHeroDMG() +
                    "\n || Tower damage: " + ArrayMatch.get(position).getPlayerArray().get(i).getTowerDMG() +
                    "\n || Hero healed: " + ArrayMatch.get(position).getPlayerArray().get(i).getHeroHealing() +
                    "\n || Last hits: " + ArrayMatch.get(position).getPlayerArray().get(i).getLasthits() +
                    " || Denies: " + ArrayMatch.get(position).getPlayerArray().get(i).getDenies();

            ArrayGameStats.add(temp);
            if(i+1 == (ArrayMatch.get(position).getPlayerArray().size())/2)
            {
                ArrayGameStats.add("DIRE TEAM: ");
            }
        }

        adapter = new ArrayAdapter<String>(this, R.layout.custom_textview, ArrayGameStats);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                try
                {

                }
                catch(Exception e)
                {
                    Log.v(myLogTag, "Nu gick något fel");
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_specific_game, menu);
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
}
