package com.game.tulusoft.themaze.Utilities;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shazam on 6/13/2016.
 */
public class PlayerInfo {
    public String getFacebook_account() {
        return facebook_account;
    }

    public void setFacebook_account(String facebook_account) {
        this.facebook_account = facebook_account;
    }

    public int getMax_n() {
        return max_n;
    }

    public void setMax_n(int max_n) {
        this.max_n = max_n;
    }

    public int getMax_m() {
        return max_m;
    }

    public void setMax_m(int max_m) {
        this.max_m = max_m;
    }

    public int getMax_h() {
        return max_h;
    }

    public void setMax_h(int max_h) {
        this.max_h = max_h;
    }

    public int getMul_n() {
        return mul_n;
    }

    public void setMul_n(int mul_n) {
        this.mul_n = mul_n;
    }

    public int getMul_m() {
        return mul_m;
    }

    public void setMul_m(int mul_m) {
        this.mul_m = mul_m;
    }

    public int getMul_h() {
        return mul_h;
    }

    public void setMul_h(int mul_h) {
        this.mul_h = mul_h;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getBugs() {
        return bugs;
    }

    public void setBugs(int bugs) {
        this.bugs = bugs;
    }

    public int getProhibits() {
        return prohibits;
    }

    public void setProhibits(int prohibits) {
        this.prohibits = prohibits;
    }

    public String getMap_trial() {
        return map_trial;
    }

    public void setMap_trial(String map_trial) {
        this.map_trial = map_trial;
    }

    public int getGame_speed() {
        return game_speed;
    }

    public void setGame_speed(int game_speed) {
        this.game_speed = game_speed;
    }

    private String facebook_account;
    private int max_n;
    private int max_m;
    private int max_h;
    private int mul_n;
    private int mul_m;
    private int mul_h;
    private int coin;
    private int bugs;
    private int prohibits;
    private String map_trial;
    private int game_speed;

    public PlayerInfo(){
        facebook_account = "";
        max_n = 1;
        max_m = 1;
        max_h = 1;
        mul_n = 0;
        mul_m = 0;
        mul_h = 0;
        coin = 0;
        bugs = 0;
        prohibits = 0;
        map_trial = "0,";
        game_speed = 1;
    }

    public PlayerInfo(String facebook_account, int max_n, int max_m, int max_h, int mul_n, int mul_m, int mul_h, int coin, int bugs, int prohibits, String map_trial, int game_speed) {
        this.facebook_account = facebook_account;
        this.max_n = max_n;
        this.max_m = max_m;
        this.max_h = max_h;
        this.mul_n = mul_n;
        this.mul_m = mul_m;
        this.mul_h = mul_h;
        this.coin = coin;
        this.bugs = bugs;
        this.prohibits = prohibits;
        this.map_trial = map_trial;
        this.game_speed = game_speed;
    }

    public void parseJson(String _json){
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(_json);
            this.facebook_account = jObj.getString(Common.API_Login_1);
            this.max_n = Integer.valueOf(jObj.getString(Common.API_Login_2));
            this.max_m = Integer.valueOf(jObj.getString(Common.API_Login_3));
            this.max_h = Integer.valueOf(jObj.getString(Common.API_Login_4));
            this.mul_n = Integer.valueOf(jObj.getString(Common.API_Login_5));
            this.mul_m = Integer.valueOf(jObj.getString(Common.API_Login_6));
            this.mul_h = Integer.valueOf(jObj.getString(Common.API_Login_7));
            this.coin = Integer.valueOf(jObj.getString(Common.API_Login_8));
            this.bugs = Integer.valueOf(jObj.getString(Common.API_Login_9));
            this.prohibits = Integer.valueOf(jObj.getString(Common.API_Login_10));
            this.map_trial = jObj.getString(Common.API_Login_11);
            this.game_speed = Integer.valueOf(jObj.getString(Common.API_Login_12));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
