package fhnw.emoba.modules.module05.multiple_screens_solution.viewmodel

import androidx.annotation.DrawableRes
import fhnw.emoba.R

enum class ScreenId(val title: String, @DrawableRes val resId: Int) {
    HOME     ("1985",     R.drawable.back_to_future_home),
    YEAR_1955("1955",     R.drawable.back_to_future_date),
    YEAR_2015("2015",     R.drawable.back_to_future_hoverboard),
    ABOUT    ("About Me", R.drawable.back_to_future_about),
}