package fhnw.emoba.modules.module04.beers.data

import androidx.annotation.DrawableRes

//Todo: Datenklasse für Beer

data class Beer(
    var title : String,
    var overview : String,
    var voteAverage : Double,
    var beercolor : String,
    @param:DrawableRes var imageId : Int,
    var link : String
)

