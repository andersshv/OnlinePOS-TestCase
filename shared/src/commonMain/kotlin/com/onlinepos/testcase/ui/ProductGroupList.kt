package com.onlinepos.testcase.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.onlinepos.testcase.model.ProductGroup
import com.onlinepos.testcase.util.colorFromHex
import com.onlinepos.testcase.util.dimOutColor

@Composable
fun ProductGroupList(
    productGroups: List<ProductGroup>,
    selectedGroup: ProductGroup,
    onGroupSelected: (ProductGroup) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Groups",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(end = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            productGroups.forEach { group ->
                ProductGroupButton(
                    group = group,
                    isSelected = group.id == selectedGroup.id,
                    onClick = { onGroupSelected(group) }
                )
            }
        }
    }
}

@Composable
fun ProductGroupButton(
    group: ProductGroup,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = colorFromHex(group.colorHex)
    val contentColor = /*if (isSelected) MaterialTheme.colors.onPrimary else */MaterialTheme.colors.onSurface
    val bg = /*if (isSelected) backgroundColor else*/ dimOutColor(backgroundColor)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(onClick = onClick),
        color = bg,
//        elevation = if (isSelected) 4.dp else 0.dp,
        border = if (isSelected) BorderStroke(width = 1.dp, color = colorFromHex("#000000")) else null
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(text = group.name, color = contentColor)
        }
    }
}
