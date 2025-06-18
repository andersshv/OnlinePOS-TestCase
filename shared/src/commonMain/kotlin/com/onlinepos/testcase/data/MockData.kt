package com.onlinepos.testcase.data

import com.onlinepos.testcase.data.MockData.GroupId.*
import com.onlinepos.testcase.model.Product
import com.onlinepos.testcase.model.ProductGroup

object MockData {

    enum class GroupId {
        Group1, Group2, Group3, Group4, Group5, Group6, Group7,
        Group8, Group9, Group10, Group11, Group12, Group13, Group14
    }

    fun groupColor(groupId: GroupId): String {
        return when (groupId) {
            Group1 -> "#FF9800"
            Group2 -> "#4CAF50"
            Group3 -> "#2196F3"
            Group4 -> "#F196F3"

            Group5 -> "#FF9800"
            Group6 -> "#4CAF50"
            Group7 -> "#2196F3"
            Group8 -> "#C5C5C5"

            Group9 -> "#C5C5C5"
            Group10 -> "#C5C5C5"
            Group11 -> "#C5C5C5"
            Group12 -> "#C5C5C5"

            Group13 -> "#C5C5C5"
            Group14 -> "#C5C5C5"
        }
    }

    fun groupName(groupId: GroupId): String {
        return when (groupId) {
            Group1 -> "Burgers";
            Group2 -> "Sandwiches";
            Group3 -> "Pizzas";
            Group4 -> "Drinks";
            Group5 -> "Kids Menu";
            Group6 -> "Salads";
            Group7 -> "Side Orders";
            Group8 -> "New 8"
            Group9 -> "New 9"
            Group10 -> "New 10"
            Group11 -> "New 11"
            Group12 -> "New 12"
            Group13 -> "New 13"
            Group14 -> "New 14"
        }
    }

    val productGroups = listOf(
        ProductGroup(Group1.name, groupName(Group1), groupColor(Group1)),
        ProductGroup(Group2.name, groupName(Group2), groupColor(Group2)),
        ProductGroup(Group3.name, groupName(Group3), groupColor(Group3)),
        ProductGroup(Group4.name, groupName(Group4), groupColor(Group4)),
        ProductGroup(Group5.name, groupName(Group5), groupColor(Group5)),
        ProductGroup(Group6.name, groupName(Group6), groupColor(Group6)),
        ProductGroup(Group7.name, groupName(Group7), groupColor(Group7)),
        ProductGroup(Group8.name, groupName(Group8), groupColor(Group8)),
        ProductGroup(Group9.name, groupName(Group9), groupColor(Group9)),
        ProductGroup(Group10.name, groupName(Group10), groupColor(Group10)),
        ProductGroup(Group11.name, groupName(Group11), groupColor(Group11)),
        ProductGroup(Group12.name, groupName(Group12), groupColor(Group12)),
        ProductGroup(Group13.name, groupName(Group13), groupColor(Group13)),
        ProductGroup(Group14.name, groupName(Group14), groupColor(Group14)),
    )

    val products = listOf(
        Product("p100", Group1.name, "Big Burger", groupColor(Group1), 62.5),
        Product("p101", Group1.name, "Cheese Burger", groupColor(Group1), 65.5),
        Product("p102", Group1.name, "Bacon Cheese Burger", groupColor(Group1), 71.5),
        Product("p103", Group1.name, "Chicken Burger", groupColor(Group1), 60.5),

        Product("p200", Group2.name, "Tuna Sandwich", groupColor(Group2), 58.0),
        Product("p201", Group2.name, "Egg and Bacon Sandwich", groupColor(Group2), 55.0),

        Product("p300", Group3.name, "Margarita", groupColor(Group3), 70.0),
        Product("p301", Group3.name, "Hawaii", groupColor(Group3), 72.0),
        Product("p302", Group3.name, "Napoli", groupColor(Group3), 74.0),
        Product("p303", Group3.name, "Verona", groupColor(Group3), 75.0),
        Product("p304", Group3.name, "Barcelona", groupColor(Group3), 80.0),
        Product("p305", Group3.name, "Special Pizza", groupColor(Group3), 85.0),
        Product("p306", Group3.name, "Chef Fantasy", groupColor(Group3), 87.5),
        Product("p307", Group3.name, "Gorgonzola", groupColor(Group3), 75.5),

        Product("p400", Group4.name, "Coca Cola", groupColor(Group4), 20.0),
        Product("p401", Group4.name, "Pepsi Cola", groupColor(Group4), 20.0),
        Product("p402", Group4.name, "Fanta", groupColor(Group4), 20.0),
        Product("p403", Group4.name, "Squash", groupColor(Group4), 20.0),
        Product("p404", Group4.name, "Lemon", groupColor(Group4), 20.0),
        Product("p405", Group4.name, "Juice", groupColor(Group4), 15.0),
        Product("p406", Group4.name, "Milk", groupColor(Group4), 10.0),

        Product("p500", Group5.name, "Funny Meal w. Cola", groupColor(Group5), 100.0),
        Product("p501", Group5.name, "Funny Meal w. Fanta", groupColor(Group5), 100.0),
        Product("p502", Group5.name, "Funny Meal w. Juice", groupColor(Group5), 95.0),
        Product("p503", Group5.name, "Funny Meal w. Milk", groupColor(Group5), 90.0),

        Product("p600", Group6.name, "Cesar Salad", groupColor(Group6), 45.0),

        Product("p700", Group7.name, "Garlic Bread", groupColor(Group7), 16.5),

//        Product("p800", Group8.name, "Garlic Bread", groupColor(Group8), 6.5),
//
//        Product("p900", Group9.name, "Garlic Bread", groupColor(Group9), 6.5),
//
//        Product("p1000", Group10.name, "Garlic Bread", groupColor(Group10), 6.5),
//
//        Product("p1100", Group11.name, "Garlic Bread", groupColor(Group11), 6.5),
//
//        Product("p1200", Group12.name, "Garlic Bread", groupColor(Group12), 6.5),
//
//        Product("p1300", Group13.name, "Garlic Bread", groupColor(Group13), 6.5),
//
//        Product("p1400", Group14.name, "Garlic Bread", groupColor(Group14), 6.5),
    )
}
