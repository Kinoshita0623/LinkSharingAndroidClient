package jp.kinoshita.linksharingandroidclient.util

class FIFOMap<K, V>(private val capacity: Int) : LinkedHashMap<K, V>(capacity, 0.75F, false){

    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
        return super.size > capacity
    }

}