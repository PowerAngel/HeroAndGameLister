package com.example.golvmopp.heroandgamelister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapterHeroInfo;
    private Spinner spinner;
    String myLogTag = "MyTag";
    TextView textViewHeroName = null;
    TextView textViewLoad = null;
    ListView listViewHeroInfo = null;
    ArrayList<Hero> HeroesArray = new ArrayList<Hero>();
    ArrayList<Match> ArrayMatches = new ArrayList<Match>();
    int ArrayCount = 0;
    String heroID = null;
    private long mLastClickTime = 0;

    public final static String EXTRA_MESSAGE = "com.example.webapitutorial.MESSAGE";
    public final static String apiURLMatchHistory = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?";
    public final static String apiURLMatchDetails = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?";
    public final static String APIkey = "key=43385BD96392D0F8127976F34606D55F";
    public final static String APIuserID = "account_id=";
    public final static String APIheroID = "hero_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLoad = (TextView) this.findViewById(R.id.textViewLoad);
        listViewHeroInfo = (ListView) this.findViewById(R.id.listViewHeroInfo);

        try
        {
            HeroesArray = loadHeroes();
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

        ArrayList<String> tempArray = new ArrayList<String>();
        for(int i = 0; i < HeroesArray.size(); i++)
        {
            tempArray.add(HeroesArray.get(i).getName());
        }

        String[] HeroesSpinnerArray = tempArray.toArray(new String[tempArray.size()]);

        setUpSpinner(HeroesSpinnerArray);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void setUpSpinner(String[] HeroesArray)
    {
        adapter = new ArrayAdapter<String>(this, R.layout.custom_textview, HeroesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinnerHeroes);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
    }

    private class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            showHeroInfo(position);

            heroID = HeroesArray.get(position).getID();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {
            // TODO Auto-generated method stub

        }

    }

    public void showHeroInfo(int position)
    {
        ArrayList<String> ArrayHeroInfo = new ArrayList<String>();
        String temp = "Hero name: " + HeroesArray.get(position).getName() +
                "\n || Main attribute: " + HeroesArray.get(position).getMainAttribute() +
                "\n || Movementspeed: " + HeroesArray.get(position).getMovementspeed() +
                "\n || Range: " + HeroesArray.get(position).getRange() +
                "\n\n || Start health: " + HeroesArray.get(position).getStartHealth() +
                "\n || Start mana: " + HeroesArray.get(position).getStartMana() +
                "\n || Start damage: " + HeroesArray.get(position).getStartDamage() +
                "\n || Start armor: " + HeroesArray.get(position).getStartArmor() +
                "\n\n || Strengt: " + HeroesArray.get(position).getStartStr() + " + " + HeroesArray.get(position).getStrPerLvl() + "/level" +
                "\n || Agility: " + HeroesArray.get(position).getStartAgi() + " + " + HeroesArray.get(position).getAgiPerLvl() + "/level" +
                "\n || Intelligence: " + HeroesArray.get(position).getStartInt() + " + " + HeroesArray.get(position).getIntPerLvl() + "/level";
        ArrayHeroInfo.add(temp);

        adapterHeroInfo = new ArrayAdapter<String>(this, R.layout.custom_textview, ArrayHeroInfo);

        listViewHeroInfo.setAdapter(adapterHeroInfo);
    }

    public ArrayList<Hero> loadHeroes() throws IOException
    {
        ArrayList<Hero> HeroesArray = new ArrayList<Hero>();

        HeroDB.getInstance().open(true, this);
        HeroesArray = HeroDB.getInstance().getAllHeroes();

        return HeroesArray;
    }

    @SuppressLint("NewApi")
    public void getMatches(View view)
    {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 5000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        EditText UserID =  (EditText) findViewById(R.id.editTextuserID);
        String userID = UserID.getText().toString();
        ArrayMatches = new ArrayList<Match>();

        if(userID != null && !userID.isEmpty())
        {
            String urlStringMatchID = apiURLMatchHistory + APIuserID + userID + "&" + APIheroID + heroID + "&" + APIkey + "&format=XML&matches_requested=10";

            new CallAPIAllMatches().execute(urlStringMatchID);

        }
    }

    private class CallAPISpecificMatches extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params)
        {
            String urlString = params[0]; // URL to call

            InputStream in = null;

            // HTTP Get
            try
            {
                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                in = new BufferedInputStream(urlConnection.getInputStream());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                Log.v(myLogTag, "Error: " + e.getMessage());
                return e.getMessage();
            }

            // Parse XML
            XmlPullParserFactory pullParserFactory;

            try
            {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();

                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                parseXMLSpecificMatch(parser);
            }
            catch(XmlPullParserException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            ArrayCount++;
            textViewLoad.setText("" + ArrayCount + "/" + ArrayMatches.size() + " matches loaded");

            if(ArrayCount == ArrayMatches.size())
            {
                Intent intent = new Intent(getApplicationContext(), LatestHeroMatchesActivity.class);
                intent.putParcelableArrayListExtra("ArrayMatches", ArrayMatches);
                intent.putParcelableArrayListExtra("HeroesArray", HeroesArray);
                textViewLoad.setText("");
                startActivity(intent);
            }
        }
    }

    private class CallAPIAllMatches extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params)
        {
            String urlString = params[0]; // URL to call

            InputStream in = null;

            // HTTP Get
            try
            {
                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                in = new BufferedInputStream(urlConnection.getInputStream());
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                Log.v(myLogTag, "Error: " + e.getMessage());
                return e.getMessage();
            }

            // Parse XML
            XmlPullParserFactory pullParserFactory;

            try
            {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();

                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                parseXMLmatchID(parser);
            }
            catch(XmlPullParserException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            Log.v(myLogTag, "ArrayMatches.size: " + ArrayMatches.size());
            for(int i = 0; i < ArrayMatches.size(); i++)
            {
                Log.v(myLogTag, "MatchID: " + ArrayMatches.get(i).getMatchID().toString());

                ArrayCount = 0;
                String urlStringMatchID = apiURLMatchDetails + "match_id=" + ArrayMatches.get(i).getMatchID().toString() + "&" + APIkey + "&format=XML";
                new CallAPISpecificMatches().execute(urlStringMatchID);
            }

        }
    }

    @SuppressLint("NewApi")
    private void parseXMLmatchID (XmlPullParser parser) throws XmlPullParserException, IOException
    {
        int eventType = parser.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String name = null;
            Match match = new Match();

            if(eventType == XmlPullParser.START_TAG)
            {
                name = parser.getName();
                if(name.equals("match_id"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        match.setMatchID(parser.getText());
                        ArrayMatches.add(match);
                    }
                }
            }
            eventType = parser.next();
        }
    }

    @SuppressLint("NewApi")
    private void parseXMLSpecificMatch (XmlPullParser parser) throws XmlPullParserException, IOException
    {
        int eventType = parser.getEventType();
        Player player = new Player();
        ArrayList<Player> playerArray = new ArrayList<Player>();
        String accountID;
        String playerSlot;
        String hero;
        String item0;
        String item1;
        String item2;
        String item3;
        String item4;
        String item5;
        int kills;
        int deaths;
        int assists;
        int gold;
        int lasthits;
        int denies;
        int gpm;
        int xpm;
        int heroDMG;
        int towerDMG;
        int heroHealing;
        int level;
        String radiantWin = null;
        int duration = 0;
        String matchID = null;
        String temp;

        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String name = null;
            //Match match = new Match();


            if(eventType == XmlPullParser.START_TAG)
            {
                name = parser.getName();
                //Spelarinfo
                if(name.equals("account_id"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        accountID = parser.getText();
                        player.setAccountID(accountID);
                    }
                }
                else if(name.equals("player_slot"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        playerSlot = parser.getText();
                        player.setPlayerSlot(playerSlot);
                    }
                }
                else if(name.equals("hero_id"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        hero = parser.getText();
                        player.setHero(hero);
                    }
                }
                else if(name.equals("item_0"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {

                        item0 = parser.getText();
                        player.setItem0(item0);
                    }
                }
                else if(name.equals("item_1"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        item1 = parser.getText();
                        player.setItem1(item1);
                    }
                }
                else if(name.equals("item_2"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        item2 = parser.getText();
                        player.setItem2(item2);
                    }
                }
                else if(name.equals("item_3"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        item3 = parser.getText();
                        player.setItem3(item3);
                    }
                }
                else if(name.equals("item_4"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        item4 = parser.getText();
                        player.setItem4(item4);
                    }
                }
                else if(name.equals("item_5"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        item5 = parser.getText();
                        player.setItem5(item5);
                    }
                }
                else if(name.equals("kills"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        kills = Integer.parseInt(temp);
                        player.setKills(kills);
                    }
                }
                else if(name.equals("deaths"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        deaths = Integer.parseInt(temp);
                        player.setDeaths(deaths);
                    }
                }
                else if(name.equals("assists"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        assists = Integer.parseInt(temp);
                        player.setAssists(assists);
                    }
                }
                else if(name.equals("gold"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        gold = Integer.parseInt(temp);
                        player.setGold(gold);
                    }
                }
                else if(name.equals("last_hits"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        lasthits = Integer.parseInt(temp);
                        player.setLasthits(lasthits);
                    }
                }
                else if(name.equals("denies"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        denies = Integer.parseInt(temp);
                        player.setDenies(denies);
                    }
                }
                else if(name.equals("gold_per_min"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        gpm = Integer.parseInt(temp);
                        player.setGpm(gpm);
                    }
                }
                else if(name.equals("xp_per_min"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        xpm = Integer.parseInt(temp);
                        player.setXpm(xpm);
                    }
                }
                else if(name.equals("hero_damage"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        heroDMG = Integer.parseInt(temp);
                        player.setHeroDMG(heroDMG);
                    }
                }
                else if(name.equals("tower_damage"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        towerDMG = Integer.parseInt(temp);
                        player.setTowerDMG(towerDMG);
                    }
                }
                else if(name.equals("hero_healing"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        heroHealing = Integer.parseInt(temp);
                        player.setHeroHealing(heroHealing);
                    }
                }
                else if(name.equals("level"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        level = Integer.parseInt(temp);
                        player.setLevel(level);
                        if(player.getAccountID() != null)
                        {
                            playerArray.add(player);
                        }
                        player = new Player();
                    }
                }

                //matchinfo
                else if(name.equals("radiant_win"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        radiantWin = parser.getText();
                    }
                }
                else if(name.equals("duration"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        temp = parser.getText();
                        duration = Integer.parseInt(temp);
                    }
                }
                else if(name.equals("match_id"))
                {
                    if(parser.next() == XmlPullParser.TEXT)
                    {
                        matchID = parser.getText();
                    }
                }
            }
            eventType = parser.next();
        }

        for(int i = 0; i < ArrayMatches.size(); i++)
        {
            if(ArrayMatches.get(i).getMatchID().equals(matchID))
            {
                ArrayMatches.get(i).setDuration(duration);
                ArrayMatches.get(i).setPlayerArray(playerArray);
                ArrayMatches.get(i).setRadiantWin(radiantWin);
            }
        }
    }
}
