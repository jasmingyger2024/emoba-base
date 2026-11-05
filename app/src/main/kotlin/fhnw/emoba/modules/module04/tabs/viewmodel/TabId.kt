package fhnw.emoba.modules.module04.tabs.viewmodel

import androidx.annotation.DrawableRes
import fhnw.emoba.R

enum class TabId(val title : String, @param:DrawableRes val imageDrawble: Int) {
    FIRST ("Frühling", R.drawable.spring),
    SECOND ("Sommer", R.drawable.summer),
    THIRD ("Herbst", R.drawable.autumn),
    FOURTH ("Winter", R.drawable.winter)
}