package com.korostylev.nevorobey.presenter

import android.app.Application
import android.content.Context
import android.util.Log
import com.korostylev.nevorobey.application.NeVorobeyApplication
import com.korostylev.nevorobey.db.NeVorobeyDB
import com.korostylev.nevorobey.entity.ActiveGameEntity
import com.korostylev.nevorobey.model.NeVorobeyModel
import com.korostylev.nevorobey.model.NeVorobeyModelImpl
import com.korostylev.nevorobey.ui.KeyboardAction
import com.korostylev.nevorobey.ui.ViewInterface

class NeVorobeyPresenterImpl(private var viewInterface: ViewInterface, val context: Context, val currentLevel: Int): NeVorobeyPresenter, KeyboardAction {




    private val model: NeVorobeyModel = NeVorobeyModelImpl(NeVorobeyDB.getInstance(context).activeGameDao)
    override fun checkWord(word: String) {
        val currentRow = model.checkWord(word)
        viewInterface.checkWord(currentRow)
    }

    override fun getCurrentRow() = model.getCurrentRow()

    override fun updateCurrentRow() {
        model.updateCurrentRow()
    }

    override fun clearInputFields() {
        viewInterface.clearInputFields()
    }

    override fun getTheLetter(text: String) {
        Log.d("vorobey", "12345")
    }

    override fun pressed(text: String) {
        Log.d("vorobey", "text")
    }

    init {
        model.saveCurrentGame(ActiveGameEntity(0, true, currentLevel))
    }


}