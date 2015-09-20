package com.example.golvmopp.heroandgamelister;

/**
 * Created by Golvmopp on 2015-09-20.
 */
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Match implements Parcelable
{
    private String matchID;
    private String radiantWin;
    private int duration;

    private ArrayList<Player> PlayerArray = new ArrayList<Player>();

    public Match()
    {

    }

    public Match(String matchID, String radiantWin, int duration, ArrayList<Player> playerArray)
    {
        super();
        this.matchID = matchID;
        this.radiantWin = radiantWin;
        this.duration = duration;
        PlayerArray = playerArray;
    }

    public String getMatchID()
    {
        return matchID;
    }

    public void setMatchID(String matchID)
    {
        this.matchID = matchID;
    }

    public String getRadiantWin() {
        return radiantWin;
    }

    public void setRadiantWin(String radiantWin) {
        this.radiantWin = radiantWin;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<Player> getPlayerArray() {
        return PlayerArray;
    }

    public void setPlayerArray(ArrayList<Player> playerArray) {
        PlayerArray = playerArray;
    }

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    private Match(Parcel in)
    {
        matchID = in.readString();
        radiantWin = in.readString();
        duration = in.readInt();
        in.readTypedList(PlayerArray, Player.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(matchID);
        out.writeString(radiantWin);
        out.writeInt(duration);
        out.writeTypedList(PlayerArray);
    }

    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };
}

