package com.example.profilescreen.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.profilescreen.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var name by remember { mutableStateOf("Imran Mamirov") }
    var aboutMe by remember { mutableStateOf("I am a passionate mobile developer with a strong background in programming languages like Kotlin, Java, and Swift. I have experience in developing cross-platform applications for Android and iOS using the Flutter and Kotlin/Native frameworks.") }
    var isDialogOpen by remember { mutableStateOf(false) }
    var dialogType by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Profile")
            }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage()
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .clickable {
                            dialogType = "name"
                            isDialogOpen = true
                        },
                    text = name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Mobile Developer",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        BoldText(text = "About me")
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    dialogType = "aboutMe"
                    isDialogOpen = true
                },
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontStyle = FontStyle.Italic,
            text = aboutMe,
        )

        if (isDialogOpen) {
            EditTextDialog(
                initialText = if (dialogType == "name") name else aboutMe,
                onDismiss = { isDialogOpen = false },
                onSave = { newText ->
                    if (newText.isNotBlank()) {
                        if (dialogType == "name") {
                            name = newText
                        } else {
                            aboutMe = newText
                        }
                        isDialogOpen = false
                    }
                }
            )
        }
    }
}

@Composable
fun EditTextDialog(
    initialText: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var inputText by remember { mutableStateOf(initialText) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    text = "Edit Text"
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = inputText,
                    onValueChange = { inputText = it },
                    singleLine = false,
                    placeholder = { Text(text = "Enter text here...") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = { onSave(inputText) }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileImage() {
    Image(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(BorderStroke(2.dp, Color.Black), shape = CircleShape),
        painter = painterResource(R.drawable.icon_amg),
        contentDescription = "Profile Icon",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BoldText(text: String) {
    Text(
        fontSize = 24.sp,
        text = text,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview(showBackground = true)
fun ProfileScreen_Preview() {
    ProfileScreen()
}