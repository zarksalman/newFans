package com.example.newfans

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.facebook.ads.*
import com.facebook.ads.AdError
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.gms.ads.*
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.formats.NativeAdOptions
import java.util.*


/*
* Created by SalmanHameed on 12/15/2020.
*/

object AdsManager {

    private var fbInterstitialAd: com.facebook.ads.InterstitialAd? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mActivity: Activity? = null
    private var nativeAd: NativeAd? = null
    var preference: Preference? = null

    fun loadAds(activity: Activity?) {
        loadFacebookInterstitial(activity)
        loadInterstitial(activity)
    }

    fun showBanner(linearLayout: LinearLayout, activity: Activity?) {

        mActivity = activity
        preference = Preference(activity);

        if (BuildConfig.DEBUG) {
            RequestConfiguration.Builder().setTestDeviceIds(
                Arrays.asList("821932DD6AFBE2E8EB60FF092A8883A2")
            )
            AdSettings.addTestDevice("3ceb0ed2-4040-4827-a3ab-86372dc0b925")
            AdSettings.addTestDevice("1ea5a4cb-5766-421d-a3f2-b1fdffe39c80")
            AdSettings.turnOnSDKDebugger(activity)
            AdSettings.setTestMode(true)
        }

        if (linearLayout.childCount > 0) linearLayout.removeAllViews()

        val fbAdView = com.facebook.ads.AdView(
            activity,
            AppConstant.FB_BANNER_ID,
            com.facebook.ads.AdSize.BANNER_HEIGHT_50
        )
        linearLayout.addView(fbAdView);

        val fbAdListener = object : com.facebook.ads.AdListener {
            override fun onAdClicked(p0: Ad?) = Unit

            override fun onError(ad: Ad?, adError: AdError?) {
                Log.d("new_app_ads_error_fb", adError?.errorMessage)

                val googleAdView = AdView(activity)
                googleAdView.adSize = AdSize.BANNER
                googleAdView.adUnitId = AppConstant.BANNER_ID
                linearLayout.addView(googleAdView)
                val adRequest: AdRequest = AdRequest.Builder().build()
                googleAdView.loadAd(adRequest)
            }

            override fun onAdLoaded(p0: Ad?) = Unit

            override fun onLoggingImpression(p0: Ad?) = Unit
        }

        fbAdView.loadAd(fbAdView.buildLoadAdConfig().withAdListener(fbAdListener).build());
    }

    fun showFbInterstitial() {
        if (fbInterstitialAd?.isAdLoaded == true)
            fbInterstitialAd?.show()
        else
            showInterstitial()
    }

    private fun loadFacebookInterstitial(activity: Activity?) {

        mActivity = activity

        preference = Preference(activity);

        if (fbInterstitialAd == null || fbInterstitialAd?.isAdLoaded == false) {
            fbInterstitialAd = com.facebook.ads.InterstitialAd(
                activity,
                AppConstant.FB_INTERSTITIAL_ID
            )

            if (BuildConfig.DEBUG) {
                RequestConfiguration.Builder().setTestDeviceIds(
                    Arrays.asList("821932DD6AFBE2E8EB60FF092A8883A2")
                )
                AdSettings.addTestDevice("3ceb0ed2-4040-4827-a3ab-86372dc0b925")
                AdSettings.addTestDevice("1ea5a4cb-5766-421d-a3f2-b1fdffe39c80")
                AdSettings.turnOnSDKDebugger(activity)
                AdSettings.setTestMode(true)
            }

            val fbInterstitialAdListener: InterstitialAdListener = object :
                InterstitialAdListener {
                override fun onInterstitialDisplayed(ad: Ad) = Unit

                override fun onInterstitialDismissed(ad: Ad) {
                    // Interstitial dismissed callback
                    fbInterstitialAd?.loadAd()
                }

                override fun onError(ad: Ad, adError: AdError) {
                    // Ad error callback
                    Log.d("new_app_ads_error_fb", adError.errorMessage)
                }

                override fun onAdLoaded(ad: Ad) = Unit

                override fun onAdClicked(ad: Ad) = Unit

                override fun onLoggingImpression(ad: Ad) = Unit
            }

            fbInterstitialAd?.loadAd(
                fbInterstitialAd?.buildLoadAdConfig()
                    ?.withAdListener(fbInterstitialAdListener)
                    ?.build()
            )
        }
    }

    private fun loadInterstitial(activity: Activity?) {

        mActivity = activity

        preference = Preference(activity)

        if (mInterstitialAd == null || mInterstitialAd?.isLoaded == false) {

            mInterstitialAd = InterstitialAd(activity)
            mInterstitialAd?.adUnitId = AppConstant.INTERSTITIAL_ID
            mInterstitialAd?.loadAd(AdRequest.Builder().build())

            mInterstitialAd?.adListener = object : AdListener() {
                override fun onAdLoaded() {
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Code to be executed when an ad request fails.
                    Log.d("new_app_ads_error", adError.message)
                }

                override fun onAdOpened() = Unit

                override fun onAdClicked() = Unit

                override fun onAdLeftApplication() = Unit

                override fun onAdClosed() = Unit
            }
        }
    }

    private fun showInterstitial() {
        if (mInterstitialAd?.isLoaded == true)
            mInterstitialAd?.show()
        else
            loadInterstitial(activity = mActivity)
    }

    fun showFbNativeAd(
        activity: Activity?,
        mNativeAdContainer: NativeAdLayout,
        templateView: TemplateView
    ) {

        // Instantiate a NativeAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        preference = Preference(activity)
        nativeAd = NativeAd(activity, AppConstant.FB_NATIVE_ID);

        if (BuildConfig.DEBUG) {
            RequestConfiguration.Builder().setTestDeviceIds(
                Arrays.asList("821932DD6AFBE2E8EB60FF092A8883A2")
            )
            AdSettings.addTestDevice("3ceb0ed2-4040-4827-a3ab-86372dc0b925")
            AdSettings.addTestDevice("1ea5a4cb-5766-421d-a3f2-b1fdffe39c80")
            AdSettings.turnOnSDKDebugger(activity)
            AdSettings.setTestMode(true)
        }

        val nativeAdListener = object : NativeAdListener {

            override fun onAdClicked(ad: Ad?) = Unit

            override fun onMediaDownloaded(ad: Ad?) = Unit

            override fun onError(ad: Ad?, adError: AdError?) {
                Log.d("new_app_ads_error_fb", adError?.errorMessage.toString())
                showNativeAd(activity, templateView)
            }

            override fun onAdLoaded(ad: Ad?) {
                val adView = NativeAdView.render(activity, nativeAd)
                activity?.findViewById(R.id.native_ad_container) as LinearLayout
                mNativeAdContainer.addView(adView, ViewGroup.LayoutParams(MATCH_PARENT, 800))
                mNativeAdContainer.visibility = View.VISIBLE
            }

            override fun onLoggingImpression(ad: Ad?) = Unit
        }

        // Request an ad
        nativeAd?.loadAd(
            nativeAd?.buildLoadAdConfig()
                ?.withAdListener(nativeAdListener)
                ?.build()
        )
    }


    fun showNativeAd(activity: Activity?, templateView: TemplateView) {

        mActivity = activity
        preference = Preference(activity);
        val styles = NativeTemplateStyle.Builder().build()
        var adView = templateView

        val adLoader: AdLoader = AdLoader.Builder(
            activity,
            AppConstant.NATIVE_ID
        )
            .forUnifiedNativeAd { unifiedNativeAd ->
                adView.visibility = View.VISIBLE
                adView.setStyles(styles)
                adView.setNativeAd(unifiedNativeAd)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    //Log.d("new_app_ads_error", errorCode.toString())
                    adView.visibility = View.GONE
                    // Handle the failure by logging, altering the UI, and so on.
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder() // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()
            )
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }
}