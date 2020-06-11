package projects.kevin.bankapp.user.detail


data class UserAccount(
    val value: Long? = 0,
    val title: String? = "",
    val desc: String? = "",
    val date: String? = ""

)

data class DetailApiResponse(
    val statementList: ArrayList<UserAccount>? = null
)
