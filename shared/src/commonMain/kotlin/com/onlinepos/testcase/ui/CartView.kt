package com.onlinepos.testcase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.onlinepos.testcase.model.CartItem
import com.onlinepos.testcase.state.MainViewModel

@Composable
fun CartView(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(Color.White)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Order no. 123",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (viewModel.cartItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No products added")
                }
            } else {
                viewModel.cartItems.forEach { item ->
                    CartItemRow(
                        item = item,
                        onRemove = { viewModel.removeFromCart(item.product.id) }
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }

        // Total + Pay Button
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = "Total: %.2f".format(viewModel.totalPrice),
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.simulatePayment() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Pay")
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItem,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${item.quantity}x",
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = item.product.name,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "%.2f".format(item.totalPrice),
            modifier = Modifier.width(60.dp)
        )
        IconButton(onClick = onRemove) {
            Icon(Icons.Default.Delete, contentDescription = "Remove item")
        }
    }
}
