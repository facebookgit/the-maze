package com.game.tulusoft.themaze.Utilities;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.game.tulusoft.themaze.Activities.TheMazeApp;
import com.game.tulusoft.themaze.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shazam on 6/13/2016.
 */
public class SendAPI implements  Response.Listener<String>,Response.ErrorListener{

    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    Context mContext;

    public SendAPI(Context _baseContext){
        this.mContext = _baseContext;
        cd = new ConnectionDetector(this.mContext);
        // get Internet status
        isInternetPresent = cd.isConnectingToInternet();
    }

    public void HTTPConnectServer(   final params_Http[] _params,
                                        final  boolean _isShowDialog,
                                        String messageDialog,
                                        String tag_string_req,
                                        int request_Method,
                                        String _URL) {
        if(!isInternetPresent){
            Toast.makeText(this.mContext,
                    R.string.error_str_not_internet, Toast.LENGTH_LONG).show();
        }
        else {
            // Tag used to cancel the request
            StringRequest strReq = new StringRequest(request_Method,
                    _URL, this, this) {


                @Override
                protected Map<String, String> getParams() {
                    // Posting params to register url
                    Map<String, String> params = new HashMap<String, String>();
                    for (params_Http curPair :_params ) {
                        params.put(curPair.getmKey(), curPair.getmValue());
                    }
                    return params;
                }

            };

            // Adding request to request queue
            TheMazeApp.getInstance().addToRequestQueue(strReq, tag_string_req);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(String response) {
        JSONObject jObj;
        String playerinfor ="";
        try {
            jObj = new JSONObject(response);
            playerinfor = jObj.getString(Common.PLAYER_API);
            Common.getPlayerInfo().parseJson(playerinfor);
            System.out.println("Complete parser");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        if(playerinfor.equals(Common.REGISTER_TAG)) {
//            if (!Common.UserLogin.ParseJson(response)) {
//                Toast.makeText(getApplicationContext(),
//                        Common.UserLogin.getmErrorMessageParseJson(), Toast.LENGTH_LONG).show();
//                Intent i = new Intent(HomeActivity.this, LoginScreen.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
//                finish();
//            }

//        }
    }
}
