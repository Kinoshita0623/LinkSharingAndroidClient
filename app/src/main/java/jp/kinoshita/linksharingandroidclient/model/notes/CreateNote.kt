package jp.kinoshita.linksharingandroidclient.model.notes

data class CreateNote(
    val text: String,
    val url: String,
    val tags: List<String>
)