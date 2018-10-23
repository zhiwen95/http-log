# http-log
http request log print

## branch  
- master [![Build Status](https://travis-ci.org/zhiwen95/http-log.svg?branch=master)](https://travis-ci.org/zhiwen95/http-log)
- develop [![Build Status](https://travis-ci.org/zhiwen95/http-log.svg?branch=develop)](https://travis-ci.org/zhiwen95/http-log)  

## 使用方式
按照以下顺序设置javax.servlet.Filter
1. RequestIdFilter
2. PostJsonRequestBodyCacheFilter
3. RequestLogFilter

## Filter
- PostJsonRequestBodyCacheFilter  
    缓存application/json请求，使其可以多次读取inputStream
- RequestIdFilter  
    设置请求日志ID
- RequestLogFilter  
    打印请求日志


