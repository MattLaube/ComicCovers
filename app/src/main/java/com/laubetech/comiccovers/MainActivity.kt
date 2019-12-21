package com.laubetech.comiccovers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.laubetech.comiccovers.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    var characterId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

}
