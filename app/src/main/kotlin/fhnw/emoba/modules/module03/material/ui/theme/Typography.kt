package fhnw.emoba.modules.module03.material.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fhnw.emoba.R

private val Lato = FontFamily(
        Font(R.font.lato_extra_light, FontWeight.ExtraLight),
        Font(R.font.lato_hai,         FontWeight.Thin),
        Font(R.font.lato_lig,         FontWeight.Light),
        Font(R.font.lato_reg,         FontWeight.Normal),
        Font(R.font.lato_bol,         FontWeight.Bold),
        Font(R.font.lato_bla,         FontWeight.Black)
)

// Set of Material typography styles to start with
val typography = Typography(

        headlineLarge = TextStyle(
               fontFamily = Lato,     // Einbinden eines Custom Fonts
               fontSize      = 96.sp,
               fontWeight    = FontWeight.Light,
               lineHeight    = 117.sp,
               letterSpacing = (-1.5).sp
       ),
        headlineMedium = TextStyle(
               fontFamily = Lato,
               fontSize      = 60.sp,
               fontWeight    = FontWeight.Light,
               lineHeight    = 73.sp,
               letterSpacing = (-0.5).sp
       ),
       headlineSmall = TextStyle(
               fontFamily = Lato,
               fontSize      = 48.sp,
               fontWeight    = FontWeight.Normal,
               lineHeight    = 59.sp
       ),
       titleLarge = TextStyle(
               fontFamily = Lato,
               fontSize      = 30.sp,
               fontWeight    = FontWeight.SemiBold,
               lineHeight    = 37.sp
       ),
       titleMedium = TextStyle(
               fontFamily = Lato,
               fontSize      = 24.sp,
               fontWeight    = FontWeight.SemiBold,
               lineHeight    = 29.sp
       ),
       titleSmall = TextStyle(
               fontFamily = Lato,
               fontSize      = 20.sp,
               fontWeight    = FontWeight.SemiBold,
               lineHeight    = 24.sp
       ),
       bodyLarge = TextStyle(
               fontFamily = Lato,
               fontSize      = 16.sp,
               fontWeight    = FontWeight.Normal,
               lineHeight    = 28.sp,
               letterSpacing = 0.15.sp
       ),
       bodyMedium = TextStyle(
               fontFamily = Lato,
               fontSize      = 14.sp,
               fontWeight    = FontWeight.Medium,
               lineHeight    = 20.sp,
               letterSpacing = 0.25.sp
       ),
       labelMedium = TextStyle(
               fontFamily = Lato,
               fontSize      = 12.sp,
               fontWeight    = FontWeight.Bold,
               lineHeight    = 16.sp,
               letterSpacing = 0.4.sp
       ),
       labelSmall = TextStyle(
               fontFamily = Lato,
               fontSize      = 12.sp,
               fontWeight    = FontWeight.SemiBold,
               lineHeight    = 16.sp,
               letterSpacing = 1.sp
       )


)


// Set of Material typography styles to start with
val typographyBig = Typography(

        headlineLarge = TextStyle(
               fontFamily = Lato,  // Einbinden eines Custom Fonts
              fontSize      = 106.sp,
              fontWeight    = FontWeight.Normal,
              lineHeight    = 137.sp,
              letterSpacing = (-1.5).sp
      ),
        headlineMedium = TextStyle(
               fontFamily = Lato,
              fontSize      = 80.sp,
              fontWeight    = FontWeight.Normal,
              lineHeight    = 93.sp,
              letterSpacing = (-0.5).sp
      ),
        headlineSmall = TextStyle(
               fontFamily = Lato,
              fontSize      = 68.sp,
              fontWeight    = FontWeight.SemiBold,
              lineHeight    = 79.sp
      ),
        titleLarge = TextStyle(
               fontFamily = Lato,
              fontSize      = 50.sp,
              fontWeight    = FontWeight.SemiBold,
              lineHeight    = 57.sp
      ),
        titleMedium = TextStyle(
               fontFamily = Lato,
              fontSize      = 44.sp,
              fontWeight    = FontWeight.SemiBold,
              lineHeight    = 49.sp
      ),
      titleSmall = TextStyle(
               fontFamily = Lato,
              fontSize      = 40.sp,
              fontWeight    = FontWeight.SemiBold,
              lineHeight    = 44.sp
      ),
      bodyLarge = TextStyle(
               fontFamily = Lato,
              fontSize      = 36.sp,
              fontWeight    = FontWeight.Normal,
              lineHeight    = 48.sp,
              letterSpacing = 0.15.sp
      ),
        bodyMedium = TextStyle(
               fontFamily = Lato,
              fontSize      = 34.sp,
              fontWeight    = FontWeight.Medium,
              lineHeight    = 40.sp,
              letterSpacing = 0.25.sp
      ),
      labelMedium = TextStyle(
               fontFamily = Lato,
              fontSize      = 32.sp,
              fontWeight    = FontWeight.Bold,
              lineHeight    = 36.sp,
              letterSpacing = 0.4.sp
      ),
      labelSmall = TextStyle(
               fontFamily = Lato,
              fontSize      = 32.sp,
              fontWeight    = FontWeight.SemiBold,
              lineHeight    = 36.sp,
              letterSpacing = 1.sp
      )
)