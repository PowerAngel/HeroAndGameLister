package com.example.golvmopp.heroandgamelister;

/**
 * Created by Golvmopp on 2015-09-20.
 */
import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable
{
    private String ID;
    private String Name;
    private String MainAttribute;
    private int Movementspeed;
    private int Range;

    private double StartInt;
    private double StartAgi;
    private double StartStr;
    private int StartDamage;
    private double StartArmor;
    private int StartHealth;
    private int StartMana;

    private double IntPerLvl;
    private double AgiPerLvl;
    private double StrPerLvl;


    public Hero()
    {

    }

    public Hero(String iD, String name, String mainAttribute,
                int movementspeed, int range, double startInt, double startAgi,
                double startStr, int startDamage, double startArmor,
                int startHealth, int startMana, double intPerLvl, double agiPerLvl,
                double strPerLvl) {
        super();
        ID = iD;
        Name = name;
        MainAttribute = mainAttribute;
        Movementspeed = movementspeed;
        Range = range;
        StartInt = startInt;
        StartAgi = startAgi;
        StartStr = startStr;
        StartDamage = startDamage;
        StartArmor = startArmor;
        StartHealth = startHealth;
        StartMana = startMana;
        IntPerLvl = intPerLvl;
        AgiPerLvl = agiPerLvl;
        StrPerLvl = strPerLvl;
    }



    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName()
    {
        return Name;
    }


    public void setName(String name)
    {
        Name = name;
    }


    public String getMainAttribute()
    {
        return MainAttribute;
    }


    public void setMainAttribute(String mainAttribute)
    {
        MainAttribute = mainAttribute;
    }


    public int getMovementspeed()
    {
        return Movementspeed;
    }


    public void setMovementspeed(int movementspeed)
    {
        Movementspeed = movementspeed;
    }


    public int getRange()
    {
        return Range;
    }


    public void setRange(int range)
    {
        Range = range;
    }


    public double getStartInt()
    {
        return StartInt;
    }


    public void setStartInt(double startInt)
    {
        StartInt = startInt;
    }


    public double getStartAgi()
    {
        return StartAgi;
    }


    public void setStartAgi(double startAgi)
    {
        StartAgi = startAgi;
    }


    public double getStartStr()
    {
        return StartStr;
    }


    public void setStartStr(double startStr)
    {
        StartStr = startStr;
    }


    public int getStartDamage()
    {
        return StartDamage;
    }


    public void setStartDamage(int startDamage)
    {
        StartDamage = startDamage;
    }


    public double getStartArmor()
    {
        return StartArmor;
    }


    public void setStartArmor(double startArmor)
    {
        StartArmor = startArmor;
    }


    public int getStartHealth()
    {
        return StartHealth;
    }


    public void setStartHealth(int startHealth)
    {
        StartHealth = startHealth;
    }


    public int getStartMana()
    {
        return StartMana;
    }


    public void setStartMana(int startMana)
    {
        StartMana = startMana;
    }


    public double getIntPerLvl()
    {
        return IntPerLvl;
    }


    public void setIntPerLvl(double intPerLvl)
    {
        IntPerLvl = intPerLvl;
    }


    public double getAgiPerLvl()
    {
        return AgiPerLvl;
    }


    public void setAgiPerLvl(double agiPerLvl)
    {
        AgiPerLvl = agiPerLvl;
    }


    public double getStrPerLvl()
    {
        return StrPerLvl;
    }


    public void setStrPerLvl(double strPerLvl)
    {
        StrPerLvl = strPerLvl;
    }

    private Hero(Parcel in)
    {
        ID = in.readString();
        Name = in.readString();
        MainAttribute = in.readString();
        Movementspeed = in.readInt();
        Range = in.readInt();
        StartInt = in.readDouble();
        StartAgi = in.readDouble();
        StartStr = in.readDouble();
        StartDamage = in.readInt();
        StartArmor = in.readDouble();
        StartHealth = in.readInt();
        StartMana = in.readInt();
        IntPerLvl = in.readDouble();
        AgiPerLvl = in.readDouble();
        StrPerLvl = in.readDouble();
    }

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(ID);
        out.writeString(Name);
        out.writeString(MainAttribute);
        out.writeInt(Movementspeed);
        out.writeInt(Range);
        out.writeDouble(StartInt);
        out.writeDouble(StartAgi);
        out.writeDouble(StartStr);
        out.writeInt(StartDamage);
        out.writeDouble(StartArmor);
        out.writeInt(StartHealth);
        out.writeInt(StartMana);
        out.writeDouble(IntPerLvl);
        out.writeDouble(AgiPerLvl);
        out.writeDouble(StrPerLvl);
    }

    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>()
    {
        @Override
        public Hero createFromParcel(Parcel in)
        {
            return new Hero(in);
        }
        @Override
        public Hero[] newArray(int size)
        {
            return new Hero[size];
        }
    };



}

