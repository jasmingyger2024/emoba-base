package fhnw.emoba.modules.module07.flapper_solution.data

import org.json.JSONObject


data class Flap(val sender: String,
                val message: String) : Message {

    constructor(json : JSONObject): this(json.getString("sender"),
                                         json.getString("message"))

    override fun asJsonString(): String {
        return """
            {"sender":  "$sender", 
             "message": "$message" 
            }
            """
    }
}