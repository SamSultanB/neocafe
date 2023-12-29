package com.neocafe.neocafe.utils

import androidx.lifecycle.MutableLiveData
import com.neocafe.neocafe.entities.branches.Branche
import com.neocafe.neocafe.entities.menu.responses.Menu

object Constants {

    const val BASE_URL = "https://neo-cafe.org.kg/"
    var brancheId: Int = 0
    var selectedItemPosition: Int = 0
    var bonuse: Int = 0
    var userId: Int = 0
    var menu: List<Menu> = emptyList()
}