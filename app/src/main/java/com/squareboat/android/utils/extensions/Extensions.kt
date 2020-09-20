package com.squareboat.android.utils.extensions

fun List<String>.toStringWithComma(): String {
    var string = ""
    forEach() {
        string = "$string, $it"
    }

    return string.substring(2)
}

fun Int?.orElse(): Int {
    return this ?: 0
}

fun Int?.orElse(defaultValue: Int): Int {
    return this ?: defaultValue
}

fun <T> ArrayList<T>.togglePush(item: T) {
    if (contains(item)) {
        remove(item)
    } else {
        add(item)
    }
}

fun <T> List<T>.second() = this[1]