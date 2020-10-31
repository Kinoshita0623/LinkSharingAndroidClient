package jp.kinoshita.linksharingandroidclient.store

sealed class NoteException : Exception(){
    class FavoriteFailedException() : NoteException()

    class UnFavoriteFailedException() : NoteException()

    class DeleteFailedException() : NoteException()


}

