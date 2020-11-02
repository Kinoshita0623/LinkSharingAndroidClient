package jp.kinoshita.linksharingandroidclient.application.notes.timelines

import android.accounts.Account
import io.reactivex.Single
import jp.kinoshita.linksharingandroidclient.application.notes.TimelineViewModel
import jp.kinoshita.linksharingandroidclient.model.AuthRequiredLinkSharingAPI
import jp.kinoshita.linksharingandroidclient.model.Page
import jp.kinoshita.linksharingandroidclient.model.notes.Note
import retrofit2.Response

class DefaultTimelineAdapter(
    private val api: AuthRequiredLinkSharingAPI
) : TimelineViewModel.Adapter{


    override fun onInitialLoad(): Single<Response<Page<Note>>> {
        return api.timeline(0)
    }

    override fun onLoadNext(pageNumber: Int): Single<Response<Page<Note>>> {
        return api.timeline(pageNumber)
    }
}