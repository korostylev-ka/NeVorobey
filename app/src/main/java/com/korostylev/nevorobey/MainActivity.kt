package com.korostylev.nevorobey

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.korostylev.nevorobey.presenter.NeVorobeyPresenter
import com.korostylev.nevorobey.ui.FiveLettersFragment
import com.korostylev.nevorobey.ui.KeyboardAction
import com.korostylev.nevorobey.ui.StartFragment

class MainActivity : AppCompatActivity(), KeyboardAction {
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

    override fun pressed(text: String) {

        FiveLettersFragment.getTheLetterFromKeyboard(text)
    }
}