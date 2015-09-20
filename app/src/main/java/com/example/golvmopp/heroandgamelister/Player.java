package com.example.golvmopp.heroandgamelister;

/**
 * Created by Golvmopp on 2015-09-20.
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable
{
    private String accountID;
    private String playerSlot;
    private String hero;
    private String item0;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private int kills;
    private int deaths;
    private int assists;
    private int gold;
    private int lasthits;
    private int denies;
    private int gpm;
    private int xpm;
    private int heroDMG;
    private int towerDMG;
    private int heroHealing;
    private int level;

    public Player()
    {

    }

    public Player(String accountID, String playerSlot, String hero,
                  String item0, String item1, String item2, String item3,
                  String item4, String item5, int kills, int deaths, int assists,
                  int gold, int lasthits, int denies, int gpm, int xpm, int heroDMG,
                  int towerDMG, int heroHealing, int level) {
        super();
        this.accountID = accountID;
        this.playerSlot = playerSlot;
        this.hero = hero;
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.gold = gold;
        this.lasthits = lasthits;
        this.denies = denies;
        this.gpm = gpm;
        this.xpm = xpm;
        this.heroDMG = heroDMG;
        this.towerDMG = towerDMG;
        this.heroHealing = heroHealing;
        this.level = level;
    }



    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getPlayerSlot() {
        return playerSlot;
    }

    public void setPlayerSlot(String playerSlot) {
        this.playerSlot = playerSlot;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String getItem0() {
        return item0;
    }

    public void setItem0(String item0) {
        this.item0 = item0;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLasthits() {
        return lasthits;
    }

    public void setLasthits(int lasthits) {
        this.lasthits = lasthits;
    }

    public int getDenies() {
        return denies;
    }

    public void setDenies(int denies) {
        this.denies = denies;
    }

    public int getGpm() {
        return gpm;
    }

    public void setGpm(int gpm) {
        this.gpm = gpm;
    }

    public int getXpm() {
        return xpm;
    }

    public void setXpm(int xpm) {
        this.xpm = xpm;
    }

    public int getHeroDMG() {
        return heroDMG;
    }

    public void setHeroDMG(int heroDMG) {
        this.heroDMG = heroDMG;
    }

    public int getTowerDMG() {
        return towerDMG;
    }

    public void setTowerDMG(int towerDMG) {
        this.towerDMG = towerDMG;
    }

    public int getHeroHealing() {
        return heroHealing;
    }

    public void setHeroHealing(int heroHealing) {
        this.heroHealing = heroHealing;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    private Player(Parcel in)
    {
        accountID = in.readString();
        playerSlot = in.readString();
        hero = in.readString();
        item0 = in.readString();
        item1 = in.readString();
        item2 = in.readString();
        item3 = in.readString();
        item4 = in.readString();
        item5 = in.readString();
        kills = in.readInt();
        deaths = in.readInt();
        assists = in.readInt();
        gold = in.readInt();
        lasthits = in.readInt();
        denies = in.readInt();
        gpm = in.readInt();
        xpm = in.readInt();
        heroDMG = in.readInt();
        towerDMG = in.readInt();
        heroHealing = in.readInt();
        level = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(accountID);
        out.writeString(playerSlot);
        out.writeString(hero);
        out.writeString(item0);
        out.writeString(item1);
        out.writeString(item2);
        out.writeString(item3);
        out.writeString(item4);
        out.writeString(item5);
        out.writeInt(kills);
        out.writeInt(deaths);
        out.writeInt(assists);
        out.writeInt(gold);
        out.writeInt(lasthits);
        out.writeInt(denies);
        out.writeInt(gpm);
        out.writeInt(xpm);
        out.writeInt(heroDMG);
        out.writeInt(towerDMG);
        out.writeInt(heroHealing);
        out.writeInt(level);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}

