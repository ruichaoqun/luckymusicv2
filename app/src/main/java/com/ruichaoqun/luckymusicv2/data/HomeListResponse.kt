package com.ruichaoqun.luckymusicv2.data

/**
 *
 * @Author:         芮超群
 * @CreateDate:     2021/3/26 15:11
 * @Description:    HomeListResponse
 * @Version:        1.0
 */
data class HomeListResponse(
    var data:Data,
    var errorCode:Int?,
    var errorMsg:String?
) {
    data class Data(
        var curPage:Int = 0,
        var datas:List<Result>?,
        var offset:Int?,
        var pageCount:Int = 0,
        var size:Int?,
        var total:Int?
    ){
        data class Result(
            var apkLink: String?,
            var audit: Int?,
            var author: String?,
            var canEdit: Boolean?,
            var chapterId: Int?,
            var chapterName: String?,
            var collect: Boolean?,
            var courseId: Int?,
            var desc: String?,
            var descMd: String?,
            var envelopePic: String?,
            var fresh: Boolean?,
            var host: String?,
            var id: Int?,
            var link: String?,
            var niceDate: String?,
            var niceShareDate: String?,
            var origin: String?,
            var prefix: String?,
            var projectLink: String?,
            var publishTime: Long?,
            var realSuperChapterId: Int?,
            var selfVisible: Int?,
            var shareDate: Long?,
            var shareUser: String?,
            var superChapterId: Int?,
            var superChapterName: String?,
            var tags: List<Any?>?,
            var title: String?,
            var type: Int?,
            var userId: Int?,
            var visible: Int?,
            var zan: Int?
        )
    }
}