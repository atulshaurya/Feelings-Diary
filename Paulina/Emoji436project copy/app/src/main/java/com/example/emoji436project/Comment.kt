package com.example.emoji436project

class Comment(comment_content: String, uid: String, uimg: String, uname: String?) {
    private var content: String? = null
    private var uid: String? = null
    private var uimg: String? = null
    private var uname: String? = null


    fun Comment(content: String?, uid: String, uimg: String, uname: String) {
        this.content = content
        this.uid = uid
        this.uimg = uimg
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

    fun getUimg(): String? {
        return uimg
    }

    fun setUimg(uimg: String) {
        this.uimg = uimg
    }

    fun getUname(): String? {
        return uname
    }

    fun setUname(uname: String) {
        this.uname = uname
    }
}