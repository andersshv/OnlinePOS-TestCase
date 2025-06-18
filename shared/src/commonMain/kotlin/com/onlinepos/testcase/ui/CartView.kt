package com.onlinepos.testcase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    var showClearAllDialog by remember { mutableStateOf(false) }
    var showCompletePaymentDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxHeight(),
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
            } else {
                viewModel.cartItems.sortedBy { it.product.groupId }.forEach { item ->
                    CartItemRow(
                        item = item,
                        onIncrease = { viewModel.increaseQuantity(item.product.id) },
                        onDecrease = { viewModel.decreaseQuantity(item.product.id) },
                        onRemove = { viewModel.removeOneFromCart(item.product.id) }
                    )
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }

        // Total + Pay Button
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total: " + formatPrice(viewModel.totalPrice),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        if (viewModel.cartItems.isNotEmpty()) {
                            showClearAllDialog = true
                        }
                    }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Clear cart")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (viewModel.cartItems.isNotEmpty()) {
                        showCompletePaymentDialog = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Done, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Pay")
            }
        }
    }

    // Confirmation Dialog
    if (showClearAllDialog) {
        AlertDialog(
            onDismissRequest = { showClearAllDialog = false },
            title = { Text("Clear Order") },
            text = { Text("Are you sure you want to clear the order and remove all items from the cart?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.clearCart()
                    showClearAllDialog = false
                }) {
                    Text("Clear Order")
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearAllDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Payment Dialog
    if (showCompletePaymentDialog) {
        AlertDialog(
            onDismissRequest = { showCompletePaymentDialog = false },
            buttons = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Cancel X button at top-right
                    IconButton(
                        onClick = { showCompletePaymentDialog = false },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Cancel Payment")
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp), // space for the X button
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Total Text
                        Text(
                            text = "Total: " + formatPrice(viewModel.totalPrice),
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )

                        // Blinking Dots
                        val transition = rememberInfiniteTransition()
                        val alpha1 by transition.animateFloat(
                            initialValue = 0.3f,
                            targetValue = 1f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(600),
                                repeatMode = RepeatMode.Reverse
                            )
                        )
                        val alpha2 by transition.animateFloat(
                            initialValue = 0.3f,
                            targetValue = 1f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(600, delayMillis = 200),
                                repeatMode = RepeatMode.Reverse
                            )
                        )
                        val alpha3 by transition.animateFloat(
                            initialValue = 0.3f,
                            targetValue = 1f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(600, delayMillis = 400),
                                repeatMode = RepeatMode.Reverse
                            )
                        )

                        Row(
                            modifier = Modifier
                                .padding(bottom = 32.dp)
                                .height(24.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(12.dp).alpha(alpha1).background(MaterialTheme.colors.primary, shape = CircleShape))
                            Box(modifier = Modifier.size(12.dp).alpha(alpha2).background(MaterialTheme.colors.primary, shape = CircleShape))
                            Box(modifier = Modifier.size(12.dp).alpha(alpha3).background(MaterialTheme.colors.primary, shape = CircleShape))
                        }

                        // Payment Received Button
                        Button(
                            onClick = {
                                viewModel.clearCart()
                                showCompletePaymentDialog = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.Done, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Payment Received")
                        }
                    }
                }
            },
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = contentColorFor(MaterialTheme.colors.surface)
        )
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
//                    fontWeight = FontWeight.SemiBold,
//                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(end = 8.dp)
                )
                IconButton(onClick = onRemove) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove item")
                }
            }
        }
    }
}

