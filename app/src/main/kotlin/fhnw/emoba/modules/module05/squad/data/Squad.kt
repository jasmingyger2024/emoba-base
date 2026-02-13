package fhnw.emoba.modules.module05.squad.data

import org.json.JSONArray
import org.json.JSONObject

data class Squad(val jsonObject: JSONObject){
    val squadName: String       = jsonObject.getString("squadName")
    val homeTown: String        = jsonObject.optString("homeTown","")
    val formed: Int             = jsonObject.optInt("formed",0)
    val secretBase: String      = jsonObject.optString("secretBase","")
    val active: Boolean         = jsonObject.optBoolean("active",false)
    val members: List<Member>   = members(jsonObject.getJSONArray("members"))

    constructor(jsonString: String) : this(JSONObject(jsonString))
    
    private fun members(memberArray: JSONArray) : List<Member>{
        val list: MutableList<Member> = mutableListOf()
        for (i in 0 until memberArray.length()) {
            list.add(Member(memberArray.getJSONObject(i)))
        }
        return list
    }

}