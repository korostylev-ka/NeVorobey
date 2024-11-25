package ru.korostylev.nevorobey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.korostylev.nevorobey.R
import ru.korostylev.nevorobey.ui.StartFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = StartFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, null)
                .commit()
        }
    }

}