package com.gustavo.bankandroid.contants

object Constants {
    const val USER_DATABASE = "user_database"

    const val cpfRegex = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}"
    const val emailRegex = "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*\$"
    const val strongPasswordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*]).+\$"
}