package com.example.newfans;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private static Context context;
    public final static String PREFS_NAME = "newfanapp";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public Preference(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREFS_NAME, 0);
        editor = prefs.edit();
    }

    public void setUnityBannerId(String value) {
        editor.putString("unityBannerId", value);
        editor.apply();
    }

    public void setUnityInterstitialId(String value) {
        editor.putString("unityInterstitialId", value);
        editor.apply();
    }


    public String getUnityBannerId() {
        return prefs.getString("unityBannerId", "BANNER_ID");
    }

    public String getUnityInterstitialId() {
        return prefs.getString("unityInterstitialId", "INTERSTITIAL_AD");
    }

    public void setAdmobAppId(String value) {
        editor.putString("appId", value);
        editor.apply();
    }

    public String getAdmobAppId() {
        return prefs.getString("appId", "ca-app-pub-3940256099942544~3347511713");
    }

    public void setAdmobBanner(String value) {
        editor.putString("admobBanner", value);
        editor.apply();
    }

    public String getAdmobBanner() {
        return prefs.getString("admobBanner", "ca-app-pub-3940256099942544/6300978111");
    }

    public void setAdmobInterstitial(String value) {
        editor.putString("admobInterstitial", value);
        editor.apply();
    }

    public String getAdmobInterstitial() {
        return prefs.getString("admobInterstitial", "ca-app-pub-3940256099942544/1033173712");
    }

    public String getTestAdmobInterstitial() {
        return "ca-app-pub-3940256099942544/1033173712";
    }

    public void setAdmobNative(String value) {
        editor.putString("admobNative", value);
        editor.apply();
    }

    public String getAdmobNative() {
        return prefs.getString("admobNative", "ca-app-pub-3940256099942544/2247696110");
    }

    public void setFbBanner(String value) {
        editor.putString("FbBanner", value);
        editor.apply();
    }

    public String getFbBanner() {
        return prefs.getString("FbBanner", "");
    }

    public void setFbInterstitial(String value) {
        editor.putString("FbInterstitial", value);
        editor.apply();
    }

    public String getFbInterstitial() {

        return prefs.getString("FbInterstitial", "");
    }

    public void setFbNative(String value) {
        editor.putString("FbNative", value);
        editor.apply();
    }

    public String getFbNative() {
        return prefs.getString("FbNative", "");
    }


    public void setGuideContent(String value) {
        editor.putString("guidContent", value);
        editor.apply();
    }

    public String getGuideContent() {
        return prefs.getString("guidContent", "YOUR_PLACEMENT_ID");
    }

    public void setCreateAccountContent(String value) {
        editor.putString("createAccountContent", value);
        editor.apply();
    }

    public String getCreateAccountContent() {
        return prefs.getString("createAccountContent", "YOUR_PLACEMENT_ID");
    }

    public void setSubScriptionContent(String value) {
        editor.putString("subscriptionContent", value);
        editor.apply();
    }

    public String getSubScriptionContent() {
        return prefs.getString("subscriptionContent", "YOUR_PLACEMENT_ID");
    }

    public void setProfileContent(String value) {
        editor.putString("profileContent", value);
        editor.apply();
    }

    public String getProfileContent() {
        return prefs.getString("profileContent", "YOUR_PLACEMENT_ID");
    }

    public void setPlanContentContent(String value) {
        editor.putString("planContent", value);
        editor.apply();
    }

    public String getPlanContentContent() {
        return prefs.getString("planContent", "YOUR_PLACEMENT_ID");
    }

    public void setKingContentContent(String value) {
        editor.putString("kingContent", value);
        editor.apply();
    }

    public String getKingContentContent() {
        return prefs.getString("kingContent", "YOUR_PLACEMENT_ID");
    }
}