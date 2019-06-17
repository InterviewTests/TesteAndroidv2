package com.accenture.santander.utils


object MaskAgency {

    @JvmStatic
    fun mask(agency: String?): String? {
        if (agency?.length ?: 0 == 9)
            return "${agency?.substring(0, 2)}.${agency?.substring(2, 8)}-${agency?.substring(8, agency.length)}"
        return agency
    }
}