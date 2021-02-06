package com.nakhli.testeurosport.ui.videoPlay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class VideoPlayActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, VideoPlayFragment.newVideoInstance())
            .commit()
    }
}