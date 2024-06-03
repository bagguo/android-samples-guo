<!-- TOC -->

* [Why](#why)
* [What](#what)
* [Feature](#feature)
    * [1 防止域名劫持](#1-防止域名劫持)
    * [2 精准调度](#2-精准调度)
    * [3 用户连接失败率下降](#3-用户连接失败率下降)

<!-- TOC -->

# Why

弱网环境下，基于UDP的LocalDNS解析容易解析超时，并且即使解析成功会消耗数百毫秒甚至更多

DNS优化对整个应用的网络优化所占权重大

# What

HTTPDNS使用HTTP与DNS服务器交互，代替传统的基于UDP的DNS协议，域名解析请求直接发送到HTTPDNS服务端，从而绕过运营商的Local
DNS

# Feature

## 1 防止域名劫持

由于 HttpDns 是通过 IP 直接请求 HTTP 获取服务器 A 记录地址，不存在向本地运营商询问 domain
解析过程，所以从根本避免了劫持问题。

## 2 精准调度

HTTPDNS能够直接获取到用户的IP地址，从而实现精确定位与导流

## 3 用户连接失败率下降

通过算法降低以往失败率过高的服务器排序，通过时间近期访问过的数据提高服务器排序，通过历史访问成功记录提高服务器排序。
