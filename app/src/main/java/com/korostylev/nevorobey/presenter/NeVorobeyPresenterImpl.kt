package com.korostylev.nevorobey.presenter

import android.util.Log
import com.korostylev.nevorobey.model.NeVorobeyModel
import com.korostylev.nevorobey.model.NeVorobeyModelImpl
import com.korostylev.nevorobey.ui.KeyboardAction
import com.korostylev.nevorobey.ui.ViewInterface

class NeVorobeyPresenterImpl(private var viewInterface: ViewInterface): NeVorobeyPresenter, KeyboardAction {
    private val model: NeVorobeyModel = NeVorobeyModelImpl()
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


}