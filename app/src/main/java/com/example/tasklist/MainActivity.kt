package com.example.tasklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasklist.ui.theme.TaskListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListTheme {
                TaskListScreen()
            }
        }
    }
}

@Composable
fun TaskListScreen() {
    // Use remember inside a Composable
    var name by remember { mutableStateOf("") }
    var names by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { text -> name = text },
                modifier = Modifier.weight(1f), // To avoid UI overlap
                label = { Text("Enter name") }
            )
            Spacer(modifier = Modifier.width(8.dp)) // Add space between TextField and Button
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        names = names + name
                        name = "" // Clear input after adding
                    }
                }
            ) {
                Text(text = "Add")
            }
        }
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(names) { currentName ->
                Text(text = currentName, modifier = Modifier.fillMaxWidth() .padding(16.dp) )
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskListScreen() {
    TaskListTheme {
        TaskListScreen()
    }
}


