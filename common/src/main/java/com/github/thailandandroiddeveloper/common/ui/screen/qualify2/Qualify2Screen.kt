package com.github.thailandandroiddeveloper.common.ui.screen.qualify2

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.thailandandroiddeveloper.common.R
import com.github.thailandandroiddeveloper.common.ui.data.Tutorial
import com.github.thailandandroiddeveloper.common.ui.preview.Pixel7
import com.github.thailandandroiddeveloper.common.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Qualify2Screen(navController: NavController? = null) {
    // TODO: Qualify2
    val tutorial = Tutorial()
    val pagerState = rememberPagerState(
        initialPage = if (navController == null) 3 else 0,
        initialPageOffsetFraction = 0f
    ) { 5 } //pageCount

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)
                .background(Color.White)) {
            ContentPager(pagerState, tutorial,
                Modifier
                    .fillMaxWidth()
                    .weight(1f))
            ContentPagerIndicator(pagerState, coroutineScope, Modifier.padding(top = 32.dp, bottom = 48.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 80.dp, vertical = 31.dp)
                .height(40.dp),
                onClick = {
                    when (pagerState.currentPage) {
                        pagerState.pageCount - 1 -> navigateToMainScreen(navController)

                        else -> coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage+1)
                        }
                    }
                }) {
                Text(stringResource(id = R.string.text_next))
            }
        }

        TextButton(modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(8.dp)
            .height(40.dp),
            onClick = { navigateToMainScreen(navController) }) {
            Text(stringResource(id = R.string.text_skip))
        }
    }

}

fun navigateToMainScreen(navController: NavController?) {
    navController?.navigate("qualify1") {
        this.launchSingleTop = true
        popUpTo("qualify2") {
            inclusive = true
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentPager(pagerState: PagerState, tutorial: Tutorial, modifier: Modifier) {

    HorizontalPager(state = pagerState,
        pageSize = PageSize.Fill,
        modifier = modifier) { page ->

        Column(verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .graphicsLayer {
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                    val scale = lerp(
                        start = ScaleFactor(1f, 1f),
                        stop = ScaleFactor(0.7f, 0.7f),
                        fraction = pageOffset.coerceIn(0f, 1f)
                    )
                    scaleX = scale.scaleX
                    scaleY = scale.scaleY
                    alpha = scale.scaleX
                }

        ) {

            Text(text = tutorial.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = tutorial.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Image(painter = painterResource(id = tutorial.image),
                contentDescription = "image description",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentPagerIndicator(pagerState: PagerState, coroutineScope: CoroutineScope, modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier) {
        repeat(pagerState.pageCount) { iteration ->
            when (iteration) {
                pagerState.currentPage -> Button(
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .width(32.dp)
                        .height(16.dp),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(iteration)
                        }
                    }
                ) { }
                else -> Button(
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(iteration)
                        }
                    }
                ) { }
            }
        }
    }
}
// region Read-only because we use this to process your score.
@Composable
@Preview(group = Pixel7.name, device = Pixel7.spec, showBackground = true)
fun Qualify2ScreenPreview() = AppTheme {
    Qualify2Screen()
}
// endregion