# Git

## 配置ssh登录

**初次安装git需要配置用户名和邮箱**

```bash
git config --global user.name "Gallon"
git config --global user.email "2833275565@qq.com"
```

```bash
git config --global credential.helper store
```

:cry:git使用https协议，每次pull, push都会提示要输入密码，使用git协议，然后使用ssh密钥，这样免去每次都输密码的麻烦.

### 生成密钥对

```bash
ssh-keygen -t rsa ## -C "your_email@youremail.com"
```

<font color=LightBlue>Enter same passphrase again: [Type passphrase again]:</font>

可以输一个保证安全（020423cjl）

### 添加公钥到你的远程仓库（github）

#### 查看公钥

```bash
$ cat ~/.ssh/id_rsa.pub

ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC0X6L1zLL4VHuvGb8aJH3ippTozmReSUzgntvk434aJ/v7kOdJ/MTyBlWXFCR+HAo3FXRitBqxiX1nKhXpHAZsMciLq8vR3c8E7CjZN733f5AL8uEYJA+YZevY5UCvEg+umT7PHghKYaJwaCxV7sjYP7Z6V79OMCEAGDNXC26IBMdMgOluQjp6o6j2KAdtRBdCDS/QIU5THQDxJ9lBXjk1fiq9tITo/aXBvjZeD+gH/Apkh/0GbO8VQLiYYmNfqqAHHeXdltORn8N7C9lOa/UW3KM7QdXo6J0GFlBVQeTE/IGqhMS5PMln3 admin@admin-PC
```

#### 上传公钥

登陆你的github帐户。点击你的头像，然后 

`Settings -> 左栏点击 SSH and GPG keys -> 点击 New SSH key`

然后你复制上面的公钥内容，粘贴进“Key”文本域内。 title域，自己随便起个名字。

#### 检验

`$ ssh -T git@github.com`

如果出现

`Hi xxx! You've successfully authenticated, but GitHub does not provide shell access.`表示成功。

## PUSH失败：

### <font color=Red>OpenSSL SSL_read: Connection was aborted, errno 10053</font>

- 修改缓冲区大小
  
  `git config http.postBuffer 524288000`

- 修改配置
  
  `git config http.sslVerify "false"`

## Git Flow

![](C:\Users\28332\AppData\Roaming\marktext\images\2022-04-03-22-55-43-image.png)

### Tag/Github-release

![](C:\Users\28332\AppData\Roaming\marktext\images\2022-04-08-23-17-02-image.png)

输入tag之后还要点击一下下面的按钮（有点隐蔽）。

# 正则表达式

## Java中注意事项

`matches()`是完全匹配，执行该方法后，会改变Matcher对象中的成员变量值，导致继续执行`find()`时可能无法匹配到正确结果。

`find()`是局部匹配，执行该方法不会改变Matcher对象中的成员变量值，每执行一次该方法都会使内部的游标向右移动到下一个匹配到的位置，通常搭配`group()`来获取当次局部匹配到的字符串。

`find()`在局部匹配成功后下标从0开始计算，可以通过`find(int start)`来重置局部匹配的位置。

如果在匹配字符串时，需要同时使用到`matches()`和`find()`，应该在最后使用`matches()`，避免Matcher对象被修改导致`find()`结果不正确。或者不使用同一个Matcher对象来调用`matches()`和`find()`。

## 版本号

软件版本号由四部分组成：

- 第一个1为主版本号

- 第二个1为子版本号

- 第三个1为阶段版本号

- 第四部分为（日期版本号加）希腊字母版本号

##### 软件版本阶段说明

- `Alpha版`: 此版本表示该软件在此阶段主要是以实现软件功能为主，通常只在软件开发者内部交流，一般而言，该版本软件的Bug较多，需要继续修改。

- `Beta版`: 该版本相对于α版已有了很大的改进，消除了严重的错误，但还是存在着一些缺陷，需要经过多次测试来进一步消除，此版本主要的修改对像是软件的UI。

- `RC版`: 该版本已经相当成熟了，基本上不存在导致错误的BUG，与即将发行的正式版相差无几。

- `Release版`: 该版本意味“最终版本”，在前面版本的一系列测试版之后，终归会有一个正式版本，是最终交付用户使用的一个版本。该版本有时也称为标准版。一般情况下，`Release` 不会以单词形式出现在软件封面上，取而代之的是符号(R)。

eg.版本号1.0.2_alpha
