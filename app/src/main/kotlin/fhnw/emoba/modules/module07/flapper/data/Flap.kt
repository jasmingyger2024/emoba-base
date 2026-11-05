package fhnw.emoba.modules.module07.flapper.data

import org.json.JSONObject

data class Flap(
    val sender: String,
    val message: String
)
{
    constructor(json: JSONObject) : this (
        sender  = json.optString("sender", "n/a"),
        message = json.optString("message", "no message")
    )
    fun asJsonString() : String {
        return JSONObject()
            .put("sender",  sender)
            .put("message", message)
            .toString()
    }
}
