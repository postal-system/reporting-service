package io.aimc.reportingservice.util

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerDelegate<in R : Any> : ReadOnlyProperty<R, Logger> {
    override fun getValue(thisRef: R, property: KProperty<*>): Logger = LoggerFactory.getLogger(javaClass)
}
