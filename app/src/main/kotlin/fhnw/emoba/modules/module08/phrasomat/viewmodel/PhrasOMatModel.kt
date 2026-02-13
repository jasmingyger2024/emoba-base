package fhnw.emoba.modules.module08.phrasomat.viewmodel

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class PhrasOMatModel : ViewModel(){
    val title = "PhrasOMat"

    //Todo 1: geeignete Liste fuer die Phrasen definieren



    //Todo 2: in Data-Layer verschieben
    private val wordListOne  = listOf("verlässliche", "erfolgsorientierte", "webbasierte", "allumfassende",
                                      "clevere", "kundenorientierte", "pfadkritische", "dynamische",
                                      "konkurrenzfähige", "verteilte", "zielgerichtete")

    private val wordListTwo  = listOf("gepowerte", "haftende", "Mehrwert-", "zentrierte", "geclusterte",
                                      "proaktive", "Out-of-the-Box", "positionierte", "vernetzte",
                                      "fokussierte", "kraftvolle", "geordnete", "geteilte", "kooperative",
                                      "beschleunigte", "Multi-Tier-", "Enterprise-", "B2B-", "Frontend-")

    private val wordListThree = listOf("Schicht", "Endstufe", "Lösung", "Architektur", "Kernkompentenz",
                                       "Strategie", "Kooperation", "Ausrichtung", "Räumlichkeit", "Vision",
                                       "Dimension", "Mission")

    //Todo 2: wie kann das einfacher/uebersichtlicher implementiert werden?
    private fun generatePhrase(): String =
            wordListOne  [Random.nextInt(1, wordListOne.size)] + " " +
            wordListTwo  [Random.nextInt(1, wordListTwo.size)] + " " +
            wordListThree[Random.nextInt(1, wordListOne.size)] + " "



    //Todo 1: Funktion, die neue Phrase einer Liste hinzufuegt



    //Todo 2: PhrasenGenerator asnychron aufrufen

}