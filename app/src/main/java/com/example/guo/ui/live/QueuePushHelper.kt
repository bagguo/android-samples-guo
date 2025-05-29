package com.example.guo.ui.live

import android.util.Log
import java.util.LinkedList
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * 堆栈消息帮助类
 * */
object QueuePushHelper {
    /**
     * 通过type修改监听状态
     * */
    private var type = false


    private var queuePushInterface: QueuePushInterface? = null

    fun setQueuePushInterface(queuePushInterface: QueuePushInterface) {
        this.queuePushInterface = queuePushInterface
    }

    /**
     * 缓存所有消息
     */
    private var cacheGiftList: LinkedList<QueuePushBean> =
        LinkedList<QueuePushBean>()

    /**
     * 用于更新界面消息的队列
     */
    private var uiMsgList: LinkedList<QueuePushBean> =
        LinkedList<QueuePushBean>()

    /**
     * 定时器
     */
    private var msgTimer = Executors.newScheduledThreadPool(2)

    private lateinit var futures: ScheduledFuture<*>

    /**
     * 消息加入队列
     */
    @JvmStatic
    @Synchronized
    fun onMsgReceived(customMsg: QueuePushBean) {
        cacheGiftList.offer(customMsg)
    }

    /**
     *  清空队列
     * */
    fun clearQueue() {
        if (cacheGiftList.size > 0) {
            cacheGiftList.clear()
        }
        if (uiMsgList.size > 0) {
            uiMsgList.clear()
        }
        updateStatus(true)
    }

    /**
     * 修改队列状态
     * */
    fun updateStatus(status: Boolean) {
        type = status
    }

    /**
     * 开启定时任务，数据清空
     * */
    @JvmStatic
    fun startReceiveMsg() {
        if (cacheGiftList.size > 0) {
            cacheGiftList.clear()
        }
        if (uiMsgList.size > 0) {
            uiMsgList.clear()
        }
        updateStatus(true)
        futures = msgTimer.scheduleAtFixedRate(
            TimerTask(),
            0,
            500,
            TimeUnit.MILLISECONDS
        )
    }

    /**
     * 结束定时任务，数据清空
     * ### 退出登录，需要清楚
     * */
    fun stopReceiveMsg() {
        updateStatus(false)
        if (cacheGiftList.size > 0) {
            cacheGiftList.clear()
        }
        if (uiMsgList.size > 0) {
            uiMsgList.clear()
        }
        if (::futures.isInitialized) {
            futures.cancel(true)
        }
        msgTimer.shutdown()
    }

    /**
     * 定时任务
     * */
    class TimerTask : Runnable {
        override fun run() {
            try {
                synchronized(cacheGiftList) {
                    if (type) {
                        if (cacheGiftList.isNullOrEmpty()) {
                            return
                        }
                        uiMsgList.clear()
                        uiMsgList.offer(cacheGiftList.pollLast())
                        uiMsgList.poll()?.let {
                            if (cacheGiftList.size > 1) {
                                it.type = true
                            }
                            //poll一个用户信息，且从剩余集合中过滤出第一个不同名字的用户
                            queuePushInterface?.handleMessage(
                                it,
                                "",
                                cacheGiftList.size + 1 // 因为poll了一个 所以数量加1
                            )
                        }
                        cacheGiftList.clear()
                    }
                }
            } catch (e: Exception) {
                Log.d("QueuePushHelper", "run: e $e")
            }
        }
    }

    interface QueuePushInterface {

        fun handleMessage(item: QueuePushBean, name: String?, msgCount: Int)
    }
}
