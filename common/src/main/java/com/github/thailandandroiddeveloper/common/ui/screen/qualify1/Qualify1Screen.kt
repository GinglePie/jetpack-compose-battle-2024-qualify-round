package com.github.thailandandroiddeveloper.common.ui.screen.qualify1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.thailandandroiddeveloper.common.R
import com.github.thailandandroiddeveloper.common.ui.data.Profile
import com.github.thailandandroiddeveloper.common.ui.preview.Pixel7
import com.github.thailandandroiddeveloper.common.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Qualify1Screen(navController: NavController? = null) {
    // TODO: Qualify1
    val profile = Profile()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                navigationIcon = {
                    IconButton(onClick = {},
                        modifier = Modifier.size(48.dp)) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_1_menu),
                            contentDescription = "Menu")
                    }

                },
                title = {  },
                actions = {
                    IconButton(onClick = {},
                        modifier = Modifier.size(48.dp)) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_1_profile),
                            contentDescription = "Profile",
                        )
                    }

                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
       ProfileContent(profile, innerPadding, navController)
    }

}


@Composable
fun ProfileContent(profile: Profile, innerPadding: PaddingValues, navController: NavController? = null) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(innerPadding)
        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 48.dp)) {

        val buttonHeight = 48.dp

        Card(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = buttonHeight / 2)
            .clickable { navController?.navigate("qualify3") {
                launchSingleTop = true
            } },
            shape = RoundedCornerShape(16.dp)
        ) {
            Box (contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize() ) {

                Image(painter = painterResource(id = R.drawable.img_qualify_1_profile),
                    contentDescription = "image description",
                    contentScale = ContentScale.Fit)

                ProfileCardDetail(profile)
            }
        }

        Row(verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(horizontal = 48.dp)) {

            val modifierButton = Modifier.size(120.dp, buttonHeight)

            FilledTonalIconButton(modifier = modifierButton,
                colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.errorContainer),
                onClick = { }) {

                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_1_thumb_down),
                    contentDescription = "Thumb down button",
                    tint = MaterialTheme.colorScheme.onErrorContainer)
            }

            FilledTonalIconButton(modifier = modifierButton,
                colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.primaryContainer),
                onClick = { }) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_1_thumb_up),
                    contentDescription = "Thumb up button",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }

    }

}

@Composable
fun ProfileCardDetail(profile: Profile) {
    Column (verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.75f))
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 40.dp)) {

        Text(text = profile.name,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth())

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_1_gender_male),
                contentDescription = "Gender Male Icon",
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onPrimary)

            Text(text = profile.gender,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium)
        }

        Text(text = profile.description,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth())
    }
}

// region Read-only because we use this to process your score.
@Composable
@Preview(group = Pixel7.name, device = Pixel7.spec, showBackground = true)
fun Qualify1ScreenPreview() = AppTheme {
    Qualify1Screen()
}
// endregion