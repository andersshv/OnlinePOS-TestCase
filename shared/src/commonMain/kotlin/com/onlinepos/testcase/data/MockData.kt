package com.onlinepos.testcase.data

import com.onlinepos.testcase.model.Product
import com.onlinepos.testcase.model.ProductGroup

object MockData {

    val productGroups = listOf(
        ProductGroup("grp1", "Drinks", "#FF9800"),
        ProductGroup("grp2", "Snacks", "#4CAF50"),
        ProductGroup("grp3", "Meals", "#2196F3")
    )

    val products = listOf(
        Product("p1", "grp1", "Cola", "#FFC107", 2.5),
        Product("p2", "grp1", "Water", "#03A9F4", 1.0),
        Product("p3", "grp2", "Chips", "#8BC34A", 1.5),
        Product("p4", "grp2", "Chocolate", "#795548", 2.0),
        Product("p5", "grp3", "Burger", "#FF5722", 5.0),
        Product("p6", "grp3", "Pizza", "#9C27B0", 6.5)
    )
}
