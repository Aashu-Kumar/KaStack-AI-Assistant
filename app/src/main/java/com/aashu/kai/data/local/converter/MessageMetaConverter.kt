package com.aashu.kai.data.local.converter

import androidx.room.TypeConverter
import com.aashu.kai.model.MessageMeta
import org.json.JSONObject

class MessageMetaConverter {

    @TypeConverter
    fun fromMessageMeta(meta: MessageMeta): String {
        return JSONObject().apply {
            put("timestamp", meta.timestamp)
            put("sender", meta.sender)
            put("status", meta.status)
        }.toString()
    }

    @TypeConverter
    fun toMessageMeta(value: String): MessageMeta {
        val json = JSONObject(value)

        return MessageMeta(
            timestamp = json.getLong("timestamp"),
            sender = json.getString("sender"),
            status = json.getString("status")
        )
    }
}