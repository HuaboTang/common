# Release Notes

## 1.3.7 2017-7-25
- 枚举类bug处理

## 1.3.6 2017-06-22
- failed的单元测试处理
## 1.3.5 2017-06-22
- 优化枚举类的序列化器 & 新增Timestamp类型序列化类
## 1.3.5 2017-06-21
- 升级BeanUtils.copyPropertiesAndConvertKeyToEnum 支持Collecion类型参数
## 1.3.4 2017-06-07
- 新增序列化类EnumWithKeySerializer实现 EnumWithKey<Key>类 -> key 的转化
## 1.3.3 2017-05-09
- 重命名 BeanUtils的copyPropertiesXXX方法.

## 1.3.2 2017-05-05
- 能用返回状态码,增加NOT_LOGIN,标识用户未登录

## 1.3.1 2017-05-04
- 优化&&扩充ResultCode枚举值,引入javax-validation依赖

## 1.3.0 2017-05-03
- 增加DubboAnnotationBean

## 1.2.0 2017-05-02
- 增加RpcResultUtils工具方法

## 1.1.0 2017-03-22
- JsonMapper增加将字符串转化为集合的方法

## 1.0.20 2017-03-17
- 扩展BeanUtils实现,新增两个方法.bean属性复制时,支持枚举值到枚举类(实现EnumWithKey)转换.

## 1.0.19.1 2017-03-10
- 1.0.19版本有问题,修复BeanUtils.copyProperties复制无效的问题

## 1.0.19 2017-03-10
- 调整BeanUtils实现,不再继承apache BeanUtils,内部改动Spring BeanUtils实现

## 1.0.18 2017-02-16
- 更改commons-collection 为commons-collection4

## 1.0.17 2017-02-16
- 增加DateJsonSerializer/EnumWithKeyDescCollectionSerializer/EnumWithKeyDescSerializer

## 1.0.16 2017-02-15
- 调整EnumUtils,增加了一些不抛出异常的处理方法

## 1.0.15 2017-02-13
- 增加rpc的内容

## 1.0.14 2017-02-10
- 1.0.13的修正版本,正确的解决Enum json序列化问题

## 1.0.13 2017-02-10
你看不到我看不到我,这个版本不要用,有错误
- 调整Enum json序列化处理,解决序列化不存在的enum报错的问题

## 1.0.12 2017-02-09
- 新增枚举值的序列化工具 

## 1.0.10 2017-01-17
- 调整ServiceException, 增加构造方法,解决Assert无法抛出异常的问题

## 1.0.9 2017-01-16
- 增加EnumWithKeyDesc接口 处理序列化时的枚举的key-desc

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
