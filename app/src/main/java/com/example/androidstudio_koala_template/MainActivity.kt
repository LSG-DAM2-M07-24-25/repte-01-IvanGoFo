package com.example.androidstudio_koala_template

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var selectedText: String by remember { mutableStateOf("Tria un Icon") }
    var expanded: Boolean by remember { mutableStateOf(false) }

    val iconList = listOf(
        Icons.Default.Home to "Home",
        Icons.Default.Favorite to "Favorite",
        Icons.Default.Search to "Search",
        Icons.Default.Settings to "Settings",
        Icons.Default.AccountCircle to "Profile",
        Icons.Default.Notifications to "Notifications",
        Icons.Default.AddCircle to "Add",
        Icons.Default.CheckCircle to "Check",
        Icons.Default.Delete to "Delete",
        Icons.Default.Info to "Info"
    )

    var selectedIcon by remember { mutableStateOf(Icons.Default.Home) }

    var sliderValue: Float by remember { mutableStateOf(0f) }
    var sliderStart: Int by remember { mutableStateOf(0) }
    var sliderEnd: Int by remember { mutableStateOf(10) }
    var finishValue: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Column(
            Modifier
                .padding(20.dp, 80.dp)
                .fillMaxWidth()
        ) {
            // OutlinedTextField con colores personalizados
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { expanded = true }
                    .fillMaxWidth(),
                // Aquí se cambia el color del texto usando textStyle
                textStyle = TextStyle(color = Color.Black),  // Cambiar color del texto aquí
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue, // Border color when focused
                    unfocusedBorderColor = Color.Gray, // Border color when unfocused
                    disabledBorderColor = Color.LightGray, // Border color when disabled
                    focusedLabelColor = Color.Blue, // Label color when focused
                    disabledLabelColor = Color.LightGray, // Label color when disabled
                    cursorColor = Color.Black // Cursor color
                )
            )

            // DropdownMenu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .border(2.dp, Color.Black, RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                iconList.forEach { (icon, name) ->
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = name,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Black
                                )
                                Text(text = name, modifier = Modifier.padding(start = 8.dp))
                            }
                        },
                        onClick = {
                            expanded = false
                            selectedText = name
                            selectedIcon = icon
                        }
                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .padding(20.dp, 40.dp)
        ) {

            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                onValueChangeFinished = {
                    finishValue = sliderValue.toInt().toString() },
                valueRange = sliderStart.toFloat()..sliderEnd.toFloat(),
                steps = sliderEnd-sliderStart,
                enabled = true
            )


            Text(text = finishValue)
        }
        Text(text = finishValue.toString())

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){

            OutlinedTextField(
                value = sliderStart.toString(),
                onValueChange = { newText ->

                    if (newText.all { it.isDigit() }) {
                        sliderStart = newText.toInt()
                    }
                },
                label = { Text("Min") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Black
                )
            )

            OutlinedTextField(
                value = sliderEnd.toString(),
                onValueChange = { newText ->

                    if (newText.all { it.isDigit() }) {
                        sliderEnd = newText.toInt()
                    }
                },
                label = { Text("Max") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Black
                )
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BadgedBox(
                modifier = Modifier
                    .padding(20.dp),
                badge = { Badge { Text(finishValue) } }
            ) {

                Icon(

                    imageVector = selectedIcon,
                    contentDescription = selectedText,
                    modifier = Modifier.size(100.dp)

                )
            }
        }


    }
}

@Preview(showBackground = true
    , showSystemUi = true)
@Composable
fun GreetingPreview() {
    AndroidStudioKoalaTemplateTheme {
        Greeting("Android")
    }
}