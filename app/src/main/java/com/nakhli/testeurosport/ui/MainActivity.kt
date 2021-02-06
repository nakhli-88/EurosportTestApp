package com.nakhli.testeurosport.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nakhli.testeurosport.R
import com.nakhli.testeurosport.ui.listNews.ListNewsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content,
                ListNewsFragment()
            ).commit()
    }
}