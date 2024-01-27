package com.github.thailandandroiddeveloper.common.ui.data

data class Profile(
    val name: String = "John Doe",
    val gender: String = "Male",
    val description: String = "Lorem ipsum dolor sit amet, cd nulla lacinia, quis fringilla lorem imperdiet. Proin in quam vel odio iaculis fringilla."
)

data class ProfileImage(
    val imageName: Int,
    var isSelected: Boolean = false
)

data class ProfileChat(
    val imageName: Int,
    val name: String,
    val description: String,
    val tag: String
)