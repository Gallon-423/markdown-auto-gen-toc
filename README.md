# markdown-auto-gen-toc
## 项目描述:
Java实现为md文档进行自动生成目录
![441125](https://user-images.githubusercontent.com/81243518/162879657-c5a00d62-4b9a-4445-aa74-cdfb25679135.jpg)
## 用了什么
- Java文件IO流
- 正则表达式
- bash脚本
## 说明
- 目的为个人使用
- 如果阅读代码后，可以很简单地构造一些特殊情况从而产生各种各样的纰漏，并不打算处理这部分特殊情况
- 目标是为依照正常规范书写的markdown文件添加或更新目录
## 环境要求
Windows下装有WSL或者Git Bash
Linux无需额外配置环境
## 使用
- 将原始目录下的create_toc.sh和target目录下的single-file-CN.jar放入平时放置md文件的目录下。
- 运行脚本，会将原有的md文件进行备份放入新生成的md_backup文件夹，并用生成toc的md文件替换原有文件。
- (可以选择是否进行该步骤)脚本自动运行
  - Windows下可创建基本任务。[参考](https://cloud.tencent.com/developer/article/1528138)
  - Linux下使用Crontab。[参考](https://www.runoob.com/w3cnote/linux-crontab-tasks.html)
