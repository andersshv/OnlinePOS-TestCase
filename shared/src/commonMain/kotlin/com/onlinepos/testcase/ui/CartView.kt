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
import androidx.compose.ui.unit.dp
import com.onlinepos.testcase.model.CartItem
import com.onlinepos.testcase.state.MainViewModel
import com.onlinepos.testcase.util.colorFromHex
import com.onlinepos.testcase.util.dimOutColor
import com.onlinepos.testcase.util.formatPrice

@Composable
fun CartView(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Order no. 123",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            if (viewModel.cartItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No products added")
                }
            }
            else {
                viewModel.cartItems.sortedBy{ it.product.groupId }.forEach { item ->
                    CartItemRow(
                        item = item,
                        onIncrease = { viewModel.increaseQuantity(item.product.id) },
                        onDecrease = { viewModel.decreaseQuantity(item.product.id) },
                        onRemove = { viewModel.removeFromCart(item.product.id) }
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }

        // Total + Pay Button
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = "Total: " + formatPrice(viewModel.totalPrice),
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
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
            .background(color = dimOutColor(colorFromHex(item.product.colorHex)))
    ) {
        // Product name
        Text(
            text = item.product.name,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 4.dp)
        )

        // Quantity and actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Quantity buttons
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { if (item.quantity > 1) onDecrease() },
                    enabled = item.quantity > 1
                ) {
                    Text("-", style = MaterialTheme.typography.button)
                }

                Text(
                    text = "${item.quantity}",
                    modifier = Modifier
                        .width(32.dp)
                        .padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.body1
                )

                IconButton(onClick = onIncrease) {
                    Text("+", style = MaterialTheme.typography.button)
                }
            }

            // Price + Remove
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = formatPrice(item.totalPrice),
                    modifier = Modifier.padding(end = 8.dp)
                )
                IconButton(onClick = onRemove) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove item")
                }
            }
        }
    }
}

