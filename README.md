# Release Notes
## 1.0.9 2017-01-17
- 调整ServiceException, 增加构造方法,解决Assert无法抛出异常的问题

## 1.0.8 2017-01-04 (与上一版本不兼容,慎重升级)
- 调整EnumUtils,让其增加更多类型的枚举值类型

## 1.0.7 2016-12-28
- add com.codrim.common.utils.enums.EnumUtils.enumForKey(java.lang.Class<T>, java.lang.Integer)

## 1.0.6 2016-12-28
-优化BeanUtils.copyProperties & 新增 BeanUtils.populate支持

## 1.0.5 2016-12-02
-HttpClientUtils.post(url,json),json转二进制时,增加编码UTF-8处理

## 1.0.4 2016-12-01
- HttpClientUtils增加get方法,并增加返回状态码的方法

## 1.0.3
- 优化enum的命名及相关常量
- 引入number to date 序列化 及 string to date 反序列化类
## 1.0.2
- 增加分页相关参数
- 修改package name

## 1.0.1
- 引入CommonResult

## 1.0.0
初始版本
