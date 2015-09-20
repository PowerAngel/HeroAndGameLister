package com.example.golvmopp.heroandgamelister;

/**
 * Created by Golvmopp on 2015-09-20.
 */
import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

public class HeroDB extends AbstractHeroDB
{
    private static final HeroDB theOnlyDB = new HeroDB();

    private HeroDB()
    {

    }

    public static HeroDB getInstance()
    {
        return theOnlyDB;
    }

    public Hero insertHero(String ID, String Name, String MainAttribute, int Movementspeed, int Range, double StartInt, double StartAgi, double StartStr, int StartDamage, double StartArmor,
                           int StartHealth, int StartMana, double IntPerLvl, double AgiPerLvl, double StrPerLvl)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_HEROID, ID);
        values.put(KEY_NAME, Name);
        values.put(KEY_MAINATTRIBUTE, MainAttribute);
        values.put(KEY_MOVEMENTSPEED, Movementspeed);
        values.put(KEY_RANGE, Range);
        values.put(KEY_STARTINT, StartInt);
        values.put(KEY_STARTAGI, StartAgi);
        values.put(KEY_STARTSTR, StartStr);
        values.put(KEY_STARTDAMAGE, StartDamage);
        values.put(KEY_STARTARMOR, StartArmor);
        values.put(KEY_STARTHEALTH, StartHealth);
        values.put(KEY_STARTMANA, StartMana);
        values.put(KEY_INTPERLVL, IntPerLvl);
        values.put(KEY_AGIPERLVL, AgiPerLvl);
        values.put(KEY_STRPERLVL, StrPerLvl);

        database.insert(DB_TABLE_HERO, null, values);
        return new Hero(ID, Name, MainAttribute, Movementspeed, Range, StartInt, StartAgi, StartStr, StartDamage, StartArmor, StartHealth, StartMana, IntPerLvl, AgiPerLvl, StrPerLvl);
    }

    public ArrayList<Hero> getAllHeroes()
    {
        Cursor cr = getAllHeroesCursor();
        return makeHeroListFromCursor(cr);
    }

    private ArrayList<Hero> makeHeroListFromCursor(Cursor cr)
    {
        ArrayList<Hero> heroes = new ArrayList<Hero>();

        if(cr != null && cr.moveToFirst())
        {
            do
            {
                heroes.add((new Hero(cr.getString(0), cr.getString(1), cr.getString(2), cr.getInt(3), cr.getInt(4), cr.getDouble(5), cr.getDouble(6), cr.getDouble(7),
                        cr.getInt(8), cr.getDouble(9), cr.getInt(10), cr.getInt(11), cr.getDouble(12), cr.getDouble(13), cr.getDouble(14))));
            }while(cr.moveToNext());
        }

        return heroes;
    }

    @Override
    protected void createTestData()
    {
        HeroDB.getInstance().insertHero("102", "Abaddon", "Strength", 310, 128, 21.0, 17.0, 23, 60, 1.38, 587, 273, 2.0, 1.5, 2.7);
        HeroDB.getInstance().insertHero("73", "Alchemist", "Strength", 295, 128, 25.0, 11.0, 25.0, 54, 1.54, 625, 325, 1.8, 1.2, 1.8);
        HeroDB.getInstance().insertHero("68", "Ancient Apparition", "Intelligence", 295, 600, 25.0, 20.0, 18.0, 49, 1.8, 492, 325, 2.6, 2.2, 1.4);
        HeroDB.getInstance().insertHero("1", "Anti-Mage", "Agility", 315, 128, 15.0, 22.0, 22.0, 51, 2.08, 568, 195, 1.8, 2.8, 1.2);
        HeroDB.getInstance().insertHero("2", "Axe", "Strength", 290, 128, 18.0, 20.0, 25.0, 51, 1.8, 625, 234, 1.6, 2.2, 2.5);
        HeroDB.getInstance().insertHero("3", "Bane", "Intelligence", 315, 400, 22.0, 22.0, 22.0, 55, 3.52, 568, 286, 2.1, 2.1, 2.1);
        HeroDB.getInstance().insertHero("65", "Batrider", "Intelligence", 290, 375, 24.0, 15.0, 23.0, 40, 2.1, 587, 312, 2.5, 1.5, 2.4);
        HeroDB.getInstance().insertHero("38", "Beastmaster", "Strength", 310, 128, 16.0, 18.0, 23.0, 62, 4.52, 587, 208, 1.9, 1.6, 2.2);
        HeroDB.getInstance().insertHero("4", "Bloodseeker", "Agility", 290, 128, 18.0, 24.0, 23.0, 56, 3.36, 587, 234, 1.7, 3.0, 2.0);
        HeroDB.getInstance().insertHero("62", "Bounty Hunter", "Agility", 315, 128, 19.0, 21.0, 17.0, 52, 5.94, 473, 247, 1.4, 3.0, 1.8);

        HeroDB.getInstance().insertHero("78", "Brewmaster", "Strength", 300, 128, 14.0, 22.0, 23.0, 55, 2.52, 587, 182, 1.25, 1.95, 2.9);
        HeroDB.getInstance().insertHero("99", "Bristleback", "Strength", 295, 128, 14.0, 17.0, 22.0, 53, 3.38, 568, 182, 2.8, 1.8, 2.2);
        HeroDB.getInstance().insertHero("61", "Broodmother", "Agility", 295, 128, 18.0, 18.0, 17.0, 47, 2.52, 473, 234, 2.0, 2.2, 2.5);
        HeroDB.getInstance().insertHero("96", "Centaur", "Strength", 300, 128, 15.0, 15.0, 23.0, 56, 3.1, 587, 195, 1.6, 2.0, 3.8);
        HeroDB.getInstance().insertHero("81", "Chaos Knight", "Strength", 325, 128, 16.0, 14.0, 20.0, 64, 3.96, 530, 208, 1.2, 2.1, 2.9);
        HeroDB.getInstance().insertHero("66", "Chen", "Intelligence", 300, 600, 21.0, 15.0, 20.0, 50, 1.1, 530, 273, 2.8, 2.1, 1.5);
        HeroDB.getInstance().insertHero("56", "Clinkz", "Agility", 300, 630, 16.0, 22.0, 15.0, 40, 2.08, 435, 208, 1.55, 3.0, 1.6);
        HeroDB.getInstance().insertHero("51", "Clockwerk", "Strength", 315, 128, 17.0, 13.0, 24.0, 56, 1.82, 606, 221, 1.3, 2.3, 2.7);
        HeroDB.getInstance().insertHero("5", "Crystal Maiden", "Intelligence", 280, 600, 16.0, 16.0, 16.0, 41, 1.24, 454, 208, 2.9, 1.6, 1.7);
        HeroDB.getInstance().insertHero("55", "Dark Seer", "Intelligence", 300, 128, 29.0, 12.0, 22.0, 63, 6.68, 568, 377, 2.7, 1.2, 2.3);

        HeroDB.getInstance().insertHero("50", "Dazzle", "Intelligence", 305, 550, 27.0, 21.0, 16.0, 50, 1.94, 454, 351, 3.4, 1.7, 1.85);
        HeroDB.getInstance().insertHero("43", "Death Prophet", "Intelligence", 280, 600, 20.0, 14.0, 19.0, 50, 2.96, 511, 260, 3.0, 1.4, 1.9);
        HeroDB.getInstance().insertHero("87", "Disruptor", "Intelligence", 300, 600, 22.0, 15.0, 1.9, 51, 1.1, 511, 286, 2.5, 1.4, 1.9);
        HeroDB.getInstance().insertHero("69", "Doom", "Strength", 290, 150, 13.0, 11.0, 26.0, 61, 0.54, 644, 169, 2.1, 0.9, 3.2);
        HeroDB.getInstance().insertHero("49", "Dragon Knight", "Strength", 290, 128, 15.0, 19.0, 19.0, 49, 3.66, 511, 195, 1.7, 2.2, 2.8);
        HeroDB.getInstance().insertHero("6", "Drow Ranger", "Agility", 300, 625, 15.0, 22.0, 17.0, 49, 0.64, 473, 195, 1.4, 1.9, 1.9);
        HeroDB.getInstance().insertHero("107", "Earth Spirit", "Strength", 305, 128, 18.0, 17.0, 21.0, 51, 3.38, 549, 234, 2.4, 1.5, 2.9);
        HeroDB.getInstance().insertHero("7", "Earthshaker", "Strength", 310, 128, 16.0, 12.0, 22.0, 51, 2.68, 568, 208, 1.8, 1.4, 2.9);
        HeroDB.getInstance().insertHero("103", "Elder Titan", "Strength", 315, 128, 23.0, 14.0, 24.0, 52, 2.96, 606, 299, 1.6, 1.5, 2.3);
        HeroDB.getInstance().insertHero("106", "Ember Spirit", "Agility", 310, 128, 20.0, 22.0, 19.0, 54, 1.08, 511, 260, 1.8, 1.8, 2.0);

        HeroDB.getInstance().insertHero("58", "Enchantress", "Intelligence", 335, 550, 16.0, 19.0, 16.0, 52, 0.66, 454, 208, 2.8, 1.8, 1.0);
        HeroDB.getInstance().insertHero("33", "Enigma", "Intelligence", 300, 500, 20.0, 14.0, 17.0, 45, 3.96, 473, 260, 3.4, 1.0, 2.1);
        HeroDB.getInstance().insertHero("41", "Faceless Void", "Agility", 300, 128, 15.0, 23.0, 23.0, 61, 3.94, 587, 195, 1.5, 2.65, 1.6);
        HeroDB.getInstance().insertHero("72", "Gyrocopter", "Agility", 315, 375, 23.0, 24.0, 18.0, 46, 4.36, 492, 299, 2.1, 2.8, 1.8);
        HeroDB.getInstance().insertHero("59", "Huskar", "Strength", 300, 400, 18.0, 15.0, 21.0, 44, 1.8, 549, 234, 1.5, 1.4, 2.4);
        HeroDB.getInstance().insertHero("74", "Invoker", "Intelligence", 280, 600, 22.0, 20.0, 19.0, 38, 1.8, 511, 286, 2.5, 1.9, 1.7);
        HeroDB.getInstance().insertHero("91", "IO", "Strength", 295, 575, 23.0, 14.0, 17.0, 47, -0.4, 473, 299, 1.7, 1.6, 1.9);
        HeroDB.getInstance().insertHero("64", "Jakiro", "Intelligence", 290, 400, 28.0, 10.0, 14.0, 50, 2.4, 606, 364, 2.8, 1.2, 2.3);
        HeroDB.getInstance().insertHero("8", "Juggernaut", "Agility", 305, 128, 14.0, 26.0, 20.0, 46, 3.8, 530, 182, 1.4, 2.85, 1.9);
        HeroDB.getInstance().insertHero("90", "Kepper of the Light", "Intelligence", 315, 600, 22.0, 15.0, 14.0, 46, 1.1, 416, 286, 2.8, 1.6, 1.8);

        HeroDB.getInstance().insertHero("23", "Kunkka", "Strength", 300, 128, 18.0, 14.0, 24.0, 52, 1.96, 606, 234, 1.5, 1.3, 3.0);
        HeroDB.getInstance().insertHero("104", "Legion Commander", "Strength", 310, 128, 20.0, 18.0, 26.0, 59, 2.52, 644, 260, 2.2, 1.7, 2.6);
        HeroDB.getInstance().insertHero("52", "Leshrac", "Intelligence", 315, 600, 26.0, 23.0, 16.0, 47, 3.22, 454, 338, 3.0, 1.7, 1.5);
        HeroDB.getInstance().insertHero("31", "Lich", "Intelligence", 315, 550, 18.0, 15.0, 18.0, 46, 1.1, 492, 234, 3.25, 2.0, 1.55);
        HeroDB.getInstance().insertHero("54", "Lifestealer", "Strength", 315, 128, 15.0, 18.0, 25.0, 62, 1.52, 625, 195, 1.75, 1.9, 2.4);
        HeroDB.getInstance().insertHero("25", "Lina", "Intelligence", 295, 670, 24.0, 16.0, 18.0, 46, 1.24, 492, 351, 3.2, 1.5, 1.5);
        HeroDB.getInstance().insertHero("26", "Lion", "Intelligence", 290, 600, 22.0, 15.0, 16.0, 53, 1.1, 454, 286, 3.0, 1.5, 1.7);
        HeroDB.getInstance().insertHero("80", "Lone Druid", "Agility", 325, 550, 13.0, 24.0, 17.0, 48, 3.36, 473, 169, 1.4, 2.7, 2.1);
        HeroDB.getInstance().insertHero("48", "Luna", "Agility", 330, 330, 16.0, 18.0, 15.0, 51, 3.08, 435, 208, 1.85, 2.8, 1.9);
        HeroDB.getInstance().insertHero("77", "Lycan", "Strength", 305, 128, 17.0, 16.0, 22.0, 60, 3.24, 568, 221, 1.55, 1.9, 2.75);

        HeroDB.getInstance().insertHero("97", "Magnus", "Strength", 315, 128, 19.0, 15.0, 21.0, 55, 4.1, 549, 247, 1.65, 2.5, 2.75);
        HeroDB.getInstance().insertHero("94", "Medusa", "Agility", 290, 600, 19.0, 20.0, 14.0, 47, 1.8, 416, 247, 1.85, 2.5, 1.65);
        HeroDB.getInstance().insertHero("82", "Meepo", "Agility", 315, 128, 20.0, 23.0, 23.0, 42, 4.22, 587, 260, 1.6, 1.9, 1.6);
        HeroDB.getInstance().insertHero("9", "Mirana", "Agility", 300, 600, 17.0, 20.0, 17.0, 43, 1.8, 473, 221, 1.65, 2.75, 1.85);
        HeroDB.getInstance().insertHero("10", "Morphling", "Agility", 285, 350, 17.0, 24.0, 19.0, 47, 0.66, 511, 221, 1.5, 3.0, 2.0);
        HeroDB.getInstance().insertHero("89", "Naga Siren", "Agility", 320, 128, 18.0, 21.0, 21.0, 57, 5.94, 549, 234, 1.95, 2.75, 2.3);
        HeroDB.getInstance().insertHero("53", "Nature's Prophet", "Intelligence", 295, 600, 21.0, 18.0, 19.0, 52, 3.52, 511, 273, 2.9, 1.9, 1.8);
        HeroDB.getInstance().insertHero("36", "Necrophos", "Intelligence", 290, 550, 22.0, 15.0, 16.0, 47, 4.22, 454, 286, 2.5, 1.7, 2.0);
        HeroDB.getInstance().insertHero("60", "Night Stalker", "Strength", 295, 128, 16.0, 18.0, 23.0, 63, 5.52, 587, 208, 1.6, 2.25, 2.8);
        HeroDB.getInstance().insertHero("88", "Nyx Assassin", "Agility", 300, 128, 18.0, 19.0, 18.0, 51, 3.66, 492, 234, 2.1, 2.2, 2.0);

        HeroDB.getInstance().insertHero("84", "Ogre Magi", "Intelligence", 295, 128, 17.0, 14.0, 23.0, 61, 6.96, 587, 221, 2.4, 1.55, 3.2);
        HeroDB.getInstance().insertHero("57", "Omniknight", "Strength", 305, 128, 17.0, 15.0, 20.0, 56, 4.1, 530, 221, 1.8, 1.75, 2.65);
        HeroDB.getInstance().insertHero("111", "Oracle", "Intelligence", 305, 620, 23.0, 15.0, 18.0, 48, 2.1, 492, 299, 2.9, 1.7, 1.9);
        HeroDB.getInstance().insertHero("76", "Outworld Devourer", "Intelligence", 315, 450, 26.0, 24.0, 19.0, 53, 5.36, 511, 338, 3.3, 2.0, 1.85);
        HeroDB.getInstance().insertHero("44", "Phantom Assassin", "Agility", 310, 128, 13.0, 23.0, 20.0, 47, 4.22, 530, 169, 1.0, 3.15, 1.85);
        HeroDB.getInstance().insertHero("12", "Phantom Lancer", "Agility", 290, 128, 21.0, 29.0, 21.0, 57, 3.22, 549, 273, 2.0, 3.0, 1.7);
        HeroDB.getInstance().insertHero("110", "Phoenix", "Strength", 290, 500, 18.0, 12.0, 19.0, 59, -0.32, 473, 234, 1.8, 1.3, 2.9);
        HeroDB.getInstance().insertHero("13", "Puck", "Agility", 295, 550, 24.0, 22.0, 15.0, 52, 2.08, 435, 325, 2.4, 1.7, 1.7);
        HeroDB.getInstance().insertHero("14", "Pudge", "Strength", 285, 128, 14.0, 14.0, 25.0, 55, 0.96, 625, 182, 1.5, 1.5, 3.2);
        HeroDB.getInstance().insertHero("45", "Pugna", "Intelligence", 315, 600, 26.00, 16.00, 17.00, 49, 1.24, 473, 338, 4, 1, 1.2);

        HeroDB.getInstance().insertHero("39", "Queen of Pain", "Intelligence", 300, 550, 24.00, 18.00, 16.00, 53, 1.52, 454, 312, 2.5, 2, 1.7);
        HeroDB.getInstance().insertHero("15", "Razor", "Agility", 295, 475, 19.00, 22.00, 21.00, 46, 2.08, 549, 247, 1.8, 2, 2.3);
        HeroDB.getInstance().insertHero("32", "Riki", "Agility", 300, 128, 14.00, 34.00, 17.00, 50, 5.76, 473, 182, 1.3, 2.9, 2);
        HeroDB.getInstance().insertHero("86", "Rubick", "Intelligence", 290, 600, 27.00, 14.00, 19.00, 49, 0.96, 511, 351, 2.4, 1.6, 1.5);
        HeroDB.getInstance().insertHero("16", "Sand King", "Strength", 300, 128, 16.00, 19.00, 18.00, 51, 2.66, 492, 208, 1.8, 2.1, 2.6);
        HeroDB.getInstance().insertHero("79", "Shadow Demon", "Intelligence", 295, 500, 23.00, 18.00, 17.00, 55, 2.52, 473, 299, 2.7, 2.2, 1.9);
        HeroDB.getInstance().insertHero("11", "Shadow Fiend", "Agility", 305, 500, 18.00, 20.00, 15.00, 38, 1.8, 435, 234, 2, 2.9, 2);
        HeroDB.getInstance().insertHero("27", "Shadow Shaman", "Intelligence", 285, 500, 21.00, 16.00, 19.00, 50, 1.24, 511, 273, 3.0, 1.6, 1.6);
        HeroDB.getInstance().insertHero("75", "Silencer", "Intelligence", 300, 600, 27.00, 16.00, 17.00, 50, 1.24, 473, 351, 2.5, 3, 2.2);
        HeroDB.getInstance().insertHero("101", "Skywrath Mage", "Intelligence", 325, 600, 27.00, 13.00, 19.00, 41, 0.52, 511, 351, 3.6, 0.8, 1.5);

        HeroDB.getInstance().insertHero("28", "Slardar", "Intelligence", 300, 128, 15.00, 17.00, 21.00, 55, 5.38, 495, 195, 1.5, 2.4, 2.8);
        HeroDB.getInstance().insertHero("93", "Slark", "Agility", 305, 128, 16.00, 26.00, 21.00, 52, 1.1, 549, 208, 1.9, 1.5, 1.8);
        HeroDB.getInstance().insertHero("35", "Sniper", "Agility", 290, 550, 15.00, 21.00, 16.00, 39, 1.94, 454, 195, 2.6, 2.9, 1.7);
        HeroDB.getInstance().insertHero("67", "Spectre", "Agility", 295, 129, 16.00, 23.00, 19.00, 48, 3.22, 511, 208, 1.9, 2.2, 2);
        HeroDB.getInstance().insertHero("71", "Spirit Breaker", "Strength", 290, 238, 14.00, 17.00, 23.00, 50, 5.38, 701, 182, 1.8, 1.7, 2.4);
        HeroDB.getInstance().insertHero("17", "Storm Spirit", "Intelligence", 290, 480, 23.00, 22.00, 19.00, 50, 5.08, 511, 299, 2.6, 1.8, 1.5);
        HeroDB.getInstance().insertHero("18", "Sven", "Strength", 295, 128, 14.00, 21.00, 23.00, 55, 4.94, 587, 182, 1.3, 2, 2.7);
        HeroDB.getInstance().insertHero("105", "Techies", "Intelligence", 270, 700, 22.00, 14.00, 17.00, 30, 6.96, 473, 286, 2.9, 1.3, 2);
        HeroDB.getInstance().insertHero("46", "Templar Assassin", "Agility", 305, 140, 20.00, 23.00, 18.00, 56, 4.22, 492, 260, 2, 2.7, 2.1);
        HeroDB.getInstance().insertHero("109", "Terrorblade", "Agility", 315, 128, 19.00, 22.00, 15.00, 51, 5.22, 435, 247, 1.75, 3.2, 1.4);

        HeroDB.getInstance().insertHero("29", "Tidehunter", "Strength", 310, 128, 16.00, 15.00, 22.00, 50, 3.1, 568, 208, 1.7, 1.5, 3);
        HeroDB.getInstance().insertHero("98", "Timbersaw", "Strength", 290, 128, 16.00, 21.00, 22.00, 50, 0.24, 568, 273, 2.4, 1.8, 2.1);
        HeroDB.getInstance().insertHero("34", "Tinker", "Intelligence", 305, 550, 27.00, 13.00, 17.00, 52, 3.82, 471, 351, 2.2, 1.2, 2);
        HeroDB.getInstance().insertHero("19", "Tiny", "Strength", 285, 128, 14.00, 9.00, 24.00, 64, 0.26, 606, 182, 1.6, 0.9, 3);
        HeroDB.getInstance().insertHero("83", "Treant Protector", "Strength", 300, 128, 17.00, 15.00, 25.00, 85, 1.1, 625, 221, 1.8, 2, 3.3);
        HeroDB.getInstance().insertHero("95", "Troll Warlord", "Agility", 300, 500, 13.00, 21.00, 17.00, 47, 1.94, 473, 169, 1, 2.75, 2.2);
        HeroDB.getInstance().insertHero("100", "Tusk", "Strength", 305, 128, 18.00, 23.00, 23.00, 52, 3.22, 587, 234, 1.7, 2.1, 2.3);
        HeroDB.getInstance().insertHero("85", "Undying", "Strength", 310, 128, 27.00, 10.00, 22.00, 61, 3.4, 568, 351, 2.5, 0.8, 2.1);
        HeroDB.getInstance().insertHero("70", "Ursa", "Agility", 310, 128, 16.00, 18.00, 23.00, 47, 5.52, 587, 208, 1.5, 2.1, 2.9);
        HeroDB.getInstance().insertHero("20", "Vengeful Spirit", "Agility", 295, 400, 15.00, 27.00, 18.00, 46, 3.78, 292, 195, 1.75, 2.8, 2.6);

        HeroDB.getInstance().insertHero("40", "Venomancer", "Agility", 285, 450, 18.00, 22.00, 18.00, 42, 3.08, 492, 195, 1.75, 2.6, 1.85);
        HeroDB.getInstance().insertHero("47", "Viper", "Agility", 285, 575, 15.00, 21.00, 20.00, 45, 1.94, 530, 195, 1.8, 2.5, 1.9);
        HeroDB.getInstance().insertHero("92", "Visage", "Intelligence", 290, 600, 21.00, 11.00, 22.00, 50, -0.46, 568, 312, 2.5, 1.3, 2.4);
        HeroDB.getInstance().insertHero("37", "Warlock", "Intelligence", 295, 600, 24.00, 10.00, 18.00, 51, 2.4, 492, 312, 2.7, 1, 2.5);
        HeroDB.getInstance().insertHero("63", "Weaver", "Agility", 290, 425, 15.00, 14.00, 15.00, 55, 0.96, 435, 195, 1.8, 2.5, 1.5);
        HeroDB.getInstance().insertHero("21", "Windranger", "Intelligence", 295, 600, 22.00, 17.00, 15.00, 50, 1.38, 435, 286, 2.6, 1.4, 2.5);
        HeroDB.getInstance().insertHero("30", "Witch Doctor", "Intelligence", 305, 600, 24.00, 13.00, 16.00, 56, 0.82, 454, 312, 2.9, 1.4, 1.8);
        HeroDB.getInstance().insertHero("42", "Wraith King", "Strength", 300, 128, 18.00, 18.00, 22.00, 55, 2.52, 568, 234, 1.6, 1.7, 2.9);
        HeroDB.getInstance().insertHero("22", "Zeus", "Intelligence", 295, 350, 20.00, 11.00, 19.00, 45, 1.54, 511, 260, 2.7, 1.2, 2.3);
    }

}

