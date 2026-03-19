package ci.nsu.mobile.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.nsu.mobile.main.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }

    // текущ цвет кнопки
    var buttonColor by remember { mutableStateOf(Color.Blue) }

    //нажатие на кнопку
    val onButtonClick = {
        // Берем текст из поля ввода и меняем цвет кнопки
        buttonColor = getRandomColor()
        // Можем также вывести текст в консоль для проверки
        println("Текст из поля: $inputText")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Текстовое поле
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Введите текст") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка с изменяемым цветом
        Button(
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Нажми меня")
        }
    }
}

// Вспомогательная функция для генерации случайного цвета
private fun getRandomColor(): Color {
    return Color(
        red = (0..255).random() / 255f,
        green = (0..255).random() / 255f,
        blue = (0..255).random() / 255f
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PracticeTheme {
        MainScreen()
    }
}