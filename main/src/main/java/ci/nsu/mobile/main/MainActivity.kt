package ci.nsu.mobile.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    ColorAppScreen()
                }
            }
        }
    }
}

@Composable
fun ColorAppScreen() {
    val colorMap = remember {
        mapOf(
            "Red" to Color.Red,
            "Orange" to Color(0xFFFFA500),
            "Yellow" to Color.Yellow,
            "Green" to Color.Green,
            "Blue" to Color.Blue,
            "Indigo" to Color(0xFF4B0082),
            "Violet" to Color(0xFFEE82EE)
        )
    }

    var inputText by remember { mutableStateOf("") }
    var buttonColor by remember { mutableStateOf(Color.Gray) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Введите цвет (напр. Green)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val trimmed = inputText.trim()
                val found = colorMap[trimmed]
                if (found != null) {
                    buttonColor = found
                } else {
                    Log.d("ColorApp", "Пользовательский цвет '$trimmed' не найден")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text(
                text = "Применить цвет",
                color = if (buttonColor == Color.Yellow || buttonColor == Color.Green || buttonColor == Color(0xFFEE82EE)) Color.Black else Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Палитра доступных цветов:", style = MaterialTheme.typography.titleMedium)

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(colorMap.toList()) { (name, color) ->
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = color)
                ) {
                    Text(
                        text = name,
                        color = if (color == Color.Yellow || color == Color.Green || color == Color(0xFFEE82EE)) Color.Black else Color.White
                    )
                }
            }
        }
    }
}