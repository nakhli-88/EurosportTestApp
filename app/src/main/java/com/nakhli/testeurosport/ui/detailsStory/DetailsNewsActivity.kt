package com.nakhli.testeurosport.ui.detailsStory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailsStoryActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, DetailsStoryFragment.newDetailsStoryInstance())
            .commit()
    }
}