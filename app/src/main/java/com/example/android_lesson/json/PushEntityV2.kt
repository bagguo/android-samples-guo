package com.example.android_lesson.json

/**
 * t	接收者 ID 。
 * f	发送者 ID 。
 * m	消息 ID 。
 * g	群组 ID ，当消息是群组消息时，这个值会被赋值。
 * e	用户自定义扩展。
 */
data class PushEntityV2(
    val t: String?,
    val f: String?,
    val m: String?,
    val g: String?,
    val e: NotiExt?,
    val EPush: NotiEPushModel?,
    val alert: String?
)


/**
 * 用户自定义扩展
 */
data class NotiExt(
    val isPush: Boolean?,
    val em_push_title_zapry: String?,
    val em_push_content: String?,
    val data: NotiData?,
    val body: NotiBody?,
    val em_alert_body: String?,
    val em_alert_title: String?,
    val em_push_name: String?,
)

/**
 * data\body字段一致，兼容旧数据
 * 优先拿data，没有data用body
 *
 * 目前已知：
 * data:
 * 部落申请、添加好友 场景使用
 *
 * body:
 * 聊天消息 场景使用
 */
data class NotiData(
    // 1 单聊 2 群聊 3 超级群（部落）4 互动通知消息 5 更多消息 6 消息通知 7 互动通知 8 跳转到部落主页 9\10\12 跳转到支付通知 13 跳转通讯录，加好友 14 邀请好友
    val type: Int?,
    val tb_mid: String?,
    val targetId: String?, //会话ID
    val order_no: String?,//订单Id
    val tb_zid: String?, //分组加密ID
    val tb_cd: String?,
    val map_id: String?, //会话Id
)

data class NotiBody(
    // 1 单聊 2 群聊 3 超级群（部落）4 互动通知消息 5 更多消息 6 消息通知 7 互动通知 8 跳转到部落主页 9\10\12 跳转到支付通知 13 跳转通讯录，加好友 14 邀请好友
    val type: Int?,
    val tb_mid: String?,
    val targetId: String?, //会话ID
    val order_no: String?,//订单Id
    val tb_zid: String?, //分组加密ID
    val tb_cd: String?,
    val map_id: String?, //会话Id
)

data class NotiEPushModel(
    val origin: String?,
    val msg_id: String?,
)
