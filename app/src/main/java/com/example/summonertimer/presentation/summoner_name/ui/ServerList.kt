package com.example.summonertimer.presentation.summoner_name.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.exp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ServerList(serverList:List<String>,changeServer:(String) -> Unit,modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    var selectedValue by remember { mutableStateOf(serverList[0]) }

    Box(modifier = modifier){
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(value = selectedValue,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                })
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                serverList.forEach {
                    DropdownMenuItem(onClick = {
                        changeServer(it)
                        selectedValue = it
                        expanded = false
                    },
                    ) {
                        Text(text = it)
                    }
                }
            }
        }
    }

}