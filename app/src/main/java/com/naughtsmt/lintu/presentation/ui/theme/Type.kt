package com.naughtsmt.lintu.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.naughtsmt.lintu.R

// Set of Material typography styles to start with

//private val light = Font(R.font.comfortaa_wght, FontWeight.Light)
//private val regular = Font(R.font.comfortaa_wght, FontWeight.Normal)
//private val medium = Font(R.font.comfortaa_wght, FontWeight.Medium)
//private val semiBold = Font(R.font.comfortaa_wght, FontWeight.SemiBold)

//private val lintuFontFamily = FontFamily(light, regular, medium, semiBold/*, buttons*/)
val ComfortaaFonts = FontFamily(
    Font(R.font.comfortaa_wght, FontWeight.Light),
    Font(R.font.comfortaa_wght, FontWeight.Normal),
    Font(R.font.comfortaa_wght, FontWeight.Medium),
    Font(R.font.comfortaa_wght, FontWeight.SemiBold),
//    Font(R.font.montserrat_bold, FontWeight.Bold)
)
val MonserratFonts = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserratmedium_italic, FontWeight.Medium, FontStyle.Italic)
)
val Typography = Typography(
//    defaultFontFamily = lintuFontFamily,
    h1 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W300,
        fontSize = 92.sp
    ),
    h2 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W400,
        fontSize = 56.sp
    ),
    h3 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W600,
        fontSize = 44.sp
    ),
    h4 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp
    ),
    h6 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp
    ),
    body1 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    button = TextStyle(

        fontFamily = MonserratFonts,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = ComfortaaFonts,
        fontWeight = FontWeight.W400,
        fontSize = 13.sp
    )
)
//val Typography = Typography(
//    body1 = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp
//    )
//    /* Other default text styles to override
//    button = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.W500,
//        fontSize = 14.sp
//    ),
//    caption = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    )
//    */
//)