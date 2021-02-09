package com.example.newfans

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (AppUtil.isNetworkAvailable(this))
            AdsManager.apply {
                showFbInterstitial()
                showBanner(findViewById<LinearLayout>(R.id.bannerAd), this@DetailActivity)
                showFbNativeAd(
                    this@DetailActivity, findViewById(R.id.fb_native_ad_container),
                    findViewById<TemplateView>(R.id.adview)
                )
            }

        intent?.extras?.apply {
            val title = get("title") as String
            val content = get("content") as String

            findViewById<TextView>(R.id.tv_title).text = title
            findViewById<TextView>(R.id.tv_content).text = content
        }
    }
}