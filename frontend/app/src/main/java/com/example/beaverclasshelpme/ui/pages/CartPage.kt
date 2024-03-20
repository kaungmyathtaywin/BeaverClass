package com.example.beaverclasshelpme.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.beaverclasshelpme.CartItem
import com.example.beaverclasshelpme.CartItemRow

@Composable
fun CartPage(cartItems: List<CartItem>, onDeleteClick: (CartItem) -> Unit, onSubmitClick: () -> Unit, onDraftClick: (CartItem) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Cart", style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(16.dp))
        LazyColumn {
            items(cartItems) { item ->
                CartItemRow(item, onDeleteClick, onDraftClick)
            }
        }
        Button(
            onClick = onSubmitClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Submit")
        }
    }
}