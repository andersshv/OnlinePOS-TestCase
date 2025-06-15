package com.onlinepos.testcase.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.onlinepos.testcase.state.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = remember { MainViewModel() }
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp),
    ) {
        // Product Groups (Left Sidebar)
        ProductGroupList(
            productGroups = viewModel.productGroups,
            selectedGroup = viewModel.selectedGroup,
            onGroupSelected = { viewModel.selectGroup(it) },
            modifier = Modifier
                .fillMaxHeight()
                .width(150.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Product Grid (Center)
        ProductGrid(
            products = viewModel.currentProducts,
            onProductClick = { viewModel.addToCart(it) },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )


        Spacer(modifier = Modifier.width(16.dp))

        // Cart (Right Sidebar)
        CartView(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxHeight()
                .width(250.dp)
        )
    }
}
