package br.com.rms.bankapp.utils.extensions


fun String.formatAgencyMask(): String {
    return addMask(this, "##.######-#")
}

private fun addMask(text: String, mask: String): String {
    var outText = ""
    var i = 0
    for (m in mask.toCharArray()) {
        if (m != '#') {
            outText += m
            continue
        }
        try {
            outText += text[i]
        } catch (e: Exception) {
            break
        }
        i++
    }
    return outText
}