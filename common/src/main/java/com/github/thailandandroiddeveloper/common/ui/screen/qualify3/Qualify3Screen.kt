package com.github.thailandandroiddeveloper.common.ui.screen.qualify3

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.thailandandroiddeveloper.common.R
import com.github.thailandandroiddeveloper.common.ui.data.Profile
import com.github.thailandandroiddeveloper.common.ui.data.ProfileChat
import com.github.thailandandroiddeveloper.common.ui.data.ProfileImage
import com.github.thailandandroiddeveloper.common.ui.preview.Pixel7
import com.github.thailandandroiddeveloper.common.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Qualify3Screen(navController: NavController? = null) {
    // TODO: Qualify3
    val profile = Profile()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController?.popBackStack()
                    },
                        modifier = Modifier.size(48.dp)) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_3_back),
                            contentDescription = "Back")
                    }

                },
                title = {
                    Text(text = profile.name)
                },
                actions = {
                    IconButton(onClick = {},
                        modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_3_copy),
                            contentDescription = "Copy",
                        )
                    }

                    IconButton(onClick = {},
                        modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_3_calendar),
                            contentDescription = "Calendar",
                        )
                    }

                    IconButton(onClick = {},
                        modifier = Modifier.size(48.dp)) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_qualify_3_menu),
                            contentDescription = "Menu",
                        )
                    }

                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        ProfileDetailContent(innerPadding)
    }
}

@SuppressLint("DiscouragedApi")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileDetailContent(innerPadding: PaddingValues) {

    val context = LocalContext.current

    val tags = mutableListOf<String>()
    for (i in 1..4) {
        tags.add("Tag $i")
    }

    val profileImages = mutableListOf<ProfileImage>()
    for (i in 1..3) {
        val imageResId = context.resources.getIdentifier(
            "img_qualify_3_photo_$i",
            "drawable",
            context.packageName
        )
        profileImages.add(ProfileImage(imageResId))
    }
    profileImages.first().apply { isSelected = true }

    val profileChats = mutableListOf<ProfileChat>()
    val chats = listOf("Duis dignissim pulvinar lectus imperdiet tempus. Curabitur fringilla commodo consectetur. Sed congue blandit nibh.",
        "Morbi sed sagittis justo, at pulvinar ipsum. Praesent massa metus, interdum at suscipit a, interdum vel orci. Pellentesque in sapien. Integer faucibus mauris ac luctus aliquam accumsan.",
        "Duis dignissim pulvinar lectus imperdiet tempus. Curabitur fringilla commodo.",
        "Ut hendrerit neque nec accumsan hendrerit. Fusce lobortis rhoncus erat, a blandit nibh molestie vel. Cras commodo ligula ex, vitae venenatis lacus facilisis eget.")

    for (i in 1..4) {
        for (j in 1..4) {
            profileChats.add(
                ProfileChat(
                    imageName = R.drawable.img_qualify_3_sender,
                    name = "Lorem Ipsum",
                    description = chats[j - 1],
                    tag = "Tag $i"
                )
            )
        }
    }

    for (i in 2..3) {
        for (j in 1..4) {
            profileChats.add(
                ProfileChat(
                    imageName = R.drawable.img_qualify_3_sender,
                    name = "Lorem Ipsum",
                    description = chats[j - 1],
                    tag = "Tag $i"
                )
            )
        }
    }


    var selectTag by rememberSaveable { mutableStateOf("Tag 1") }

    LazyColumn (contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(innerPadding)) {

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier.padding(bottom = 8.dp)) {
                items(profileImages) { item ->
                    val borderColor = if (item.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
                    Image(painter = painterResource(id = item.imageName),
                        contentDescription = "image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(160.dp, 320.dp)
                            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
        }

        stickyHeader {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 8.dp)) {
                items(tags) { item ->
                    val chipColor = if (item == selectTag) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    AssistChip(modifier = Modifier.size(68.dp, 32.dp),
                        onClick = { selectTag = item },
                        label = { Text(text = item, color = chipColor) },
                        border = AssistChipDefaults.assistChipBorder(borderColor = chipColor, borderWidth = 1.dp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(items = profileChats) { item ->
            AnimatedVisibility(item.tag == selectTag, enter = fadeIn() + expandVertically(expandFrom = Alignment.Top), exit = fadeOut()+shrinkVertically(shrinkTowards = Alignment.Bottom)) {
                ProfileChatCard(item, Modifier.animateContentSize().fillMaxWidth()
                    .padding(bottom = 16.dp).heightIn(min = if (item.tag == selectTag) 96.dp else 0.dp, if (item.tag == selectTag) 96.dp else 0.dp)

                )
            }
        }

    }

}

@Composable
fun ProfileChatCard(item: ProfileChat, modifier: Modifier) {

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp)
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(corner = CornerSize(16.dp))
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(corner = CornerSize(16.dp))
            )
            .padding(16.dp)) {

        Image(painter = painterResource(id = item.imageName),
            contentDescription = "profile sender",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape))

        Column(Modifier.fillMaxWidth()) {

            Text(text = item.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium)

            Text(text = item.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium)
        }
    }

}

// region Read-only because we use this to process your score.
@Composable
@Preview(group = Pixel7.name, device = Pixel7.spec, showBackground = true)
fun Qualify3ScreenPreview() = AppTheme {
    Qualify3Screen()
}
// endregion

