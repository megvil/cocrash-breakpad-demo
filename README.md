# cocrash-breakpad-demo

该库封装编译了开源库 Google Breakpad, 使用jni，编译出动态链接so库，可让你直接继承到你的项目中去
native crash 的抓取，错误信息存在.dmp的文件中，存成路径可以在初始化的时候，直接穿进去。

等获取到dmp文件后，需要进行解析，还原错误信息，拿到准确的错误定位
解析工具请使用另外一个库[cocrash](https://github.com/yejiurui/cocrash),使用该工具，你可直接获取到错误信息。

同样你可以把该步操作，放到服务器上来做，并做批量的解析，拿到可视化的错误信息

# 如何使用


## 下载NDK
访问[Android官网](https://developer.android.com/ndk/downloads/index.html)，从这个网页，你可以直接下载对应操作系统的NDK

## 配置NDK环境
解压NDK，配置NDK环境变量，确保`NDK-build`命令可以使用

## 下载google breakpad源文件
放到 `build_lib/google_breakpad` 目录下，目前已经内置了一份，可能不是最新的，可访问[Google github](https://github.com/google/breakpad)下载

## 编译相关的库
进入工程的`build_lib`目录，直接运行：
`./build.sh` 即可

编译后的.so文件所在：`build_lib/libs` 下

## 把编译好的so库集成到App(Android)
1. 拷贝`build_lib/libs` 下的.so文件到你项目的sample/cocrashlib/libs/下
2. 在sample根目录下执行 `build.sh`,即可，自动打成aar包, 把sample/release/libs 下的aar拷贝到你的工程即可，参加 sample/app 示例代码
3. 在你的Application类初始化：` NativeBreakpad.init(Environment.getExternalStorageDirectory().getAbsolutePath());`
注意：**这个方法所传的参数你可以直接定义**

















