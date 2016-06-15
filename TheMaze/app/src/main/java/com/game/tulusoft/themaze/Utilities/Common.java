package com.game.tulusoft.themaze.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.game.tulusoft.themaze.Objective.ButtonSprite;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.util.Debug;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Shazam on 3/1/2016.
 */
public class Common {

    // key get config
    public static String Key_Config_Volume_Sound = "Key_Config_Volume_Sound";
    public static String Key_Config_Volume_Music = "Key_Config_Volume_Music";
    public static String Key_Config_Sound = "Key_Config_Sound";
    public static String Key_Config_Name = "Key_Config_Name";

    public static String Key_Config_Multi_N = "Key_Config_Multi_N";
    public static String Key_Config_Max_N = "Key_Config_Max_N";

    public static String Key_Config_Multi_M = "Key_Config_Multi_M";
    public static String Key_Config_Max_M = "Key_Config_Max_M";

    public static String Key_Config_Multi_H = "Key_Config_Multi_H";
    public static String Key_Config_Max_H = "Key_Config_Max_H";

    public static String Key_Config_Level = "Key_Config_Level";

    public static String Key_Config_Coin_Local = "Key_Config_Coin_Local";
    public static String Key_Config_Count_Bugs = "Key_Config_Count_Bugs";
    public static String Key_Config_Count_Prohibit = "Key_Config_Count_Prohibit";

    public static String Key_Config_Map_Trial = "Key_Config_Map_Trial";

    //Key gamesetting
    public static String Key_Game_Speed = "Key_Game_Speed";
    public static String Key_Game_Select_Map_Trial = "Key_Game_Select_Map_Trial";
    //game setting

    // key Playerinfor
    public static String Key_PlayerInfor= "Key_PlayerInfor";

    //API sender
    public static String API_Login_1 = "facebook_account";
    public static String API_Login_2 = "max_n";
    public static String API_Login_3 = "max_m";
    public static String API_Login_4 = "max_h";
    public static String API_Login_5 = "mul_n";
    public static String API_Login_6 = "mul_m";
    public static String API_Login_7 = "mul_h";
    public static String API_Login_8 = "coin";
    public static String API_Login_9 = "bugs";
    public static String API_Login_10 = "prohibits";
    public static String API_Login_11 = "map_trial";
    public static String API_Login_12 = "game_speed";
    public static String API_TAG = "api_tag";
    public static String REGISTER_TAG = "register";
    public static String PLAYER_API = "PLAYER";

    // Server url
    public static String URL_Server = "http://ec2-54-169-114-177.ap-southeast-1.compute.amazonaws.com/themaze_api/home/";
    // Server Login url
    public static String URL_Login = URL_Server + "create";

    public static int baseSpeed = 100;

    public static float getSpeed_Location() {
        return Speed_Location[Common.getPlayerInfo().getGame_speed()];
    }

    public static float[] Speed_Location = new float[]{0,0.4F,0.35F,0.3F,0.25F,0.2F};

    public static int getSpeed_Animation_mummy() {
        return Speed_Animation_mummy[Common.getPlayerInfo().getGame_speed()];
    }

    public static int[] Speed_Animation_mummy = new int[]{0,100,87,75,62,50};

    public static int getObject_width() {
        return object_width;
    }

    private static int object_width = 52;

    public static boolean getIsSound() {
        return isSound;
    }

    public static void setIsSound(boolean isSound) {
        Common.isSound = isSound;

        if(theme_Music != null) {
            if (isSound) theme_Music.play();
            else theme_Music.pause();
        }
    }

    public static ButtonSprite getMbtnSound() {
        if(mbtnSound == null){
            mbtnSound = new ButtonSprite(10,0,"menu/", "music.png",128,64,0,0,2,1,"mbtnSound");
        }
        return mbtnSound;
    }

    public static void setMbtnSound(ButtonSprite mbtnSound) {
        Common.mbtnSound = mbtnSound;
    }


    public static int getmWIDTH() {
        return mWIDTH;
    }

    public static int getmHEIGHT() {
        return mHEIGHT;
    }
    private static ButtonSprite mbtnSound;
    private static boolean isSound = true;

    public static float getVolume_Sound() {
        return volume_Sound;
    }

    public static void setVolume_Sound(float volume_Sound) {
        Common.volume_Sound = volume_Sound;
    }

    private static float volume_Sound;

    public static float getVolume_Music() {
        return volume_Music;
    }

    public static void setVolume_Music(float volume_Music) {
        Common.volume_Music = volume_Music;
    }

    private static float volume_Music;

    public static int getiLevel() {
        return iLevel;
    }

    public static void setiLevel(int iLevel) {
        Common.iLevel = iLevel;
    }


    private static int mWIDTH = 565;
    private static int mHEIGHT = 848;

    private static int iLevel = 0;
    public static final int LevelHard = 2;
    public static final int LevelMedium = 1;
    public static final int LevelNormal = 0;
    public static final int ButtonLevelHard = 8;
    public static final int ButtonLevelMedium = 6;
    public static final int ButtonLevelNormal = 4;


    public static int getButtonLevel(){
        int iButtonLevel = 0;
        switch (getiLevel()){
            case LevelNormal:
                iButtonLevel = ButtonLevelNormal;
                break;
            case LevelMedium:
                iButtonLevel = ButtonLevelMedium;
                break;
            case LevelHard:
                iButtonLevel = ButtonLevelHard;
                break;
        }

        return iButtonLevel;
    }

    public static int getMaxGameStory(){
        switch (getiLevel()){
            case LevelNormal:
                return Common.getPlayerInfo().getMax_n();
            case LevelMedium:
                return Common.getPlayerInfo().getMax_m();
            case LevelHard:
                return Common.getPlayerInfo().getMax_h();
        }

        return 1;
    }

    public static void setMaxGameStory(int _iRoom){
        switch (getiLevel()){
            case LevelNormal:
                Common.getPlayerInfo().setMax_n(_iRoom);
                break;
            case LevelMedium:
                Common.getPlayerInfo().setMax_m(_iRoom);
                break;
            case LevelHard:
                Common.getPlayerInfo().setMax_h(_iRoom);
                break;
        }
    }

    public static int getMulGameStory(){
        switch (getiLevel()){
            case LevelNormal:
                return Common.getPlayerInfo().getMul_n();
            case LevelMedium:
                return Common.getPlayerInfo().getMul_m();
            case LevelHard:
                return Common.getPlayerInfo().getMul_h();
        }

        return 1;
    }

    public static void setMulGameStory(int _iRoom){
        switch (getiLevel()){
            case LevelNormal:
                Common.getPlayerInfo().setMul_n(_iRoom);
                break;
            case LevelMedium:
                Common.getPlayerInfo().setMul_m(_iRoom);
                break;
            case LevelHard:
                Common.getPlayerInfo().setMul_h(_iRoom);
                break;
        }
    }

    public static int randomNext(int ifrom, int ito){
        Random rnd = new Random();
        int iResults = ito == ifrom ? ifrom : rnd.nextInt(ito - ifrom) + ifrom;
        return iResults;
    }

    //sound
    public static Music theme_Music;

    public static Sound getRope_slide() {
        return rope_slide;
    }

    public static Sound rope_slide;

    public static Sound getCoin_drop() {
        return coin_drop;
    }

    public static Sound coin_drop;

    public static Sound getBug_hit2() {
        return bug_hit2;
    }

    public static Sound bug_hit2;

    public static Sound getBug_hit() {
        return bug_hit;
    }

    public static Sound bug_hit;

    public static Sound getPurchase_gold() {
        return purchase_gold;
    }

    public static Sound purchase_gold;

    public static Sound getCancel_click() {
        return cancel_click;
    }

    public static Sound cancel_click;

    public static Sound getOpen_door() {
        return open_door;
    }

    public static Sound open_door;

    public static Sound getClick_button() {
        return click_button[randomNext(0, 3)];
    }

    public static Sound[] click_button;


    public static Sound getCoin_bonus() {
        return coin_bonus;
    }

    public static void setCoin_bonus(Sound coin_bonus) {
        Common.coin_bonus = coin_bonus;
    }

    public static Sound coin_bonus;

    public static Sound getBonus_bugs_pro() {
        return bonus_bugs_pro;
    }

    public static void setBonus_bugs_pro(Sound bonus_bugs_pro) {
        Common.bonus_bugs_pro = bonus_bugs_pro;
    }

    public static Sound bonus_bugs_pro;

    public static Sound getClose_door() {
        return close_door;
    }

    public static Sound close_door;

    public static Sound getReturn_door() {
        return return_door;
    }

    public static Sound return_door;

    public static Sound getNext_door() {
        return next_door;
    }

    public static Sound next_door;

    public static Sound getMummy_hit() {
        return mummy_hit[randomNext(0,2)];
    }

    public static Sound[] mummy_hit;


    public static Sound getFoot_step() {
        return foot_step;
    }
    public static Sound foot_step;

    public static void setupSound(Engine _engine,Context _context){
        mummy_hit = new Sound[2];
        click_button = new Sound[3];
        MusicFactory.setAssetBasePath("sfx/");
        SoundFactory.setAssetBasePath("sfx/");
        try {
            theme_Music = MusicFactory.createMusicFromAsset(_engine.getMusicManager(), _context, "theme2.wav");
            rope_slide = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "rope_slide.wav");
            coin_drop = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "coin_drop.wav");
            bug_hit2 = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "bug_hit2.wav");
            bug_hit = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "bug_hit.wav");
            purchase_gold = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "purchase_gold.wav");
            cancel_click = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "cancel_click.wav");
            open_door = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "open_door.wav");
            close_door = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "door_hit2.wav");
            coin_bonus = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "coin_bonus.wav");
            bonus_bugs_pro = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "bonus_bugs_pro.wav");
            click_button[0] = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "click2.wav");
            click_button[1] = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "click3.wav");
            click_button[2] = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "click4.wav");
            foot_step = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "footsteps_run.wav");

            return_door = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "return_door.wav");
            next_door = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "nextdoor.wav");

            mummy_hit[0] = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "mummy_hit_1.wav");
            mummy_hit[1] = SoundFactory.createSoundFromAsset(_engine.getSoundManager(), _context, "mummy_hit_2.wav");

            foot_step.setLooping(true);
            theme_Music.setLooping(true);
            theme_Music.setVolume(Common.getVolume_Music());

            Common.setVolumeSound(Common.getVolume_Sound());
            if(isSound){
            theme_Music.play();}

        } catch (final IOException e) {
            Debug.e(e);
        }
    }

    public static void setVolumeSound(float _volume){
        if(Common.rope_slide != null){
            Common.rope_slide.setVolume(_volume);
        }
        if(Common.coin_drop != null){
            Common.coin_drop.setVolume(_volume);
        }
        if(Common.bug_hit2 != null){
            Common.bug_hit2.setVolume(_volume);
        }
        if(Common.bug_hit != null){
            Common.bug_hit.setVolume(_volume);
        }
        if(Common.purchase_gold != null){
            Common.purchase_gold.setVolume(_volume);
        }
        if(Common.cancel_click != null){
            Common.cancel_click.setVolume(_volume);
        }
        if(Common.open_door != null){
            Common.open_door.setVolume(_volume);
        }
        if(Common.close_door != null){
            Common.close_door.setVolume(_volume);
        }
        if(Common.coin_bonus != null){
            Common.coin_bonus.setVolume(_volume);
        }
        if(Common.bonus_bugs_pro != null){
            Common.bonus_bugs_pro.setVolume(_volume);
        }
        if(Common.click_button[0] != null){
            Common.click_button[0].setVolume(_volume);
        }
        if(Common.click_button[1] != null){
            Common.click_button[1].setVolume(_volume);
        }
        if(Common.click_button[2] != null){
            Common.click_button[2].setVolume(_volume);
        }
        if(Common.foot_step != null){
            Common.foot_step.setVolume(_volume);
        }
        if(Common.return_door != null){
            Common.return_door.setVolume(_volume);
        }
        if(Common.next_door != null){
            Common.next_door.setVolume(_volume);
        }
        if(Common.mummy_hit[0] != null){
            Common.mummy_hit[0].setVolume(_volume);
        }
        if(Common.mummy_hit[1] != null){
            Common.mummy_hit[1].setVolume(_volume);
        }
    }

    public static void releaseSound(){
        if(Common.theme_Music != null){
            Common.theme_Music.release();
        }
        if(Common.rope_slide != null){
            Common.rope_slide.release();
        }
        if(Common.coin_drop != null){
            Common.coin_drop.release();
        }
        if(Common.bug_hit2 != null){
            Common.bug_hit2.release();
        }
        if(Common.bug_hit != null){
            Common.bug_hit.release();
        }
        if(Common.purchase_gold != null){
            Common.purchase_gold.release();
        }
        if(Common.cancel_click != null){
            Common.cancel_click.release();
        }
        if(Common.open_door != null){
            Common.open_door.release();
        }
        if(Common.close_door != null){
            Common.close_door.release();
        }
        if(Common.coin_bonus != null){
            Common.coin_bonus.release();
        }
        if(Common.bonus_bugs_pro != null){
            Common.bonus_bugs_pro.release();
        }
        if(Common.click_button[0] != null){
            Common.click_button[0].release();
        }
        if(Common.click_button[1] != null){
            Common.click_button[1].release();
        }
        if(Common.click_button[2] != null){
            Common.click_button[2].release();
        }
        if(Common.foot_step != null){
            Common.foot_step.release();
        }
        if(Common.return_door != null){
            Common.return_door.release();
        }
        if(Common.next_door != null){
            Common.next_door.release();
        }
        if(Common.mummy_hit[0] != null){
            Common.mummy_hit[0].release();
        }
        if(Common.mummy_hit[1] != null){
            Common.mummy_hit[1].release();
        }
    }

    public static String getPlayer_name() {
        return player_name;
    }

    public static void setPlayer_name(String player_name) {
        Common.player_name = player_name;
    }

    private static String player_name;


    // SharedPreferences

    public static String mazeData = "mazeData";
    public static SharedPreferences pref_config = null;
    public static SharedPreferences.Editor edit = null;
    public static SharedPreferences createSharedPref(Context _context){
        return _context.getSharedPreferences(mazeData,Context.MODE_PRIVATE);
    }
    public static SharedPreferences.Editor getEdit(Context _context) {
        if(edit == null){
            edit = getPref_config(_context).edit();
        }
        return edit;
    }


    public static SharedPreferences getPref_config(Context _context) {
        if(pref_config == null){
            pref_config = createSharedPref(_context);
        }
        return pref_config;
    }

    // game story infor

    //region delete detail infor
//
//    public static int getMax_Room_N() {
//        return Max_Room_N;
//    }
//
//    public static void setMax_Room_N(int max_Room_N) {
//        Max_Room_N = max_Room_N;
//    }
//
//    private static int Max_Room_N;
//
//    public static int getMulti_Room_N() {
//        return Multi_Room_N;
//    }
//
//    public static void setMulti_Room_N(int multi_Room_N) {
//        Multi_Room_N = multi_Room_N;
//    }
//
//    private static int Multi_Room_N;
//
//    public static int getMax_Room_M() {
//        return Max_Room_M;
//    }
//
//    public static void setMax_Room_M(int max_Room_M) {
//        Max_Room_M = max_Room_M;
//    }
//
//    private static int Max_Room_M;
//
//    public static int getMulti_Room_M() {
//        return Multi_Room_M;
//    }
//
//    public static void setMulti_Room_M(int multi_Room_M) {
//        Multi_Room_M = multi_Room_M;
//    }
//
//    private static int Multi_Room_M;
//
//
//    public static int getMax_Room_H() {
//        return Max_Room_H;
//    }
//
//    public static void setMax_Room_H(int max_Room_H) {
//        Max_Room_H = max_Room_H;
//    }
//
//    private static int Max_Room_H;
//
//    public static int getMulti_Room_H() {
//        return Multi_Room_H;
//    }
//
//    public static void setMulti_Room_H(int multi_Room_H) {
//        Multi_Room_H = multi_Room_H;
//    }
//
//    private static int Multi_Room_H;
//
//    public static String getMap_Trial() {
//        return Map_Trial;
//    }
//
//    public static void setMap_Trial(String map_Trial) {
//        Map_Trial = map_Trial;
//    }
//
//    private static String Map_Trial;

    //region Coin
//    public static int Count_Prohibit;
//    public static int Count_Bugs;
//    public static int Coin_Local;
//
//    public static int getCount_Prohibit() {
//        return Count_Prohibit;
//    }
//
//    public static void setCount_Prohibit(int count_Prohibit) {
//        Count_Prohibit = count_Prohibit;
//    }
//
//    public static int getCount_Bugs() {
//        return Count_Bugs;
//    }
//
//    public static void setCount_Bugs(int count_Bugs) {
//        Count_Bugs = count_Bugs;
//    }
//
//    public static int getCoin_Local() {
//        return Coin_Local;
//    }
//
//    public static void setCoin_Local(int coin_Local) {
//        Coin_Local = coin_Local;
//    }


//    public static int getGame_Speed() {
//        return game_Speed;
//    }
//
//    public static void setGame_Speed(int game_Speed) {
//        Common.game_Speed = game_Speed;
//    }
//    private static int game_Speed = 1;
    //endregion

    //endregion

    // Player info localy

    public static PlayerInfo getPlayerInfo() {
        if(playerInfo == null) playerInfo = new PlayerInfo();
        return playerInfo;
    }

    public static void setPlayerInfo(String _json) {
        PlayerInfo pl = new PlayerInfo();
        pl.parseJson(_json);
        playerInfo = pl;
    }

    private static PlayerInfo playerInfo;

    public static String[] getArr_Map_Trial(){
        return Common.getPlayerInfo().getMap_trial().split(",");
    }



    public static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }


    public static void CommitSharedPreferent(Context _context){
        Common.getEdit(_context).putFloat(Common.Key_Config_Volume_Sound, Common.getVolume_Sound()).commit();
        Common.getEdit(_context).putFloat(Common.Key_Config_Volume_Music, Common.getVolume_Music()).commit();
        Common.getEdit(_context).putBoolean(Common.Key_Config_Sound, Common.getIsSound()).commit();
        Common.getEdit(_context).putInt(Common.Key_Config_Level, Common.getiLevel()).commit();

        //region delete detail infor
//        Common.getEdit(_context).putInt(Common.Key_Config_Max_N, Common.getMax_Room_N()).commit();
//        Common.getEdit(_context).putInt(Common.Key_Config_Multi_N, Common.getMulti_Room_N()).commit();
//
//        Common.getEdit(_context).putInt(Common.Key_Config_Max_M, Common.getMax_Room_M()).commit();
//        Common.getEdit(_context).putInt(Common.Key_Config_Multi_M, Common.getMulti_Room_M()).commit();
//
//        Common.getEdit(_context).putInt(Common.Key_Config_Max_H, Common.getMax_Room_H()).commit();
//        Common.getEdit(_context).putInt(Common.Key_Config_Multi_H, Common.getMulti_Room_H()).commit();
//        Common.getEdit(_context).putString(Common.Key_Config_Map_Trial, Common.getMap_Trial()).commit();

//        Common.getEdit(_context).putInt(Common.Key_Config_Coin_Local, Common.getCoin_Local()).commit();
//        Common.getEdit(_context).putInt(Common.Key_Config_Count_Bugs, Common.getCount_Bugs()).commit();
//        Common.getEdit(_context).putInt(Common.Key_Config_Count_Prohibit, Common.getCount_Prohibit()).commit();
//
//        Common.getEdit(_context).putInt(Common.Key_Game_Speed, Common.getGame_Speed()).commit();
        //endregion

        Common.getEdit(_context).putString(Common.Key_PlayerInfor, Common.getPlayerInfo().toJson()).commit();

        //push to server
        if(Common.isLoggedIn()){ // is loged in
            SendAPI sender_API = new SendAPI(_context);

            Profile profileSuccess = Profile.getCurrentProfile();
            if(profileSuccess !=null) {
                params_Http[] login_param = new params_Http[]{new params_Http(Common.API_Login_1, profileSuccess.getId()),
                        new params_Http(Common.API_Login_2, String.valueOf(Common.getPlayerInfo().getMax_n())),
                        new params_Http(Common.API_Login_3, String.valueOf(Common.getPlayerInfo().getMax_m())),
                        new params_Http(Common.API_Login_4, String.valueOf(Common.getPlayerInfo().getMax_h())),
                        new params_Http(Common.API_Login_5, String.valueOf(Common.getPlayerInfo().getMul_n())),
                        new params_Http(Common.API_Login_6, String.valueOf(Common.getPlayerInfo().getMul_m())),
                        new params_Http(Common.API_Login_7, String.valueOf(Common.getPlayerInfo().getMul_h())),
                        new params_Http(Common.API_Login_8, String.valueOf(Common.getPlayerInfo().getCoin())),
                        new params_Http(Common.API_Login_9, String.valueOf(Common.getPlayerInfo().getBugs())),
                        new params_Http(Common.API_Login_10, String.valueOf(Common.getPlayerInfo().getProhibits())),
                        new params_Http(Common.API_Login_11, Common.getPlayerInfo().getMap_trial()),
                        new params_Http(Common.API_Login_12, String.valueOf(Common.getPlayerInfo().getGame_speed())),
                };
                sender_API.HTTPConnectServer(login_param, false, "Login ...", "req_login", Request.Method.POST, Common.URL_Login);
            }
        }

    }

}
