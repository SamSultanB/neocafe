package com.neocafe.neocafe.utils

import com.neocafe.neocafe.R

object TestData {
    val listCoffe = listOf<Menu>(
        Menu("capuccino", "Кофейный напиток", R.drawable.coffe_test1, "Капучи́но — кофейный напиток итальянской кухни на основе эспрессо с добавлением в него подогретого вспененного молока", 140),
        Menu("Latte", "Кофейный напиток", R.drawable.coffe_test2, "ffff", 140))
    val listDesert = listOf<Menu>(
        Menu("Brauni", "Сладкое дополнение", R.drawable.desert1, "ffff", 140),
        Menu("Cruasan", "Сладкое дополнение", R.drawable.desert2, "ffff", 140)
        )
    val list = listOf<TestCategory>(
        TestCategory("Кофе", listCoffe, R.drawable.testcofe),
        TestCategory("Десерты", listDesert, R.drawable.testdesert),
        TestCategory("Десерты", listDesert, R.drawable.testdesert),
        TestCategory("Десерты", listDesert, R.drawable.testdesert),
        TestCategory("Десерты", listDesert, R.drawable.testdesert),
        TestCategory("Десерты", listDesert, R.drawable.testdesert),
        TestCategory("Десерты", listDesert, R.drawable.testdesert),
        TestCategory("Десерты", listDesert, R.drawable.testdesert)
    )

    val lastOrders = listOf<HistoryOfOrdersItem>(
        HistoryOfOrdersItem(R.drawable.filial_test1, "NeoCafe Dzerzhinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),
        HistoryOfOrdersItem(R.drawable.filial_test2, "NeoCafe Karpinka", "latte, capuccino, apple pie", "yesterday"),

    )
}