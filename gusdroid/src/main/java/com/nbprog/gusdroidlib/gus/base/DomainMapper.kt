package com.nbprog.gusdroidlib.gus.base

interface DomainMapper<From, out To>{
    fun map(objectToMap : From) : To
}