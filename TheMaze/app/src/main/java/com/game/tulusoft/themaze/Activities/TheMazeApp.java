package com.game.tulusoft.themaze.Activities;

import android.app.Application;

import com.facebook.FacebookSdk;


/**
 * Created by Shazam on 5/8/2016.
 */
public class TheMazeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
//        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.game.tulusoft.themaze",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.d("EX 1:", e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            Log.d("EX 2:", e.getMessage());
//        }
    }

}
