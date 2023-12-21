package com.example.android_lesson.net.rxjava;

class FeedArticleBean {
    /**
     * apkLink: "",
     * audit: 1,
     * author: "",
     * canEdit: false,
     * chapterId: 502,
     * chapterName: "自助",
     * collect: false,
     * courseId: 13,
     * desc: "",
     * descMd: "",
     * envelopePic: "",
     * fresh: true,
     * id: 16049,
     * link: "https://mp.weixin.qq.com/s/fEgSymIZMm82T9CrLueIKA",
     * niceDate: "2小时前",
     * niceShareDate: "2小时前",
     * origin: "",
     * prefix: "",
     * projectLink: "",
     * publishTime: 1605143247000,
     * realSuperChapterId: 493,
     * selfVisible: 0,
     * shareDate: 1605143247000,
     * shareUser: "飞洋",
     * superChapterId: 494,
     * superChapterName: "广场Tab",
     * tags:[],
     * title: "🔥“终于懂了“系列：Jetpack AAC完整解析（-）Lifecycle 完全掌握！",
     * type: 0,
     * userId: 31360,
     * visible: 1,
     * zan: 0
     */
    private String apkLink;
    private int audit;
    //    author
//            canEdit
//    chapterId
//            chapterName
//    collect
//            courseId
//    desc
//            descMd
//    envelopePic
//            fresh
//    id
    //            niceShareDate
//    origin
//            prefix
//    projectLink
//            publishTime
//    realSuperChapterId
//            selfVisible
//    shareDate
//            shareUser
//    superChapterId
//            superChapterName
//    tags
    private String title;
    //    type
//            userId
//    visible
//            zan
    private String link;
    private String niceDate;


    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
