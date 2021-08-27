package com.example.stateflow.onclickinterface

import com.example.stateflow.adapter.TeslaAdapter
import com.example.stateflow.response.Article

interface OnClick {

    fun teslaOnClick( viewHolder: TeslaAdapter.ViewHolder , tesla : Article , position : Int)
}