package fhnw.emoba.modules.module09.gps.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fhnw.emoba.R

private val diogenes = FontFamily(
    Font(R.font.diogenes, FontWeight.ExtraLight),
    Font(R.font.diogenes, FontWeight.Thin),
    Font(R.font.diogenes, FontWeight.Light),
    Font(R.font.diogenes, FontWeight.Normal),
    Font(R.font.diogenes, FontWeight.Bold),
    Font(R.font.diogenes, FontWeight.Black)
)


// Set of Material typography styles to start with
val typography = Typography(

        headlineLarge = TextStyle(
               fontFamily = diogenes,     // Einbinden eines Custom Fonts
               fontSize      = 96.sp,
               fontWeight    = FontWeight.Light,
               lineHeight    = 117.sp,
               letterSpacing = (-1.5).sp
       ),
        headlineMedium = TextStyle(
               fontFamily = diogenes,
               fontSize      = 60.sp,
               fontWeight    = FontWeight.Light,
               lineHeight    = 73.sp,
               letterSpacing = (-0.5).sp
       ),
       headlineSmall = TextStyle(
               fontFamily = diogenes,
               fontSize      = 48.sp,
               fontWeight    = FontWeight.Normal,
               lineHeight    = 59.sp
       ),
       titleLarge = TextStyle(
               fontFamily = diogenes,
               fontSize      = 30.sp,
               fontWeight    = FontWeight.SemiBold,
               lineHeight    = 37.sp
       ),
       titleMedium = TextStyle(
               fontFamily = diogenes,
               fontSize      = 24.sp,
               fontWeight    = FontWeight.SemiBold,
               lineHeight    = 29.sp
       ),
       titleSmall = TextStyle(
               fontFamily = diogenes,
               fontSize      = 20.sp,
               fontWeight    = FontWeight.SemiBold,
               lineHeight    = 24.sp
       ),
       bodyLarge = TextStyle(
               fontFamily = diogenes,
               fontSize      = 16.sp,
               fontWeight    = FontWeight.Normal,
               lineHeight    = 28.sp,
               letterSpacing = 0.15.sp
       ),
       bodyMedium = TextStyle(
               fontFamily = diogenes,
               fontSize      = 14.sp,
               fontWeight    = FontWeight.Medium,
               lineHeight    = 20.sp,
               letterSpacing = 0.25.sp
       ),
       labelMedium = TextStyle(
               fontFamily = diogenes,
               fontSize      = 12.sp,
               fontWeight    = FontWeight.Bold,
               lineHeight    = 16.sp,
               letterSpacing = 0.4.sp
       ),
       labelSmall = TextStyle(
               fontFamily = diogenes,
               fontSize      = 12.sp,
               fontWeight    = FontWeight.SemiBold,
               lineHeight    = 16.sp,
               letterSpacing = 1.sp
       )


)