package fhnw.emoba.modules.module08.phrasomat.data

object generator {
    private val wordListOne = listOf(
        "verlässliche", "erfolgsorientierte", "webbasierte", "allumfassende",
        "clevere", "kundenorientierte", "pfadkritische", "dynamische",
        "konkurrenzfähige", "verteilte", "zielgerichtete"
    )

    private val wordListTwo = listOf(
        "gepowerte", "haftende", "Mehrwert-", "zentrierte", "geclusterte",
        "proaktive", "Out-of-the-Box", "positionierte", "vernetzte",
        "fokussierte", "kraftvolle", "geordnete", "geteilte", "kooperative",
        "beschleunigte", "Multi-Tier-", "Enterprise-", "B2B-", "Frontend-"
    )

    private val wordListThree = listOf(
        "Schicht", "Endstufe", "Lösung", "Architektur", "Kernkompentenz",
        "Strategie", "Kooperation", "Ausrichtung", "Räumlichkeit", "Vision",
        "Dimension", "Mission"
    )

    fun generatePhase() : String = wordListOne.random() + " " + wordListTwo.random() + " " + wordListThree.random()
}