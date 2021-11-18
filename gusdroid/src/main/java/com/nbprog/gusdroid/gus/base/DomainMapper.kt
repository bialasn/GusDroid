package com.nbprog.gusdroid.gus.base

import com.nbprog.gusdroid.model.GusResult

interface DomainMapper<From, out To>{
    fun map(objectToMap : From) : To
}