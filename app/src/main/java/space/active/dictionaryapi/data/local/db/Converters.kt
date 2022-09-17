package space.active.dictionaryapi.data.local.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import space.active.dictionaryapi.data.local.db.util.JsonParser
import space.active.dictionaryapi.data.remote.dto.MeaningDto

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun jsonToMeanings(json: String): List<MeaningDto> {
        return jsonParser.fromJson<ArrayList<MeaningDto>>(
            json,
            object: TypeToken<ArrayList<MeaningDto>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun meaningsToJson(meanings: List<MeaningDto>): String {
        return jsonParser.toJson(
            meanings,
            object: TypeToken<ArrayList<MeaningDto>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun jsonToSourceUrls(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun sourceUrlsToJson(urls: List<String>): String {
        return jsonParser.toJson(
            urls,
            object: TypeToken<ArrayList<String>>(){}.type
        )?: "[]"
    }
}