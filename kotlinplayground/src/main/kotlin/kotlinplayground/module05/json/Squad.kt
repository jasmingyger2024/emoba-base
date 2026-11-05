package kotlinplayground.module05.json

import org.json.JSONArray
import org.json.JSONObject

data class Squad(val jsonObject: JSONObject) {
    //TODO
    val squadName = jsonObject.getString("squadName")
    val homeTown = jsonObject.getString("homeTown")
    val formed = jsonObject.getInt("formed")
    val secretBase = jsonObject.getString("secretBase")
    val active = jsonObject.getBoolean("active")

    // easy ways for members
    // val members = jsonObject.getJSONArray("members").map { Member(it as JSONObject) }

    // same way as Member with member function
    val members = members(jsonObject.getJSONArray("members"))

    private fun members(jsonArray: JSONArray) : List<Member>{
        val list = mutableListOf<Member>()
        for (i in 0 until jsonArray.length()) {
            list.add(Member(jsonArray.getJSONObject(i)))
        }
        return list
    }
}