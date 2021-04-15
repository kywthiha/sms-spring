package tripleh.ucspkku.smsmessenger.helpers

import android.content.Context
import com.simplemobiletools.commons.helpers.BaseConfig

class Config(context: Context) : BaseConfig(context) {
    companion object {
        fun newInstance(context: Context) = Config(context)
    }

    fun saveUseSIMIdAtNumber(number: String, SIMId: Int) {
        prefs.edit().putInt(USE_SIM_ID_PREFIX + number, SIMId).apply()
    }

    fun getUseSIMIdAtNumber(number: String) = prefs.getInt(USE_SIM_ID_PREFIX + number, 0)

    var showCharacterCounter: Boolean
        get() = prefs.getBoolean(SHOW_CHARACTER_COUNTER, false)
        set(showCharacterCounter) = prefs.edit().putBoolean(SHOW_CHARACTER_COUNTER, showCharacterCounter).apply()

    var addRegexFilter: String?
        get() = prefs.getString(ADD_REGEX_FILTER, DEFAULT_REGEX)
        set(regex) = prefs.edit().putString(ADD_REGEX_FILTER, regex).apply()

    var customizeSplitLength: Int
        get() = prefs.getInt(CUSTOMIZE_SPLIT_LENGTH, DEFAULT_SPLIT_LENGTH)
        set(length) = prefs.edit().putInt(CUSTOMIZE_SPLIT_LENGTH, length).apply()
}
