package com.example.diary.ui.home

class Comment(comment_content: String, uid: String, uname: String?) {
    private var content: String? = null
    private var uid: String? = null
    private var uname: String? = null


    fun Comment(content: String?, uid: String, uname: String) {
        this.content = content
        this.uid = uid
        this.uname = uname
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    fun getUid(): String? {
        return uid
    }

    fun setUid(uid: String) {
        this.uid = uid
    }

    fun getUname(): String? {
        return uname
    }

    fun setUname(uname: String) {
        this.uname = uname
    }
}