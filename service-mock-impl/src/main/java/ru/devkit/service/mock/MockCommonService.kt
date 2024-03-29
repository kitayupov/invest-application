package ru.devkit.service.mock

import android.content.Context
import com.google.gson.Gson
import ru.devkit.repository.impl.R
import ru.devkit.service.mock.data.MockData
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author k.i.tayupov
 */
@Singleton
class MockCommonService @Inject constructor(
    private val context: Context,
) {

    internal val dto = getDto()

    private fun getDto(): List<MockData> {
        try {
            val inputStream = context.resources.openRawResource(R.raw.sandbox)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()

            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }

            val jsonString = stringBuilder.toString()
            return Gson().fromJson(jsonString, Array<MockData>::class.java).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
}
