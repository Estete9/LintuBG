package com.naughtsmt.lintu.presentation.general_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier,
    minimizedMaxLines: Int,
    style: TextStyle,
    color: Color
) {
    var cutText by remember(text) { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val seeMoreSizeSate = remember { mutableStateOf<IntSize?>(null) }
    val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeSate.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(text, expanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!expanded
            && textLayoutResult != null
            && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(lastLineIndex)
        ) {
            var lastCharIndex = textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (charRect.left > textLayoutResult.size.width - seeMoreSize.width)

            seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutText = text.substring(startIndex = 0, endIndex = lastCharIndex)
        }
    }
    Box(modifier = modifier) {
        Text(
            text = cutText ?: text,
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResultState.value = it },
            style = style,
            textAlign = TextAlign.Justify,
            color = color
        )
        if (!expanded) {
            val density = LocalDensity.current
            Text(
                text = "... ver más",
                onTextLayout = { seeMoreSizeSate.value = it.size },
                fontWeight = FontWeight.Bold,
                style = style,
                color = MaterialTheme.colors.secondary,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .then(
                        if (seeMoreOffset != null) {
                            Modifier.offset(x = with(density) { seeMoreOffset.x.toDp() },
                                y = with(density) { seeMoreOffset.y.toDp() })
                        } else {
                            Modifier
                        }
                    )
                    .clickable {
                        expanded = true
                        cutText = null
                    }
                    .alpha(if (seeMoreOffset != null) 1f else 0f)
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ExpandableTextPreview() {
//    val testText =
//        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer quis velit a nulla elementum semper quis a tortor. Sed ultrices enim sit amet elit auctor, a tempor sapien consequat. Donec non imperdiet felis. Etiam sodales vestibulum volutpat. Phasellus eget risus molestie, dictum purus sit amet, placerat sapien. Nunc mollis odio in lacus dignissim, vitae sodales massa hendrerit. Donec convallis ligula vel volutpat mattis. In congue mattis porta. Donec feugiat iaculis risus quis feugiat. Aliquam eleifend odio vel enim varius sollicitudin. Fusce fermentum dui ut augue tincidunt ornare. Curabitur dapibus commodo ante, ac semper risus consequat eget. Nulla egestas, leo ac dictum fermentum, est nunc scelerisque nulla, et egestas dolor sem eget lectus. Nunc sollicitudin, dolor quis varius fermentum, diam augue viverra urna, eu volutpat augue felis at ante. Nullam ac risus semper, eleifend odio ut, congue dui. Nulla pretium magna orci, eu consectetur mi feugiat auctor."
//    ExpandableText(text = testText, modifier = Modifier, minimizedMaxLines = 2)
//}