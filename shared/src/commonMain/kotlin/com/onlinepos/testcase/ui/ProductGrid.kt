package com.onlinepos.testcase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.onlinepos.testcase.model.Product
import com.onlinepos.testcase.model.ProductGroup
import com.onlinepos.testcase.util.colorFromHex
import com.onlinepos.testcase.util.dimOutColor
import com.onlinepos.testcase.util.formatPrice

@Composable
fun ProductGrid(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Products",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 110.dp),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(products) { product ->
                ProductTile(product = product, onClick = { onProductClick(product) })
            }
        }
    }
}

@Composable
fun ProductTile(
    product: Product,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = dimOutColor(colorFromHex(product.colorHex)))
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start // 👈 Force left-align
        ) {
            Text(text = product.name, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = formatPrice(product.price), style = MaterialTheme.typography.caption)
        }
    }
}
