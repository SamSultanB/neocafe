package com.neocafe.neocafe.utils

import com.neocafe.neocafe.R

object TestData {
    val listCoffe = listOf<Test>(
        Test("capuccino", "Кофейный напиток", R.drawable.test1, "Капучи́но — кофейный напиток итальянской кухни на основе эспрессо с добавлением в него подогретого вспененного молока", 140),
        Test("Latte", "Кофейный напиток", R.drawable.test2, "Капучи́но — кофейный напиток итальянской кухни на основе эспрессо с добавлением в него подогретого вспененного молока", 140))
    val listDesert = listOf<Test>(
        Test("Chees Cake", "Сладкое дополнение", R.drawable.test3, "Капучи́но — кофейный напиток итальянской кухни на основе эспрессо с добавлением в него подогретого вспененного молока", 140),
        Test("Brauni", "Сладкое дополнение", R.drawable.test2, "Капучи́но — кофейный напиток итальянской кухни на основе эспрессо с добавлением в него подогретого вспененного молока", 140),
        Test("Cruasan", "Сладкое дополнение", R.drawable.test3, "Капучи́но — кофейный напиток итальянской кухни на основе эспрессо с добавлением в него подогретого вспененного молока", 140)
        )
    val list = listOf<TestCategory>(
        TestCategory("Кофе", listCoffe, R.drawable.testcofe),
        TestCategory("Десерты", listDesert, R.drawable.testdesert)
    )
}