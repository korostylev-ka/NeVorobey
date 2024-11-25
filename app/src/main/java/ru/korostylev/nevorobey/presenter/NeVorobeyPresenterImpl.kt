package ru.korostylev.nevorobey.presenter

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.korostylev.nevorobey.db.NeVorobeyDB
import ru.korostylev.nevorobey.dto.Level
import ru.korostylev.nevorobey.entity.ActiveGameEntity
import ru.korostylev.nevorobey.entity.UsedWordsEntity
import ru.korostylev.nevorobey.model.NeVorobeyModel
import ru.korostylev.nevorobey.model.NeVorobeyModelImpl
import ru.korostylev.nevorobey.ui.ViewInterface


class NeVorobeyPresenterImpl(private var viewInterface: ViewInterface, val context: Context, val currentLevel: Int, level: Level): NeVorobeyPresenter {


    private val model: NeVorobeyModel = NeVorobeyModelImpl(NeVorobeyDB.getInstance(context).activeGameDao, NeVorobeyDB.getInstance(context).usedWordsDao, level)

    override val wordsFromDBLiveData: LiveData<List<UsedWordsEntity>>
        get() = model.getWordsFromDB()

    private var _wordsFromDBLiveData = MutableLiveData<List<UsedWordsEntity>>()

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

    override fun getCurrentGame(): ActiveGameEntity? {
        return model.getCurrentGame()
    }

    override fun getWordsFromDB(): LiveData<List<UsedWordsEntity>> {
        return model.getWordsFromDB()
    }

    override fun saveWordToDB(usedWordsEntity: UsedWordsEntity) {
        model.saveWordToDB(usedWordsEntity)
    }

    override fun deleteWordsFromDB() {
        model.deleteWordsFromDB()
    }

    override fun saveCurrentGame(activeGameEntity: ActiveGameEntity) {
        model.saveCurrentGame(activeGameEntity)
    }

    override fun finishGame() {
        model.finishGame()
    }

    override suspend fun getRandomWord(wordSize: Int): String {
        return model.getRandomWord(wordSize)
    }

    override suspend fun isWordExist(word: String): Boolean {
        return model.isWordExist(word)
    }

//    init {
//        model.saveCurrentGame(ActiveGameEntity(0, true, currentLevel))
//    }


}