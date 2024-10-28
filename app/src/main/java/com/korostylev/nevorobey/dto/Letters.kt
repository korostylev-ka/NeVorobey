package com.korostylev.nevorobey.dto

import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.ui.KeyboardFragment

enum class Letters(val letter: Char, val index: Int) {
    LETTER1('а', 1),
    LETTER2('б', 2),
    LETTER3('в', 3),
    LETTER4('г', 4),
    LETTER5('д', 5),
    LETTER6('е', 6),
    LETTER7('ё', 7),
    LETTER8('ж', 8),
    LETTER9('з', 9),
    LETTER10('и', 10),
    LETTER11('й', 11),
    LETTER12('к', 12),
    LETTER13('л', 13),
    LETTER14('м', 14),
    LETTER15('н', 15),
    LETTER16('о', 16),
    LETTER17('п', 17),
    LETTER18('р', 18),
    LETTER19('с', 19),
    LETTER20('т', 20),
    LETTER21('у', 21),
    LETTER22('ф', 22),
    LETTER23('х', 23),
    LETTER24('ц', 24),
    LETTER25('ч', 25),
    LETTER26('ш', 26),
    LETTER27('щ', 27),
    LETTER28('ь', 28),
    LETTER29('ы', 29),
    LETTER30('ъ', 30),
    LETTER31('э', 31),
    LETTER32('ю', 32),
    LETTER33('я', 33);

    fun returnLetterIndex(letter: Letters): Int {
        return letter.index
    }


}

