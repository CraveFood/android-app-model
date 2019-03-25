package com.cravefood.androidappmodel.data.repository.repository_util

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

class AnnotationSerializationStrategy : ExclusionStrategy {

    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.getAnnotation(JsonSerializationExclude::class.java) != null
    }

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
}