package com.example.newfans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.InterstitialAd

class MainActivity : AppCompatActivity() {

    var preference: Preference? = null
    var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_guid).text = AppConstant.FIRST_BUTTON
        findViewById<Button>(R.id.button_account).text = AppConstant.SECOND_BUTTON
        findViewById<Button>(R.id.button_subscription).text = AppConstant.THIRD_BUTTON
        findViewById<Button>(R.id.button_profile).text = AppConstant.FOURTH_BUTTON
        findViewById<Button>(R.id.button_content).text = AppConstant.FIFTH_BUTTON
        findViewById<Button>(R.id.button_king).text = AppConstant.SIXRH_BUTTON

        preference = Preference(this)

        if (AppUtil.isNetworkAvailable(this)) {
            AdsManager.showBanner(findViewById<LinearLayout>(R.id.mainBannerAd), this@MainActivity)
            AdsManager.showFbInterstitial()
            AdsManager.showFbNativeAd(
                this@MainActivity, findViewById(R.id.main_fb_native_ad_container),
                findViewById<TemplateView>(R.id.adview)
            )
        }

        findViewById<Button>(R.id.button_guid).setOnClickListener {
            Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra("title", getString(R.string.onlyfans_guide))
                putExtra("content", AppConstant.FIRST_BUTTON_CONTENT)
                startActivity(this)
            }
        }

        findViewById<Button>(R.id.button_account).setOnClickListener {
            Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra("title", "create an account")
                putExtra("content", AppConstant.SECOND_BUTTON_CONTENT)
                startActivity(this)
            }
        }

        findViewById<Button>(R.id.button_subscription).setOnClickListener {
            Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra("title", "setting your subscription rate")
                putExtra("content", AppConstant.THIRD_BUTTON_CONTENT)
                startActivity(this)
            }
        }

        findViewById<Button>(R.id.button_profile).setOnClickListener {
            Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra("title", "promote your onlyfans profile")
                putExtra("content", AppConstant.FOURTH_BUTTON_CONTENT)
                startActivity(this)
            }
        }

        findViewById<Button>(R.id.button_content).setOnClickListener {
            Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra("title", "plan your only fan content")
                putExtra("content", AppConstant.FIFTH_BUTTON_CONTENT)
                startActivity(this)
            }
        }

        findViewById<Button>(R.id.button_king).setOnClickListener {
            Intent(this@MainActivity, DetailActivity::class.java).apply {
                putExtra("title", "Retaining your fans ~ content is king")
                putExtra("content", AppConstant.SIXRH_BUTTON_CONTENT)
                startActivity(this)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        interstitialAd?.destroy()
    }
}